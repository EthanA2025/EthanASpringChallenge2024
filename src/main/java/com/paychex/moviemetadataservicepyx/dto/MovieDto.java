package com.paychex.moviemetadataservicepyx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Represents a movie Dto object
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    @NotNull
    public String id;
    @NotNull
    @NotBlank(message = "Title is required for a movie")
    public String title;
    @NotNull
    @NotBlank(message = "year is required for a movie")
    public int year;
    @NotNull
    @NotBlank(message = "Genre is required for a movie")
    public String[] genre;
    @NotNull
    @NotBlank(message = "Cast is required for a movie")
    public String[] cast;

}
