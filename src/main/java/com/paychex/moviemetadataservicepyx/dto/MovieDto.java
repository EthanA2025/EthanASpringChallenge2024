package com.paychex.moviemetadataservicepyx.dto;

import javax.validation.Valid;

public class MovieDto {

    public String id;
    @Valid
    public String title;
    public int year;
    public String[] genre;
    public String[] cast;

}
