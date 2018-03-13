package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.Domain.Book;
import com.example.Bookstore.Domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {

		return (args) -> {
			log.info("save books");
			repository.save(new Book("Eeva ja error", "Lelli lesa", 1234, 200108, 1));
			repository.save(new Book("tuut", "Eeva Kannenleppä", 99999, 19992, 2));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
