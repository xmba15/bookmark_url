package com.bookmarkapp.bookmark_url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmarkUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkUrlApplication.class, args);
	}

}
