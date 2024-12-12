package com.advent;

import com.advent.exercise7.Exercise7;
import com.advent.exercise7.InputProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class Exercise7Test {

    @Test
    void filterValidEquationsAndSumTest() throws IOException {
        var equationMap = InputProcessor.processInput(Path.of("src/test/resources/exercise7/equations.txt"));
        var result = Exercise7.filterValidEquationsAndSum(equationMap);
        Assertions.assertEquals(3749L, result);
    }

    @Test
    void filterValidEquationsAndSumLargeTest() throws IOException {
        var equationMap = InputProcessor.processInput(Path.of("src/test/resources/exercise7/equations-large.txt"));
        var result = Exercise7.filterValidEquationsAndSum(equationMap);
        System.out.println("Sum Equationss: " + result);
        //33786032700 That's not the right answer; your answer is too low.
        Assertions.assertEquals(1289579105366L, result);
    }
}
