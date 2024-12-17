package com.advent.exercise13;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Exercise13b {

    public static long minTokenForAllPricesWithDeviation(List<ClawMachine2> clawMachines){
        return clawMachines.stream()
                .map(Exercise13b::solveMachine)
                .filter(i -> i != Integer.MAX_VALUE)
                .reduce(0, Integer::sum);
    }

    private static long calculateMin(ClawMachine2 machine) {
        LinkedHashSet<ClawState2> queue = new LinkedHashSet<>();
        queue.add(new ClawState2(0, 0, 0));
        var minNumTokens = Long.MAX_VALUE;

        while (!queue.isEmpty()) {
            ClawState2 current = queue.removeFirst();

            int currentX = current.aButtonPress() * machine.aX() + current.bButtonBPress() * machine.bX();
            int currentY = current.aButtonPress() * machine.aY() + current.bButtonBPress() * machine.bY();

            if (currentX == machine.prizeX() && currentY == machine.prizeY()) {
                minNumTokens=Math.min(current.tokens(), minNumTokens);
            }

            if (machine.prizeX()%2==0 && machine.prizeY()%2==0){

                if (minNumTokens > 2 * (current.tokens() + 3)) {
                    queue.add(new ClawState2(current.aButtonPress() + 1, current.bButtonBPress(), 2*(current.tokens() + 3)));
                }
                if (minNumTokens > 2* (current.tokens() + 1)) {
                    queue.add(new ClawState2(current.aButtonPress(), current.bButtonBPress() + 1, 2*(current.tokens() + 1)));
                }

            }

            if (minNumTokens > current.tokens() + 3) {
                queue.add(new ClawState2(current.aButtonPress() + 1, current.bButtonBPress(), current.tokens() + 3));
            }
            if (minNumTokens > current.tokens() + 1) {
                queue.add(new ClawState2(current.aButtonPress(), current.bButtonBPress() + 1, current.tokens() + 1));
            }
        }

        return minNumTokens;
    }

    private static int solveMachine(ClawMachine2 machine) {
        return solve(machine, 0, 0, 0, new HashMap<>());
    }

    private static int solve(ClawMachine2 machine, int aPresses, int bPresses, int tokens, Map<String, Integer> memo) {
        long currentX = aPresses * machine.aX() + bPresses * machine.bX();
        long currentY = aPresses * machine.aY() + bPresses * machine.bY();

        if (currentX == machine.prizeX() && currentY == machine.prizeY()) {
            return tokens;
        }

        String key = aPresses + "," + bPresses;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int minTokens = Integer.MAX_VALUE;

        if (currentX <= machine.prizeX() && currentY <= machine.prizeY()) {
            minTokens = Math.min(minTokens, solve(machine, aPresses + 1, bPresses, tokens + 3, memo));
            minTokens = Math.min(minTokens, solve(machine, aPresses, bPresses + 1, tokens + 1, memo));
        }

        memo.put(key, minTokens);
        return minTokens;
    }

}
