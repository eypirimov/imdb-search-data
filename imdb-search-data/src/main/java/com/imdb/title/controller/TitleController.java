package com.imdb.title.controller;

import com.imdb.title.model.Rating;
import com.imdb.title.service.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/titles")
@Slf4j
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;

    @GetMapping(value = "/same-director-writer", produces = "application/json")
    public ResponseEntity<List<String>> getSameDirectorWriterTitles() {
        List<String> titles = titleService.getTitlesBySameDirectorAndWriter();
        return ResponseEntity.ok(titles);
    }


    @GetMapping(value = "/actors", produces = "application/json")
    public ResponseEntity<List<String>> getTitlesBySameActors(@RequestParam String actor1, @RequestParam String actor2) {
        List<String> titles = titleService.getTitlesByActors(actor1, actor2);
        return ResponseEntity.ok(titles);
    }

    @GetMapping(value = "/best/rating/genre", produces = "application/json")
    public ResponseEntity<Map<String, Rating>> getBestTitlesByGenre(@RequestParam String genre) {
        Map<String, Rating> titles = titleService.getBestTitlesByGenre(genre.toLowerCase());
        return ResponseEntity.ok(titles);
    }
}
