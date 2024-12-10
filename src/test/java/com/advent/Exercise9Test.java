package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Exercise9Test {

    @Test
    void fileSystemCheckSumTest() throws IOException {
        //var diskMap = "2333133121414131402";
        var diskMap = Files.readString(Path.of("src/test/resources/exercise9/disk-map.txt"));
        var result = Exercise9.fileSystemCheckSum(diskMap);
        Assertions.assertEquals(1928, result);
    }

    @Test
    void fileSystemCheckSumRange1Test() {
        var diskMap = "233313312141413140211";
        var result = Exercise9.fileSystemCheckSum(diskMap);
        Assertions.assertEquals(2132, result);
    }

    @Test
    void fileSystemCheckSumRange2Test() {
        var diskMap = "2333133121414131402332233";
        var result = Exercise9.fileSystemCheckSum(diskMap);
        Assertions.assertEquals(4024, result);
    }

    @Test
    void fileSystemCheckSumLargeTest() throws IOException {
        var diskMap = Files.readString(Path.of("src/test/resources/exercise9/disk-map-large.txt"));
        var result = Exercise9.fileSystemCheckSum(diskMap);
        System.out.println("CheckSum: " + result);
        //CheckSum: 181189696 That's not the right answer; your answer is too low
        //CheckSum: 282055995 That's not the right answer; your answer is too low.
        // 6356833654075 -> OK
        Assertions.assertTrue(result>0);
        Assertions.assertEquals(6356833654075L, result);
    }

}
