package com.paychex.moviemetadataservicepyx.utility;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TitleCaseConverter {

    public TitleCaseConverter() {}
    public static String titleCase(String in) {
        return Arrays.stream(in.split(" "))
                .map(word -> word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
