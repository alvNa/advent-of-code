package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise3Test {

    @Test
    void multiplicationsSumTest(){
        var result = Exercise3.multiplicationsSum("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");
        //161 (2*4 + 5*5 + 11*8 + 8*5)
        Assertions.assertEquals(161,result);
    }
}
