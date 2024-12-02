package com.advent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
}
