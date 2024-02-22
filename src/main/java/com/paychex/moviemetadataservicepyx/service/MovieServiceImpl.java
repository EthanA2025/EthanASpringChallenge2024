package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import com.paychex.moviemetadataservicepyx.utility.TitleCaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import com.paychex.moviemetadataservicepyx.handling.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.member;

/**
 * Class that represents the implementation of the methods that will preform CRUD operations
 */
@Service
public class MovieServiceImpl implements MovieService {
    MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findMoviesByTitle(title);
        if(movies.isEmpty()) {
            String message = "Title of movie: " + title + " was not found";
            throw new ApiRequestException(message);
        }
        return movies.stream()
                .map(movie -> {
                    movie.setTitle(TitleCaseConverter.titleCase(movie.getTitle()));
                    return movie;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        List<Movie> movies = movieRepository.findMoviesByYear(year);
        if (movies.isEmpty()) {
            String message = "Year for movie of: " + year + " was not found";
            throw new ApiRequestException(message);
        }
        return movies;
    }

    @Override
    public List<Movie> getMovieByCastMember(String member) {
        List<Movie> movies = movieRepository.findMoviesByCastMember(member);
        if (movies.isEmpty()) {
            String message = "Movie cast member: " + member + " was not found";
            throw new ApiRequestException(message);
        }
        return movies;
    }

    @Override
    public List<Movie> getMoviesByDecade(int decadeLowerBound) {
        int decadeUpperBound = decadeLowerBound+10;
        List<Movie> movies = movieRepository.findMoviesByDecade(decadeLowerBound, decadeUpperBound);
        if (movies.isEmpty()) {
            String message = "No movies in the range of decade: " + decadeLowerBound + "-" + decadeUpperBound;
            throw new ApiRequestException(message);
        }
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }
}
