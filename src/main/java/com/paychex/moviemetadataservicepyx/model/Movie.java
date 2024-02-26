package com.paychex.moviemetadataservicepyx.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Represents a movie object and its associated data
 */
@Getter
@Setter
@Document(collection = "movies")
public class Movie {
    @Id
    public String id;
    @NotNull
    public String title;
    @NotNull
    public int year;
    @NotNull
    public String[] genre;
    @NotNull
    public String[] cast;

    public Movie() {}

    public Movie(String id, String title, int year, String[] genre, String[] cast) {
        super();
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.cast = cast;
    }

    @Override
    public String toString() {
        return String.format(
                "Movie[id='%s', title='%s', year='%d', genre(s)='%s', cast='%s']",
                id, title, year, Arrays.toString(genre), Arrays.toString(cast));
    }
}