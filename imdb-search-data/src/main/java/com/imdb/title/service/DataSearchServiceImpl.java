package com.imdb.title.service;

import com.imdb.title.io.BufferedFile;
import com.imdb.title.model.Rating;
import com.imdb.title.util.StaticData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import static com.imdb.title.util.FileNameEnum.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class DataSearchServiceImpl implements DataSearchService {

    private final BufferedFile bufferedFile;

    /***** Find all titles in which both director and writer are the same person *****/
    public List<String> getAllTitleSameDirectorAndWriterIsLive() {
        List<String> titles = new ArrayList<>();
        try (BufferedReader br = bufferedFile.getBufferedReader(TITLE_CREW.getFilePath())) {
            String line;
            int lineNumber = 0;
            int listIndx = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (lineNumber == StaticData.getSameDirectorAndWriterTitleList().get(listIndx)) {
                    if (++listIndx == StaticData.getSameDirectorAndWriterTitleList().size())
                        break;
                    String tConst = checkLive(line);
                    if (tConst != null) {
                        titles.add(tConst);
                    }
                }
            }
        } catch (IOException e) {
            log.error("<getAllTitleSameDirectorAndWriterIsLive> Error : ", e);
        }
        return titles;
    }

    private String checkLive(String line) {
        String[] fields = line.split("\t");
        if (!StaticData.getNotLivePersonNameList().retainAll(Arrays.asList(fields[1].split(",")))) {
            return fields[0];
        }
        return null;
    }

    /***** Find all the titles in which both of two actors played at same title *****/
    public List<String> getAllTitlesPlaySameActors(String actor1, String actor2) {
        Map<String, List<String>> map = new HashMap<>();
        String line;
        int lineNumber = 0;
        int listIndx = 0;
        try (BufferedReader br = bufferedFile.getBufferedReader(TITLE_PRINCIPALS.getFilePath())) {
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (lineNumber == StaticData.getActorLineList().get(listIndx)) {
                    if (++listIndx == StaticData.getActorLineList().size())
                        break;
                    String[] fields = line.split("\t");
                    if ((actor1.equals(fields[2]) || actor2.equals(fields[2]))) {
                        map.computeIfAbsent(fields[2], k -> new ArrayList<>()).add(fields[0]);
                    }
                }
            }
        } catch (IOException e) {
            log.error("<getAllTitlesPlaySameActors> Error : ", e);
        }
        return getRetainTitles(map.get(actor1), map.get(actor2));
    }

    private List<String> getRetainTitles(List<String> list1, List<String> list2) {
        if (list1 != null && list2 != null && !list1.isEmpty() && !list2.isEmpty()) {
            List<String> commonElements = new ArrayList<>(list1);
            list1.retainAll(list2);
            return commonElements;
        }
        return Collections.emptyList();
    }

    /***** Find the best titles on each year by genre based on number of votes and rating *****/
    public Map<String, Rating> getTitleByGenre(String genre) {
        Map<String, Rating> bestTitles = new HashMap<>();
        try (BufferedReader br = bufferedFile.getBufferedReader(TITLE_BASICS.getFilePath())) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(genre)) {
                    processLineForBestTitle(line, bestTitles);
                }
            }
        } catch (IOException e) {
            log.error("<getTitleByGenre> Error : ", e);
        }
        return bestTitles;
    }

    private void processLineForBestTitle(String line, Map<String, Rating> bestTitles) {
        String[] arr = line.split("\t");
        if (!arr[5].equalsIgnoreCase("\\n")) {
            Rating newRate = StaticData.getTitleRatingsMap().get(arr[0]);
            if (newRate != null) {
                updateBestTitle(bestTitles, arr[5], newRate);
            }
        }
    }

    private void updateBestTitle(Map<String, Rating> bestTitles, String year, Rating newRate) {
        bestTitles.compute(year, (existingYear, existingRate) -> {
            if (existingRate == null || compareRate(newRate, existingRate) == newRate) {
                return newRate;
            }
            return existingRate;
        });
    }

    private Rating compareRate(Rating newRate, Rating oldRate) {
        if ((newRate.getAvgRating() * newRate.getNumVotes())
                > (oldRate.getAvgRating() * oldRate.getNumVotes())) {
            return newRate;
        }
        return oldRate;
    }
}
