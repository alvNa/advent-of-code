package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise4Test {

    @Test
    void aSumTest(){
        String input = "";
        var result = Exercise4.a(input);
        Assertions.assertEquals(0,result);
    }
}
