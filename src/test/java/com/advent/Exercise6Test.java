package com.advent;

import com.advent.exercise6.Exercise6;
import com.advent.util.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

public class Exercise6Test {

    @Test
    void findNumGuardPositionsTest() throws IOException {
        var matrix = FileUtils.loadMatrix(Path.of("src/test/resources/exercise6/initial-map.txt"));
        var result = Exercise6.numGuardPositions(matrix);
        Assertions.assertEquals(41,result);
    }

    @Test
    void findNumGuardPositionsLargeTest() throws IOException {
        var matrix = FileUtils.loadMatrix(Path.of("src/test/resources/exercise6/initial-map-large.txt"));
        var result = Exercise6.numGuardPositions(matrix);
        System.out.println("NumGuardPositions: "+result);
        //NumGuardPositions: 4515 That's the right answer!
        Assertions.assertTrue(result>0);
    }
}
