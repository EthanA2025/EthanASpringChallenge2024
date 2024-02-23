package com.paychex.moviemetadataservicepyx.controller;


import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.paychex.moviemetadataservicepyx.dto.MovieDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Represents the controller that deals with the HTTP requests given to the spring application
 */
@RestController
@Validated
public class MovieController {

    private final static Logger LOGGER = LogManager.getLogger(MovieController.class);

    @Autowired
    MovieService movieService;

    /**
     * Adds a new movie to the database
     * @param movie the request body for the new movie
     * @return movie that was added
     */
    @PostMapping("/movies/addMovie")
    public Movie addMovie(@RequestBody @Valid Movie movie) {
        return movieService.addMovie(movie);
    }

    /**
     * Gets all the movie metadata in the database
     * @return returns a response of all documents
     */
    @GetMapping("/movies/all")
    public List<MovieDto> getAllMovies() {
        return movieService.getAll();
    }

    /**
     * Finds all movies given the title of that movie and converts to titlecase
     * @param title title of movies
     * @return All movies that fit the title criteria
     */
    @GetMapping("/movies/title/{title}")
    public List<MovieDto> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    /**
     * Finds all movies given the year of that movie
     * @param year year of the movies
     * @return All movies that fit the title criteria
     */
    @GetMapping("/movies/year/{year}")
    public List<MovieDto> getMoviesByYear(@PathVariable int year) {
        return movieService.getMoviesByYear(year);
    }

    /**
     * Finds all movies given the cast member of that movie
     * @param member cast member of the movies
     * @return All movies that fit the cast member criteria
     */
    @GetMapping("/movies/member/{member}")
    public List<MovieDto> getMoviesByCastMember(@PathVariable String member) {
        return movieService.getMovieByCastMember(member);
    }

    /**
     * Finds all movies given a decade
     * @param decadeLowerBound start of the decade for the movies
     * @return All movies that fit from the decade starting point to 10 years after
     */
    @GetMapping("/movies/decade/{decadeLowerBound}")
    public List<MovieDto> getMoviesByDecade(@PathVariable int decadeLowerBound) {
        return movieService.getMoviesByDecade(decadeLowerBound);
    }
}
