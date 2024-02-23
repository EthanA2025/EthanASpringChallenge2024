package com.paychex.moviemetadataservicepyx.mapping;

import com.paychex.moviemetadataservicepyx.dto.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.paychex.moviemetadataservicepyx.model.Movie;

@Mapper
public interface MovieMapper {

    MovieMapper MAPPER = Mappers.getMapper(MovieMapper.class);

    MovieDto mapToMovieDto(Movie movie);

    Movie mapToMovie(MovieDto movieDto);
}
