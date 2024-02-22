package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    public List<Movie> getAll();
    public List<Movie> getMoviesByTitle(String title);
    public List<Movie> getMoviesByYear(int year);
    public List<Movie> getMovieByCastMember(String member);
    public List<Movie> getMoviesByDecade(int decadeLowerBound);
    public Movie addMovie(Movie movie);
}
