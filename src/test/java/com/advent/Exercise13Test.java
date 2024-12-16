package com.advent;

import com.advent.exercise13.Exercise13;
import com.advent.exercise13.InputProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

public class Exercise13Test {

    @Test
    void minTokenForAllPricesTest() throws IOException {
        var clawMachine = InputProcessor.processInput(Path.of("src/test/resources/exercise13/puzzle-input.txt"));
        Exercise13.minTokenForAllPrices(clawMachine);
        Assertions.assertEquals(0,0);
    }
}
