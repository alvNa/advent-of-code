package com.advent;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> left = Files.readAllLines(Path.of("src/test/resources/exercise1/left.txt")).stream()
                .map(Integer::valueOf)
                .toList();

        List<Integer> right = Files.readAllLines(Path.of("src/test/resources/exercise1/right.txt")).stream()
                .map(Integer::valueOf)
                .toList();
        var result = Exercise1.totalDistance(left, right);
        Assertions.assertTrue(result>0);
    }
}
