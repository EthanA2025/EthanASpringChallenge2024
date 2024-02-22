package com.paychex.moviemetadataservicepyx;

import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import com.paychex.moviemetadataservicepyx.service.MovieService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {MovieRepository.class, MovieService.class})
public class MovieMetadataServicePyxApplication {


	public static void main(String[] args) {
		SpringApplication.run(MovieMetadataServicePyxApplication.class, args);
	}

}
