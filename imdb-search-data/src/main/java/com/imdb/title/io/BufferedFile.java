package com.imdb.title.io;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class BufferedFile {

    public BufferedReader getBufferedReader(String path) throws IOException {
        return new BufferedReader(new FileReader(getResourceFilePath(path)));
    }

    private String getResourceFilePath(String resourceFilePath) throws IOException {
        Resource resource = new ClassPathResource(resourceFilePath);
        return resource.getFile().getAbsolutePath();
    }
}
