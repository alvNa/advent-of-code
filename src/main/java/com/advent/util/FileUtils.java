package com.advent.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static final String SEPARATOR = " ";

    public static List<Integer> getIntListFromFile(String source) throws IOException {
        return Files.readAllLines(Path.of(source)).stream()
                .map(Integer::valueOf)
                .toList();
    }

    public static List<List<Integer>> getRowIntListFromFile(String source) throws IOException {
        return Files.readAllLines(Path.of(source)).stream()
                .map(FileUtils::toIntList)
                .toList();
    }

    private static List<Integer> toIntList(String listStr) {
        return Arrays.stream(listStr.split(SEPARATOR))
                .map(Integer::valueOf)
                .toList();
    }
}
