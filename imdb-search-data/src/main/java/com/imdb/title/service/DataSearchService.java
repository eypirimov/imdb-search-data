package com.imdb.title.service;

import com.imdb.title.model.Rating;

import java.util.List;
import java.util.Map;

public interface DataSearchService {

    Map<String, Rating> getTitleByGenre(String genre);

    List<String> getAllTitlesPlaySameActors(String actor1, String actor2);

    List<String> getAllTitleSameDirectorAndWriterIsLive();
}
