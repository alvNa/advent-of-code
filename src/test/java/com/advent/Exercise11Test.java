package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static com.advent.util.FileUtils.laodLongList;
import static com.advent.util.FileUtils.loadArrayLong;

class Exercise11Test {
    @Test
    void sumOfTrailHeadsScoresTest() throws IOException {
        var stones = laodLongList(Path.of("src/test/resources/exercise11/stones.txt"));
        var result = Exercise11.blinkNTimesAndSum(stones, 25);
        Assertions.assertEquals(55312, result);
    }

    @Test
    void sumOfTrailHeadsScoresLarge25Test() throws IOException {
        var stones = laodLongList(Path.of("src/test/resources/exercise11/stones-large.txt"));
        var result = Exercise11.blinkNTimesAndSum(stones, 25);
        Assertions.assertEquals(217812, result);
    }

    @Test
    void sumOfTrailHeadsScores6bTest() throws IOException {
        var stones = loadArrayLong(Path.of("src/test/resources/exercise11/stones.txt"));
        var result = Exercise11b.blinkNTimesAndSum(stones, 6);
        Assertions.assertEquals(22, result);
    }

    @Test
    void sumOfTrailHeadsScores25bTest() throws IOException {
        var stones = loadArrayLong(Path.of("src/test/resources/exercise11/stones.txt"));
        var result = Exercise11b.blinkNTimesAndSum(stones, 25);
        Assertions.assertEquals(55312, result);
    }

    @Test
    void sumOfTrailHeadsScores25bLargeTest() throws IOException {
        var stones = loadArrayLong(Path.of("src/test/resources/exercise11/stones-large.txt"));
        var result = Exercise11b.blinkNTimesAndSum(stones, 25);
        Assertions.assertEquals(217812, result);
    }

    @Test
    void sumOfTrailHeadsScores75bLargeTest() throws IOException {
        var stones = loadArrayLong(Path.of("src/test/resources/exercise11/stones-large.txt"));
        var result = Exercise11b.blinkNTimesAndSum(stones, 75);
        System.out.println("75 blinks and sum: " + result);
        //75 blinks and sum: 259112729857522
        Assertions.assertTrue(result>0);
    }
}
