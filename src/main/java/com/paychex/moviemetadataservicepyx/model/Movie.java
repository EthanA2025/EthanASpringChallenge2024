package com.paychex.moviemetadataservicepyx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

/**
 * Represents a movie object and its associated data
 */
@Document(collection = "movies")
public class Movie {
    @Id
    public String id;
    public String title;
    public int year;
    public String[] genre;
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

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String[] getGenre() {return this.genre ;}
    public String[] getCast() {return this.cast; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return String.format(
                "Movie[id='%s', title='%s', year='%d', genre(s)='%s', cast='%s']",
                id, title, year, Arrays.toString(genre), Arrays.toString(cast));
    }
}