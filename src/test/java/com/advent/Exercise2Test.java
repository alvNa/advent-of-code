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
}
