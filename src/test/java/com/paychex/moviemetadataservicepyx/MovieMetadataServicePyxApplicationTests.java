package com.paychex.moviemetadataservicepyx;

import com.paychex.moviemetadataservicepyx.controller.MovieController;
import com.paychex.moviemetadataservicepyx.dto.MovieDto;
import com.paychex.moviemetadataservicepyx.handling.ApiRequestException;
import com.paychex.moviemetadataservicepyx.mapping.MovieMapper;
import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.utility.DtoListConverter;
import com.paychex.moviemetadataservicepyx.utility.TitleCaseConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieMetadataServicePyxApplicationTests {

	@Autowired
	private MovieController controller;

	private MovieMapper movieMapper;

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
		assertEquals(movie.getYear(), 1996);
		assertArrayEquals(movie.getCast(), new String[]{"LeBron James", "Bugs Bunny"});
		assertArrayEquals(movie.getGenre(), new String[]{"Comedy", "Kids"});
	}

	@Test
	void convertToTitleCase() {
		String before = "tHis wiLl BeCome title Case";
		String converted = TitleCaseConverter.titleCase(before);
		String expected = "This Will Become Title Case";

		assertEquals(converted, expected);
	}

	@Test
	void convertToTitleCaseNonAlphaString() {
		String before = ("(how to train your dragon)");
		String converted = TitleCaseConverter.titleCase(before);
		String expected = ("(How To Train Your Dragon)");

		assertEquals(converted, expected);
	}

	@Test
	void convertToTitleCaseNonAlphaStringAndNumbers() {
		String before = ("2. (2how to train your dragon)");
		String converted = TitleCaseConverter.titleCase(before);
		String expected = ("2. (2How To Train Your Dragon)");

		assertEquals(converted, expected);
	}

	@Test
	void convertMovieToMovieDto() {
		Movie movie = new Movie("100", "Space Jam", 1996,
				new String[]{"Comedy", "Kids"},
				new String[]{"LeBron James", "Bugs Bunny"});
		MovieDto movieDto = movieMapper.MAPPER.mapToMovieDto(movie);

		assertEquals(movieDto.getId(), "100");
		assertEquals(movieDto.getTitle(), "Space Jam");
		assertEquals(movieDto.getYear(), 1996);
		assertArrayEquals(movieDto.getCast(), new String[]{"LeBron James", "Bugs Bunny"});
		assertArrayEquals(movieDto.getGenre(), new String[]{"Comedy", "Kids"});
	}

	@Test
	void convertMovieListToMovieDtoList() {
		Movie movie1 = new Movie("100", "Space Jam", 1996,
				new String[]{"Comedy", "Kids"},
				new String[]{"LeBron James", "Bugs Bunny"});

		List<Movie> movies = new ArrayList<Movie>();
		movies.add(movie1);
		List<MovieDto> moviesDto = DtoListConverter.movieListToMovieDtoList(movies);

		assertEquals(moviesDto.get(0).getId(), movies.get(0).getId());
		assertEquals(moviesDto.get(0).getTitle(), movies.get(0).getTitle());
		assertEquals(moviesDto.get(0).getYear(), movies.get(0).getYear());
		assertArrayEquals(moviesDto.get(0).getGenre(), movies.get(0).getGenre());
		assertArrayEquals(moviesDto.get(0).getCast(), movies.get(0).getCast());
	}

	@Test
	void convertMovieDtoToMovie() {
		MovieDto movieDto = new MovieDto("100", "Space Jam", 1996,
				new String[]{"Comedy", "Kids"},
				new String[]{"LeBron James", "Bugs Bunny"});
		Movie movie = movieMapper.MAPPER.mapToMovie(movieDto);

		assertEquals(movieDto.getId(), "100");
		assertEquals(movieDto.getTitle(), "Space Jam");
		assertEquals(movieDto.getYear(), 1996);
		assertArrayEquals(movieDto.getCast(), new String[]{"LeBron James", "Bugs Bunny"});
		assertArrayEquals(movieDto.getGenre(), new String[]{"Comedy", "Kids"});
	}

	@Test
	void getMovieByTitle() {
		List<MovieDto> movies = controller.getMoviesByTitle("How to Train Your Dragon");
		String expected = "How To Train Your Dragon";
		String actual = movies.get(0).getTitle();

		assertEquals(expected, actual);
	}

	@Test
	void getAllMovies() {
		List<MovieDto> movies = controller.getAllMovies();

		assertNotNull(movies);
		assertInstanceOf(MovieDto.class, movies.get(0));
	}

	@Test
	void getMoviesByYear() {
		List<MovieDto> movies = controller.getMoviesByYear(1999);
		int yearExpected = 1999;
		int yearActual = movies.get(0).getYear();

		assertEquals(yearExpected, yearActual);
	}

	@Test
	void getMoviesByCastMember() {
		List<MovieDto> movies = controller.getMoviesByCastMember("Kevin Bowman");
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
	void createBadRequestDecade() {
		assertThrows(ApiRequestException.class, () -> {
			controller.getMoviesByDecade(100000);
		});
	}

	@Test
	void createBadRequestCastMember() {
		assertThrows(ApiRequestException.class, () -> {
			controller.getMoviesByCastMember("Stewie Griffin");
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
		List<MovieDto> movies = controller.getMoviesByDecade(decade);
		assertEquals(movies.get(0).getYear(), decade);
	}

	@Test
	void getMovieByDecadeUpperBound() {
		int decade = 2009;
		List<MovieDto> movies = controller.getMoviesByDecade(decade);
		assertEquals(movies.get(movies.size()-1).getYear(), decade+10);
	}

	@Test
	void sendRequestInvalidMovieNotAdded() {
		Movie invalidMovie = new Movie(null, null, 0, null, null);
		assertThrows(Exception.class, () -> {controller.addMovie(invalidMovie);});
	}
}
