package com.imdb.title.service;

import com.imdb.title.model.Rating;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
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
        if (actor1 == null || actor1.isEmpty() || actor2 == null || actor2.isEmpty())
            return Collections.emptyList();
        return dataSearchService.getAllTitlesPlaySameActors(actor1, actor2);
    }

    @Cacheable(value = "genre", key = "#genre")
    public Map<String, Rating> getBestTitlesByGenre(String genre) {
        if (genre == null || genre.isEmpty())
            return new HashMap<>();
        return dataSearchService.getTitleByGenre(genre);
    }

}
