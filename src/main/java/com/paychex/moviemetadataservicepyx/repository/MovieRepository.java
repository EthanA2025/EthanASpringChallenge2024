package com.paychex.moviemetadataservicepyx.repository;

import com.paychex.moviemetadataservicepyx.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Represents a Repository for Movies
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

    @Query("{'title' : ?0 }")
    List<Movie> findMoviesByTitle(String title);

    @Query("{year : ?0 }")
    List<Movie> findMoviesByYear(int year);

    @Query("{'cast': ?0 }")
    List<Movie> findMoviesByCastMember(String member);

    @Query(value = "{year : { $gte: ?0, $lte: ?1 } }", sort = "{ year : 1}")
    List<Movie> findMoviesByDecade(int decadeLowerBound, int decadeUpperBound);
}
