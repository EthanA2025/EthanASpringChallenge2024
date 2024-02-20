package com.paychex.moviemetadataservicepyx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class MovieMetadataServicePyxApplication {


	public static void main(String[] args) {
		SpringApplication.run(MovieMetadataServicePyxApplication.class, args);
	}

}
