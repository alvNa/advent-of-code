package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Exercise3Test {

    @Test
    void multiplicationsSumTest(){
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        var result = Exercise3.multiplicationsSum(input);
        //161 (2*4 + 5*5 + 11*8 + 8*5)
        Assertions.assertEquals(161,result);
    }

    @Test
    void multiplicationsSumLargeExampleTest() throws IOException {
        var program = Files.readString(Path.of("src/test/resources/exercise3/program.txt"));
        var result = Exercise3.multiplicationsSum(program);
        System.out.println("Multiplications added: " + result);
        Assertions.assertTrue(result >0);
        //190604937 That's the right answer!
    }
}
