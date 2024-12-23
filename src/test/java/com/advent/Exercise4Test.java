package com.advent;

import com.advent.util.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class Exercise4Test {

    @Test
    void findXMASTest() throws IOException {
        var matrix = FileUtils.loadMatrix(Path.of("src/test/resources/exercise4/matrix.txt"));
        var result = Exercise4.findXMAS(matrix);
        Assertions.assertEquals(18,result);
    }

    @Test
    void findXMASLargeTest() throws IOException {
        var matrix = FileUtils.loadMatrix(Path.of("src/test/resources/exercise4/matrix-large.txt"));
        var result = Exercise4.findXMAS(matrix);
        System.out.println("XMAS found: " + result);
        //XMAS found: 2332 is too low
        //2500 your answer is too high
        //2348 That's not the right answer
        //XMAS found: 2364
        //XMAS found: 2358 That's the right answer!
        Assertions.assertEquals(2358, result);
    }

    @Test
    void findCrossMASTest() throws IOException {
        var matrix = FileUtils.loadMatrix(Path.of("src/test/resources/exercise4/matrix.txt"));
        var result = Exercise4b.findCrossMAS(matrix);
        Assertions.assertEquals(9,result);
    }

    @Test
    void findCrossMASLargeTest() throws IOException {
        var matrix = FileUtils.loadMatrix(Path.of("src/test/resources/exercise4/matrix-large.txt"));
        var result = Exercise4b.findCrossMAS(matrix);
        System.out.println("Cross MAS: " + result);
        //Cross MAS: 1737: That's the right answer!
        Assertions.assertTrue(result>0);
    }
}
