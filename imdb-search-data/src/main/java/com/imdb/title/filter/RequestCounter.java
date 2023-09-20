package com.imdb.title.filter;

import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestCounter {

    private final AtomicInteger requestCount = new AtomicInteger(0);

    public int getRequestCount() {
        return requestCount.get();
    }

    public void incrementRequestCount() {
        requestCount.incrementAndGet();
    }
}

