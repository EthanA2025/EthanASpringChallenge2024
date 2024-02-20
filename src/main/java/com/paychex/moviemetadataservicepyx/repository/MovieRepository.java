package com.paychex.moviemetadataservicepyx.repository;

import com.paychex.moviemetadataservicepyx.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a Repository for Movies
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

    @Query("{'title' : ?0 }")
    List<Movie> findMoviesByTitle(String title);
    @Query("{year : ?0 }")
    List<Movie> findMoviesByYear(int year);
}
