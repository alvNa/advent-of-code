package com.advent.exercise13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class InputProcessor {
    public static List<ClawMachine> processInput(Path filePath) throws IOException {
        var clawLines = Files.readString(filePath).split("\n\n");

        return Arrays.stream(clawLines)
                .map(InputProcessor::processClawMachine)
                .toList();
    }

    private static ClawMachine processClawMachine(String clawLines) {
        var cm = new ClawMachine();
        var lines = clawLines.split("\n");

        cm.buttonA=processLine(lines[0],"\\+","+");
        cm.buttonB=processLine(lines[1], "\\+","+");
        cm.price=processLine(lines[2],"\\=","=");

        return cm;
    }

    private static List<BinaryOp> processLine(String line, String opPattern, String operator) {
        var equation = line.split(":")[1].split(",");
        var left = equation[0].split(opPattern);
        var right = equation[1].split(opPattern);
        var binaryOp1 = new BinaryOp(left[0],Integer.parseInt(left[1]), operator);
        var binaryOp2 = new BinaryOp(right[0],Integer.parseInt(right[1]), operator);

        return List.of(binaryOp1,binaryOp2);
    }
}
