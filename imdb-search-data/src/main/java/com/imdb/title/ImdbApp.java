package com.imdb.title;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ImdbApp {

	public static void main(String[] args) {
		SpringApplication.run(ImdbApp.class, args);
	}

}
