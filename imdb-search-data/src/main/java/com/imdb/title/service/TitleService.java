package com.imdb.title.service;

import com.imdb.title.model.Rating;

import java.util.List;
import java.util.Map;

public interface TitleService {
    List<String> getTitlesBySameDirectorAndWriter();
    List<String> getTitlesByActors(String actor1, String actor2);
    Map<String, Rating> getBestTitlesByGenre(String genre);
}
