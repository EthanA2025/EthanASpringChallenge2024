package com.paychex.moviemetadataservicepyx;

import com.paychex.moviemetadataservicepyx.controller.MovieController;
import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import com.paychex.moviemetadataservicepyx.utility.TitleCaseConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieMetadataServicePyxApplicationTests {

	@Autowired
	private MovieController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

	@Test
	void convertToTitleCase() {
		List<Movie> movies = controller.getMoviesByTitle("How to Train Your Dragon");
		String expected = "How To Train Your Dragon";
		String actual = movies.get(0).getTitle();

		assertEquals(expected, actual);
	}
}
