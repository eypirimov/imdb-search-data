package com.imdb.title.async;

import com.imdb.title.io.CacheDataProcessor;
import com.imdb.title.util.FileNameEnum;
import com.imdb.title.util.StaticData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheDataLoader implements Runnable {
    private final FileNameEnum type;
    private final CacheDataProcessor cacheDataProcessor;

    public CacheDataLoader(FileNameEnum type, CacheDataProcessor cacheDataProcessor){
        this.type = type;
        this.cacheDataProcessor = cacheDataProcessor;
    }

    @Override
    public void run() {
        switch (type) {
            case NAMES_BASICS:
                StaticData.setNotLivePersonNameList(cacheDataProcessor.readNameBasicFile(type.getFilePath()));
                break;
            case TITLE_CREW:
                StaticData.setSameDirectorAndWriterTitleList(cacheDataProcessor.readTitleCrewFile(type.getFilePath()));
                break;
            case TITLE_PRINCIPALS:
                StaticData.setActorLineList(cacheDataProcessor.readTitlePrincipalsFile(type.getFilePath()));
                break;
            case TITLE_RATINGS:
                StaticData.setTitleRatingsMap(cacheDataProcessor.readTitleRatingFile(type.getFilePath()));
                break;
            default:
                log.info("Unknown Cache Type!!!!");
        }
    }



}
