package com.imdb.title.async;

import com.imdb.title.io.CacheDataProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

import static com.imdb.title.util.FileNameEnum.*;

@Component
@RequiredArgsConstructor
public class AsyncTaskExecutor {

    public final CacheDataProcessor cacheDataProcessor;

    @PostConstruct
    public void executeTasks() {
        List<Thread> list = Arrays.asList(new Thread(new CacheDataLoader(TITLE_PRINCIPALS, cacheDataProcessor)),
                new Thread(new CacheDataLoader(NAMES_BASICS, cacheDataProcessor)),
                new Thread(new CacheDataLoader(TITLE_CREW, cacheDataProcessor)),
                new Thread(new CacheDataLoader(TITLE_RATINGS, cacheDataProcessor)));

        list.forEach(Thread::start);

        list.forEach(task -> {
            try {
                task.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
