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
}
