package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.advent.util.FileUtils.getRowIntListFromFile;


class Exercise2Test {

    @Test
    void numReportsSaveTest() throws IOException {
        var rowList = getRowIntListFromFile("src/test/resources/exercise2/list-short.csv");
        var numReportSave = Exercise2.numReportsSave(rowList);
        Assertions.assertEquals(2, numReportSave);
    }

    @Test
    void numReportsSaveLargeExampleTest() throws IOException {
        var rowList = getRowIntListFromFile("src/test/resources/exercise2/list-large.csv");
        var numReportSave = Exercise2.numReportsSave(rowList);
        System.out.println("Number of reports saved: " + numReportSave);
        Assertions.assertTrue(numReportSave >0);
        //557 494 too high
        //486 -OK
    }

    @Test
    void numReportsSaveWithErrorTolerationTest() throws IOException {
        var rowList = getRowIntListFromFile("src/test/resources/exercise2/list-short.csv");
        var numReportSave = Exercise2.numReportsSafeWithToleration(rowList);
        Assertions.assertEquals(4, numReportSave);
    }

    @Test
    void numReportsSaveWithErrorTolerationCornerCase1Test() {
        List<Integer> l1 = new ArrayList<>(List.of(1, 5, 6, 7));
        List<Integer> l2 = new ArrayList<>(List.of(2, 1, 5, 7));
        var rowList = List.of(l1, l2);
        var numReportSave = Exercise2.numReportsSafeWithToleration(rowList);
        Assertions.assertEquals(2, numReportSave);
    }

    @Test
    void numReportsSaveWithErrorTolerationLargeExampleTest() throws IOException {
        var rowList = getRowIntListFromFile("src/test/resources/exercise2/list-large.csv");
        var numReportSave = Exercise2.numReportsSafeWithToleration(rowList);
        System.out.println("Number of reports saved: " + numReportSave);
        Assertions.assertTrue(numReportSave >0);
        //519 That's not the right answer; your answer is too low
        //622 your answer is too high
        //540 That's the right answer!
    }
}
