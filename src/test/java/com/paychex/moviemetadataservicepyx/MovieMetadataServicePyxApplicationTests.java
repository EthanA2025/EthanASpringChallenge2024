package com.paychex.moviemetadataservicepyx;

import com.paychex.moviemetadataservicepyx.controller.MovieController;
import com.paychex.moviemetadataservicepyx.handling.ApiRequestException;
import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.utility.TitleCaseConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	void createMovieConstructor() {
		Movie movie = new Movie("100", "Space Jam", 1996,
				new String[]{"Comedy", "Kids"},
				new String[]{"LeBron James", "Bugs Bunny"});

		assertEquals(movie.getId(), "100");
		assertEquals(movie.getTitle(), "Space Jam");
		assertArrayEquals(movie.getCast(), new String[]{"LeBron James", "Bugs Bunny"});
		assertArrayEquals(movie.getGenre(), new String[]{"Comedy", "Kids"});
	}

	@Test
	void ConvertToTitleCase() {
		String before = "tHis wiLl BeCome title Case";
		String converted = TitleCaseConverter.titleCase(before);
		String expected = "This Will Become Title Case";

		assertEquals(converted, expected);
	}

	@Test
	void getMovieByTitle() {
		List<Movie> movies = controller.getMoviesByTitle("How to Train Your Dragon");
		String expected = "How To Train Your Dragon";
		String actual = movies.get(0).getTitle();

		assertEquals(expected, actual);
	}

	@Test
	void getAllMovies() {
		List<Movie> movies = controller.getAllMovies();

		assertNotNull(movies);
		assertInstanceOf(Movie.class, movies.get(0));
	}

	@Test
	void getMoviesByYear() {
		List<Movie> movies = controller.getMoviesByYear(1999);
		int yearExpected = 1999;
		int yearActual = movies.get(0).getYear();

		assertEquals(yearExpected, yearActual);
	}

	@Test
	void getMoviesByCastMember() {
		List<Movie> movies = controller.getMoviesByCastMember("Kevin Bowman");
		int numberOfMoviesExpected = 2;
		int numberOfMoviesActual = movies.size();

		assertEquals(numberOfMoviesExpected, numberOfMoviesActual);
	}

	@Test
	void createBadRequestYear() {
	    assertThrows(ApiRequestException.class, () -> {
			controller.getMoviesByYear(99999);
		});
	}

	@Test
	void createBadRequestTitle() {
		assertThrows(ApiRequestException.class, () -> {
			controller.getMoviesByTitle("This movie doesn't exist");
		});
	}

	@Test
	void addMovie() {
		Movie movie = new Movie("1", "title", 1000, new String[1], new String[1]);
		assertEquals(movie, controller.addMovie(movie));
	}

	@Test
	void getMovieByDecadeLowerBound() {
		int decade = 2009;
		List<Movie> movies = controller.getMoviesByDecade(decade);
		assertEquals(movies.get(0).getYear(), decade);
	}

	@Test
	void getMovieByDecadeUpperBound() {
		int decade = 2009;
		List<Movie> movies = controller.getMoviesByDecade(decade);
		assertEquals(movies.get(movies.size()-1).getYear(), decade+10);

	}
}
