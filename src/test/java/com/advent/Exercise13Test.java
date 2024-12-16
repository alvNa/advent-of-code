package com.advent;

import com.advent.exercise13.Exercise13;
import com.advent.exercise13.InputProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

class Exercise13Test {

    @Test
    void minTokenForAllPricesTest() throws IOException {
        var clawMachines = InputProcessor.processInput(Path.of("src/test/resources/exercise13/puzzle-input.txt"));
        var result = Exercise13.minTokenForAllPrices(clawMachines);
        Assertions.assertEquals(480,result);
    }

    @Test
    void minTokenForAllPricesLargeTest() throws IOException {
        var clawMachines = InputProcessor.processInput(Path.of("src/test/resources/exercise13/puzzle-input-large.txt"));
        var result = Exercise13.minTokenForAllPrices(clawMachines);
        Assertions.assertEquals(35997,result);
    }
}
