package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.dto.MovieDto;
import com.paychex.moviemetadataservicepyx.model.Movie;

import java.util.List;

/**
 * Interface that represents the service layer for movies
 */
public interface MovieService {
    List<MovieDto> getAll();
    List<MovieDto> getMoviesByTitle(String title);
    List<MovieDto> getMoviesByYear(int year);
    List<MovieDto> getMovieByCastMember(String member);
    List<MovieDto> getMoviesByDecade(int decadeLowerBound);
    Movie addMovie(Movie movie);
}
