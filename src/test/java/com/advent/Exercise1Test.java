package com.advent;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Exercise1Test {

    @Test
    public void calcDistanceTest(){
        List<Integer> left = List.of(3,4,2,1,3,3);
        List<Integer> right = List.of(4,3,5,3,9,3);
        var result = Exercise1.totalDistance(left, right);
        Assertions.assertEquals(11,result);
    }

    @Test
    public void calcDistanceTestFromFile() throws IOException {
        List<Integer> left = getLeftListFromFile();
        List<Integer> right = getRightListFromFile();

        var result = Exercise1.totalDistance(left, right);
        Assertions.assertTrue(result>0);
    }

    @Test
    public void calcSimilarityTest(){
        List<Integer> left = List.of(3,4,2,1,3,3);
        List<Integer> right = List.of(4,3,5,3,9,3);
        var result = Exercise1.totalSimilarity(left, right);
        Assertions.assertEquals(31,result);
    }

    @Test
    public void calcDistancealcSimilarityTestFromFile() throws IOException {
        List<Integer> left = getLeftListFromFile();
        List<Integer> right = getRightListFromFile();

        var result = Exercise1.totalSimilarity(left, right);
        Assertions.assertTrue(result>0);
    }

    private static List<Integer> getRightListFromFile() throws IOException {
        return Files.readAllLines(Path.of("src/test/resources/exercise1/right.txt")).stream()
                .map(Integer::valueOf)
                .toList();
    }

    private static List<Integer> getLeftListFromFile() throws IOException {
        return Files.readAllLines(Path.of("src/test/resources/exercise1/left.txt")).stream()
                .map(Integer::valueOf)
                .toList();
    }
}
