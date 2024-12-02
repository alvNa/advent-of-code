package com.advent;


import com.advent.util.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Exercise1Test {

    @Test
    void calcDistanceTest(){
        List<Integer> left = List.of(3,4,2,1,3,3);
        List<Integer> right = List.of(4,3,5,3,9,3);
        var result = Exercise1.totalDistance(left, right);
        Assertions.assertEquals(11,result);
    }

    @Test
    void calcDistanceTestFromFile() throws IOException {
        List<Integer> left = FileUtils.getIntListFromFile("src/test/resources/exercise1/left.txt");
        List<Integer> right = FileUtils.getIntListFromFile("src/test/resources/exercise1/right.txt");

        var result = Exercise1.totalDistance(left, right);
        System.out.println("Total Distance: " + result);
        Assertions.assertTrue(result>0);
    }

    @Test
    void calcSimilarityTest(){
        List<Integer> left = List.of(3,4,2,1,3,3);
        List<Integer> right = List.of(4,3,5,3,9,3);
        var result = Exercise1.totalSimilarity(left, right);
        Assertions.assertEquals(31,result);
    }

    @Test
    void calcDistanceCalcSimilarityTestFromFile() throws IOException {
        List<Integer> left = FileUtils.getIntListFromFile("src/test/resources/exercise1/left.txt");
        List<Integer> right = FileUtils.getIntListFromFile("src/test/resources/exercise1/right.txt");

        var result = Exercise1.totalSimilarity(left, right);
        System.out.println("Similarity: " + result);
        Assertions.assertTrue(result>0);
    }
}
