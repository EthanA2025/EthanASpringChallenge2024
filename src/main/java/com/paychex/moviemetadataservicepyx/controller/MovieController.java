package com.paychex.moviemetadataservicepyx.controller;


import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import com.paychex.moviemetadataservicepyx.utility.TitleCaseConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the controller that deals with the HTTP requests given to the spring application
 */
@RestController
public class MovieController {

    private final static Logger LOGGER = LogManager.getLogger(MovieController.class);
    @Autowired
    MovieRepository movieRepository;

    /**
     * Adds a new movie to the database
     * @param movie the request body for the new movie
     */
    @PostMapping("/movies/addMovie")
    public void addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * Gets all the movie metadata in the database
     * @return returns a response of all documents
     */
    @GetMapping("/movies/all")
    List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Finds all movies given the title of that movie and converts to titlecase
     * @param title title of movies
     * @return All movies that fit the title criteria
     */
    @GetMapping("/movies/title/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        List<Movie> movies = movieRepository.findMoviesByTitle(title);
        return movies.stream()
                .map(movie -> {
                    movie.setTitle(TitleCaseConverter.titleCase(movie.getTitle()));
                    return movie;
                })
                .collect(Collectors.toList());
    }

    /**
     * Finds all movies given the year of that movie
     * @param year year of the movies
     * @return All movies that fit the title criteria
     */
    @GetMapping("/movies/year/{year}")
    List<Movie> getMoviesByYear(@PathVariable int year) {
        return movieRepository.findMoviesByYear(year);
    }
}
