package com.paychex.moviemetadataservicepyx.utility;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TitleCaseConverter {
    private static int index = 0;
    public TitleCaseConverter() {}
    public static String titleCase(String in) {
        index = 0;
        return Arrays.stream(in.split(" "))
                .map(word ->  {
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
                })
                .collect(Collectors.joining(" "));
    }

}
