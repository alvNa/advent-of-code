package com.advent;

import com.advent.exercise10.Exercise10;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static com.advent.util.FileUtils.loadIntMatrix;

class Exercise10Test {
    @Test
    void sumOfTrailHeadsScoresTest() throws IOException {
        var topographicMap = loadIntMatrix(Path.of("src/test/resources/exercise10/topographic-map.txt"));
        var result = Exercise10.sumOfTrailHeadsScores(topographicMap);
        Assertions.assertEquals(36, result);
    }

    @Test
    void sumOfTrailHeadsScoresLargeTest() throws IOException {
        var topographicMap = loadIntMatrix(Path.of("src/test/resources/exercise10/topographic-map-large.txt"));
        var result = Exercise10.sumOfTrailHeadsScores(topographicMap);
        System.out.println("sum Trails Scores: " + result);
        Assertions.assertTrue(result >0);
    }

    @Test
    void sumOfTrailHeadsRatingsTest() throws IOException {
        var topographicMap = loadIntMatrix(Path.of("src/test/resources/exercise10/topographic-map.txt"));
        var result = Exercise10.sumOfTrailHeadsRatings(topographicMap);
        Assertions.assertEquals(81, result);
    }

    @Test
    void sumOfTrailHeadsRatingsLargeTest() throws IOException {
        var topographicMap = loadIntMatrix(Path.of("src/test/resources/exercise10/topographic-map-large.txt"));
        var result = Exercise10.sumOfTrailHeadsRatings(topographicMap);
        System.out.println("sum Ratings Scores: " + result);
        //sum Ratings Scores: 1494
        Assertions.assertEquals(1494, result);
    }
}
