package com.advent.exercise7;

import com.advent.util.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputProcessor {

    public static Map<Long,List<Integer>> processInput(Path filePath) throws IOException {
        return Files.readAllLines(filePath).stream()
                .map(InputProcessor::processLine)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    private static Map.Entry<Long, List<Integer>> processLine(String line) {
        var equation = line.split(":");
        var key = Long.parseLong(equation[0]);
        var value = FileUtils.toIntList(equation[1]);
        return new AbstractMap.SimpleEntry<>(key,value);
    }
}
