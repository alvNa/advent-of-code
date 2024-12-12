package com.advent.exercise7;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise7 {

    public static long filterValidEquationsAndSum(Map<Long,List<Integer>> equationsMap) {
        return equationsMap.entrySet().stream()
                .filter(entry -> isValidEquation(entry.getKey(), entry.getValue()))
                .map(Map.Entry::getKey)
                .reduce(0L, Long::sum);
    }

    private static boolean isValidEquation(Long result, List<Integer> operands) {
        var operators = operands.size()-1;
        int top = (int) Math.pow(2, operators);
        boolean found = false;
        int i=0;
        while (i < top && !found) {
            var candidates = toBinary(i,operators);
            found = getEquationValue(operands, operators, candidates) == result;
            i++;
        }

        return found;
    }

    private static long getEquationValue(List<Integer> operands, int operators, String candidates) {
        var operandsIndex =0;
        long equationValue =0;

        for (int j = 0; j< operators; j++) {
            long a,b;
            if (j == 0) {
                a = operands.get(operandsIndex);
                b = operands.get(operandsIndex +1);
                operandsIndex = operandsIndex +2;
            }
            else{
                a = equationValue;
                b = operands.get(operandsIndex);
                operandsIndex++;
            }

            var operator = candidates.charAt(j);
            if (operator == '0') {
                equationValue = a + b;
            }
            else if (operator == '1'){
                equationValue = a * b;
            }
        }
        return equationValue;
    }

    private static String toBinary(int number, int length) {
        var binary = Integer.toBinaryString(number);

        while (binary.length() < length) {
            binary = "0" + binary;
        }
        return binary;
    }

    private static String toOperators(String binaryOperators){
        return binaryOperators.chars()
                .mapToObj(c -> (char) c)
                .map(c -> c == '0' ? "+" : "*")
                .collect(Collectors.joining());
    }

    private static void printEquation(Long result, List<Integer> operands, String candidates) {
        System.out.println("Found: " + result);
        System.out.println("Operands: " + operands);
        System.out.println("Operators: " + toOperators(candidates));
        System.out.println("---");
    }
}
