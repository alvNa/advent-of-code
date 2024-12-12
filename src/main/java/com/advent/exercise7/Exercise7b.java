package com.advent.exercise7;

import java.util.List;
import java.util.Map;

import static com.advent.exercise7.Exercise7.getEquationValue;

public class Exercise7b {

    public static long filterValidComplexEquationsAndSum(Map<Long,List<Integer>> equationsMap) {
        return equationsMap.entrySet().stream()
                .filter(entry -> isValidEquation(entry.getKey(), entry.getValue()))
                .map(Map.Entry::getKey)
                .reduce(0L, Long::sum);
    }

    private static boolean isValidEquation(Long result, List<Integer> operands) {
        var operators = operands.size()-1;
        int top = (int) Math.pow(3, operators);
        boolean found = false;
        int i=0;
        while (i < top && !found) {
            var candidates = toTernary(i,operators);
            found = getEquationValue(operands, operators, candidates) == result;
            i++;
        }

        return found;
    }

    private static String toTernary(int number, int length) {
        var ternary = Integer.toString(number, 3);
        return "0".repeat(length - ternary.length()) + ternary;
    }
}
