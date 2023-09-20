package com.imdb.title.service;

import com.imdb.title.model.Rating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TitleServiceImpl implements TitleService{

    private final DataSearchService dataSearchService;

    @Cacheable("sdw")
    public List<String> getTitlesBySameDirectorAndWriter() {
        return dataSearchService.getAllTitleSameDirectorAndWriterIsLive();
    }

    @Cacheable(value = "actors", key = "#actor1+'-'+#actor2")
    public List<String> getTitlesByActors(String actor1, String actor2) {
        return dataSearchService.getAllTitlesPlaySameActors(actor1, actor2);
    }

    @Cacheable(value = "genre", key = "#genre")
    public Map<String, Rating> getBestTitlesByGenre(String genre) {
        return dataSearchService.getTitleByGenre(genre);
    }

}
