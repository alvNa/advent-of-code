package com.advent.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
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
        return new ArrayList<>(Arrays.stream(listStr.split(SEPARATOR))
                .map(Integer::valueOf)
                .toList());
    }

    public static List<Long> laodLongList(Path filePath) throws IOException {
        return new ArrayList<>(Arrays.stream(Files.readString(filePath).split(SEPARATOR))
                .map(Long::valueOf)
                .toList());
    }

    public static LinkedList<Long> laodLongLinkedList(Path filePath) throws IOException {
        return new LinkedList<>(Arrays.stream(Files.readString(filePath).split(SEPARATOR))
                .map(Long::valueOf)
                .toList());
    }

    public static char[][] loadMatrix(Path filePath) throws IOException {
        return Files.readAllLines(filePath).stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static int[][] loadIntMatrix(Path filePath) throws IOException {
        return Files.readAllLines(filePath).stream()
                .map(FileUtils::toArrayInt)
                 .toArray(int[][]::new);
    }

    private static int[] toArrayInt(String str) {
        int[] array = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            array[i] = str.charAt(i) - '0';
        }
        return array;
    }

    public static long[] loadArrayLong(Path filePath) throws IOException {
        var arrayStr = Files.readString(filePath).split(SEPARATOR);
        long[] array = new long[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            array[i] = Long.parseLong(arrayStr[i]);
        }
        return array;
    }
}
