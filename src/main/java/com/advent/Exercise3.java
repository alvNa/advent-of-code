package com.advent;

import java.util.Scanner;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exercise3 {

    public static int multiplicationsSum(String input){
        var scanner = new Scanner(input);

        return scanner.findAll(Pattern.compile("mul\\((\\d+),(\\d+)\\)"))
                .map(mr -> {
                    var a = Integer.parseInt(mr.group(1));
                    var b = Integer.parseInt(mr.group(2));
                    return a * b;
                }).reduce(0, Integer::sum);
    }
}
