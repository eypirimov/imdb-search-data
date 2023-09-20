package com.imdb.title.io;


import com.imdb.title.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CacheDataProcessor {
    private final BufferedFile bufferedFile;

    public CacheDataProcessor(BufferedFile bufferedFile) {
        this.bufferedFile = bufferedFile;
    }

    public List<String> readNameBasicFile(String path) {
        String line;
        List<String> list = new ArrayList<>();
        try (BufferedReader br = bufferedFile.getBufferedReader(path)) {
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields[3].equalsIgnoreCase("\\N")) {
                    list.add(fields[0]);
                }
            }
        } catch (IOException e) {
            log.error("Error : ", e);
        }
        return list;
    }

    public Map<String, Rating> readTitleRatingFile(String path) {
        String line;
        Map<String, Rating> map = new HashMap<>();
        try (BufferedReader br = bufferedFile.getBufferedReader(path)) {
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] fields = line.split("\t");
                float avgRate = Float.parseFloat(fields[1]);
                int numVotes = Integer.parseInt(fields[2]);
                map.put(fields[0], new Rating(fields[0], avgRate, numVotes));
            }
        } catch (Exception e) {
            log.error("Error : ", e);
        }
        return map;
    }

    public List<Integer> readTitleCrewFile(String path) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = bufferedFile.getBufferedReader(path)) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                if (lineNum == 1) {
                    continue;
                }
                String[] fields = line.split("\t");
                if (fields[1].equalsIgnoreCase(fields[2]) && !fields[1].equalsIgnoreCase("\\N")
                        && !fields[2].equalsIgnoreCase("\\N")) {
                    list.add(lineNum);
                }
            }
        } catch (Exception e) {
            log.error(" setTitleBasicPointers Error : {}", e);
        }
        return list;
    }

    public List<Integer> readTitlePrincipalsFile(String path) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = bufferedFile.getBufferedReader(path)) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                if (lineNum == 1) {
                    continue;
                }
                if (line.contains("actor")) {
                    list.add(lineNum);
                }
            }
        } catch (Exception e) {
            log.error(" setTitlePrincipalsPointers Error : {}", e);
        }
        return list;
    }
}

