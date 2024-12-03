package com.advent;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exercise3 {
    private static boolean ENABLING_STATE = true;

    public static int multiplicationsSum(String input){
        var scanner = new Scanner(input);

        return scanner.findAll(Pattern.compile("mul\\((\\d+),(\\d+)\\)"))
                .map(mr -> {
                    var a = Integer.parseInt(mr.group(1));
                    var b = Integer.parseInt(mr.group(2));
                    return a * b;
                }).reduce(0, Integer::sum);
    }

    public static int multiplicationsSumWithEnabling(String input){
        var scanner = new Scanner(input);
        var regexp1 = "mul\\((\\d+),(\\d+)\\)";
        var regexp2 = "do\\(\\)";
        var regexp3 = "don't\\(\\)";

        Pattern pattern = Pattern.compile(regexp1+"|"+regexp2+"|"+regexp3);
        return scanner
                .findAll(pattern)
                .map(Exercise3::calculateFunValue)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(0, Integer::sum);
    }

    private static Optional<Integer> calculateFunValue(MatchResult mr) {
        var functor = mr.group(0);
        switch (functor){
            case "do()":
                ENABLING_STATE = true;
                break;
            case "don't()":
                ENABLING_STATE = false;
                break;
            default:
                if (ENABLING_STATE){
                    var a = Integer.parseInt(mr.group(1));
                    var b = Integer.parseInt(mr.group(2));
                    return Optional.of(a * b);
                }
        }
        return Optional.empty();
    }
}
