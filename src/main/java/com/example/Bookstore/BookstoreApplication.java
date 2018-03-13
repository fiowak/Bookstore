package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.Domain.Book;
import com.example.Bookstore.Domain.BookRepository;
import com.example.Bookstore.Domain.User;
import com.example.Bookstore.Domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, UserRepository urepository) {

		return (args) -> {
			log.info("save books");
			repository.save(new Book("ei ja error", "Lelli ", 1234, 200108, 1));
			repository.save(new Book("tuut", "Kannenleppä", 99999, 19992, 2));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");

			urepository.save(user1);
			urepository.save(user2);


			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
