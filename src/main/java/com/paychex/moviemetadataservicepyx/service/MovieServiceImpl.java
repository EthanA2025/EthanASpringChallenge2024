package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.dto.MovieDto;
import com.paychex.moviemetadataservicepyx.mapping.MovieMapper;
import com.paychex.moviemetadataservicepyx.model.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import com.paychex.moviemetadataservicepyx.utility.DtoListConverter;
import com.paychex.moviemetadataservicepyx.utility.TitleCaseConverter;
import com.paychex.moviemetadataservicepyx.handling.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Class that represents the implementation of the methods that will preform CRUD operations
 */
@Service
public class MovieServiceImpl implements MovieService {

    private MovieMapper movieMapper;
    MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<MovieDto> getAll() {
        List<Movie> movies = movieRepository.findAll();

        return DtoListConverter.movieListToMovieDtoList(movies);
    }

    @Override
    public List<MovieDto> getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findMoviesByTitle(title);
        if(movies.isEmpty()) {
            String message = "Title of movie: " + title + " was not found";
            throw new ApiRequestException(message);
        }
        return movies.stream()
                .map(movie -> {
                    MovieDto movieDto = movieMapper.MAPPER.mapToMovieDto(movie);
                    movieDto.setTitle(TitleCaseConverter.titleCase(movie.getTitle()));
                    return movieDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> getMoviesByYear(int year) {
        List<Movie> movies = movieRepository.findMoviesByYear(year);
        if (movies.isEmpty()) {
            String message = "Year for movie of: " + year + " was not found";
            throw new ApiRequestException(message);
        }
        return DtoListConverter.movieListToMovieDtoList(movies);
    }

    @Override
    public List<MovieDto> getMovieByCastMember(String member) {
        List<Movie> movies = movieRepository.findMoviesByCastMember(member);
        if (movies.isEmpty()) {
            String message = "Movie cast member: " + member + " was not found";
            throw new ApiRequestException(message);
        }
        return DtoListConverter.movieListToMovieDtoList(movies);
    }

    @Override
    public List<MovieDto> getMoviesByDecade(int decadeLowerBound) {
        int decadeUpperBound = decadeLowerBound+10;
        List<Movie> movies = movieRepository.findMoviesByDecade(decadeLowerBound, decadeUpperBound);
        if (movies.isEmpty()) {
            String message = "No movies in the range of decade: " + decadeLowerBound + "-" + decadeUpperBound;
            throw new ApiRequestException(message);
        }
        return DtoListConverter.movieListToMovieDtoList(movies);
    }

    @Override
    public Movie addMovie(@Valid Movie movie) {
        try {
            movieRepository.save(movie);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        return movie;
    }
}
