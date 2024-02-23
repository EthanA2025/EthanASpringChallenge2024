package com.paychex.moviemetadataservicepyx.utility;

import com.paychex.moviemetadataservicepyx.dto.MovieDto;
import com.paychex.moviemetadataservicepyx.mapping.MovieMapper;
import com.paychex.moviemetadataservicepyx.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class DtoListConverter {
    static MovieMapper mapper;

    public static List<MovieDto> movieListToMovieDtoList(List<Movie> movies) {
        return movies.stream()
                .map(mapper.MAPPER::mapToMovieDto)
                .collect(Collectors.toList());
    }
}
