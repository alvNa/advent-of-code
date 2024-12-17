package com.advent.exercise13;

import com.advent.util.Tuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class InputProcessor {

    public static List<ClawMachine> processInput(Path filePath) throws IOException {
        var clawLines = Files.readString(filePath).split("\r\n\r\n");

        return Arrays.stream(clawLines)
                .map(InputProcessor::processClawMachine)
                .toList();
    }

    public static List<ClawMachine2> processInput2(Path filePath) throws IOException {
        var clawLines = Files.readString(filePath).split("\r\n\r\n");

        return Arrays.stream(clawLines)
                .map(InputProcessor::processClawMachine2)
                .toList();
    }

    private static ClawMachine2 processClawMachine2(String clawLines) {
        var lines = clawLines.split("\r\n");

        var buttonA=processLine(lines[0],"\\+");
        var buttonB=processLine(lines[1], "\\+");
        var price=processLine(lines[2],"\\=");

        return new ClawMachine2(buttonA.x(),buttonA.y(),
                buttonB.x(),buttonB.y(),
                price.x()+10000000000000L,price.y()+10000000000000L);
    }


    private static ClawMachine processClawMachine(String clawLines) {
        var lines = clawLines.split("\r\n");

        var buttonA=processLine(lines[0],"\\+");
        var buttonB=processLine(lines[1], "\\+");
        var price=processLine(lines[2],"\\=");

        return new ClawMachine(buttonA.x(),buttonA.y(),
                buttonB.x(),buttonB.y(),
                price.x(),price.y());
    }

    private static Tuple<Integer> processLine(String line, String opPattern) {
        var equation = line.split(":")[1].split(",");
        var left = equation[0].split(opPattern);
        var right = equation[1].split(opPattern);
        return new Tuple<>(Integer.parseInt(left[1]),Integer.parseInt(right[1]));
    }
}