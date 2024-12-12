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

    public static long getEquationValue(List<Integer> operands, int operators, String candidates) {
        var operandsIndex =0;
        long equationValue =0;

        for (int j = 0; j< operators; j++) {
            Long a,b;
            if (j == 0) {
                a = operands.get(operandsIndex).longValue();
                b = operands.get(operandsIndex +1).longValue();
                operandsIndex = operandsIndex +2;
            }
            else{
                a = equationValue;
                b = operands.get(operandsIndex).longValue();
                operandsIndex++;
            }

            var operator = candidates.charAt(j);
            equationValue = calculateOperation(operator, a, b);
        }
        return equationValue;
    }

    private static long calculateOperation(char operator, Long a, Long b) {
        long result =0;

        if (operator == '0') {
            result = a + b;
        }
        else if (operator == '1'){
            result = a * b;
        }
        else if (operator == '2'){
            String newValue = a.toString() + b.toString();
            result = Long.parseLong(newValue);
        }
        return result;
    }

    private static String toBinary(int number, int length) {
        var binary = Integer.toBinaryString(number);

        while (binary.length() < length) {
            binary = "0" + binary;
        }
        return binary;
    }

    private static String toOperators(String ternaryOperators){
        return ternaryOperators.chars()
                .mapToObj(c -> (char) c)
                .map(c -> switch (c){
                    case 0: yield "+";
                    case 1: yield "*";
                    case 2: yield "||";
                    default:
                        throw new IllegalStateException("Unexpected value: " + c);
                })
                .collect(Collectors.joining());
    }
    private static void printEquation(Long result, List<Integer> operands, String candidates) {
        System.out.println("Found: " + result);
        System.out.println("Operands: " + operands);
        System.out.println("Operators: " + toOperators(candidates));
        System.out.println("---");
    }
}
