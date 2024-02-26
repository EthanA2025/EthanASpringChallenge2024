package com.paychex.moviemetadataservicepyx.utility;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TitleCaseConverter {
    private static int index = 0;
    public TitleCaseConverter() {}
    public static String conversion(@NotNull String word) {
        String conversion = "";
        while(index < word.length()) {
            if(Character.isLetter(word.charAt(index))) {
                conversion += word.substring(index,index+1).toUpperCase() + word.substring(index+1).toLowerCase();
                index = 0;
                return conversion;
            } else {
                conversion += word.charAt(index);
                index++;
            }
        }
        index = 0;
        return word;
    }

    public static String titleCase(@NotNull String in) {
        index = 0;
        return Arrays.stream(in.split(" "))
                .map(TitleCaseConverter::conversion)
                .collect(Collectors.joining(" "));
    }

}
