package com.imdb.title.controller;

import com.imdb.title.filter.RequestCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    private final RequestCounter counter;

    @Autowired
    public RequestController(RequestCounter counter) {
        this.counter = counter;
    }

    @GetMapping("/request-count")
    public int getRequestCount() {
        return counter.getRequestCount();
    }
}

