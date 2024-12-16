package com.advent.exercise13;

import java.util.LinkedHashSet;
import java.util.List;

public class Exercise13 {

    public static int minTokenForAllPrices(List<ClawMachine> clawMachines){
        return clawMachines.stream()
                .map(Exercise13::calculateMin)
                .filter(i -> i != Integer.MAX_VALUE)
                .reduce(0, Integer::sum);
    }

    private static int calculateMin(ClawMachine machine) {
        LinkedHashSet<ClawState> queue = new LinkedHashSet<>();
        queue.add(new ClawState(0, 0, 0));
        var minNumTokens = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            ClawState current = queue.removeFirst();

            int currentX = current.aButtonPress() * machine.aX() + current.bButtonBPress() * machine.bX();
            int currentY = current.aButtonPress() * machine.aY() + current.bButtonBPress() * machine.bY();

            if (currentX == machine.prizeX() && currentY == machine.prizeY()) {
                minNumTokens=Math.min(current.tokens(), minNumTokens);
            }

            if (current.aButtonPress() < 100 && minNumTokens > current.tokens() + 3) {
                queue.add(new ClawState(current.aButtonPress() + 1, current.bButtonBPress(), current.tokens() + 3));
            }
            if (current.bButtonBPress() < 100 && minNumTokens > current.tokens() + 1) {
                queue.add(new ClawState(current.aButtonPress(), current.bButtonBPress() + 1, current.tokens() + 1));
            }
        }

        return minNumTokens;
    }

}
