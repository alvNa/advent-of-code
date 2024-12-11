package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static com.advent.util.FileUtils.laodLongList;

class Exercise11Test {
    @Test
    void sumOfTrailHeadsScoresTest() throws IOException {
        var topographicMap = laodLongList(Path.of("src/test/resources/exercise11/stones.txt"));
        var result = Exercise11.blinkNTimesAndSum(topographicMap, 25);
        Assertions.assertEquals(55312, result);
    }

    @Test
    void sumOfTrailHeadsScoresLargeTest() throws IOException {
        var topographicMap = laodLongList(Path.of("src/test/resources/exercise11/stones-large.txt"));
        var result = Exercise11.blinkNTimesAndSum(topographicMap, 25);
        Assertions.assertEquals(217812, result);
    }
}
