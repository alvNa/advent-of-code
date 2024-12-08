package com.advent;

import com.advent.util.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise5Test {

    @Test
    void pageValid(){
        var page = List.of(75,97,47,61,53);
        Map<Integer, List<Integer>> rules = new HashMap<>();
        rules.put(97,List.of(75));

        var result = Exercise5.pagesValid(rules, page);
        Assertions.assertFalse(result);
    }

    @Test
    void middlePageSumFromCorrectlyOrder() throws IOException {
        var program = Files.readString(Path.of("src/test/resources/exercise5/program.txt"));
        var segments = program.split("\n\n");
        var rulesStr = segments[0];
        var pagesStr = segments[1];

        Map<Integer, List<Integer>> rules = Exercise5.getRules(rulesStr);
        List<List<Integer>> pageList = Exercise5.getPageList(pagesStr);

        var result = Exercise5.middlePageSumFromCorrectlyOrder(rules,pageList);
        Assertions.assertEquals(143,result);
    }

    @Test
    void middlePageSumFromCorrectlyOrderLarge() throws IOException {
        var program = Files.readString(Path.of("src/test/resources/exercise5/program-large.txt"));
        var segments = program.split("\n\n");
        var rulesStr = segments[0];
        var pagesStr = segments[1];

        Map<Integer, List<Integer>> rules = Exercise5.getRules(rulesStr);
        List<List<Integer>> pageList = Exercise5.getPageList(pagesStr);

        var result = Exercise5.middlePageSumFromCorrectlyOrder(rules,pageList);
        System.out.println(result);
        Assertions.assertTrue(result>0);
    }

    @Test
    void middlePageSumFromFixedOrderPages() throws IOException {
        var program = Files.readString(Path.of("src/test/resources/exercise5/program.txt"));
        var segments = program.split("\n\n");
        var rulesStr = segments[0];
        var pagesStr = segments[1];

        Map<Integer, List<Integer>> rules = Exercise5.getRules(rulesStr);
        List<List<Integer>> pageList = Exercise5.getPageList(pagesStr);

        var result = Exercise5.middlePageSumFromFixedOrderPages(rules,pageList);
        Assertions.assertEquals(123,result);
    }

    @Test
    void middlePageSumFromFixedOrderPagesLarge() throws IOException {
        var program = Files.readString(Path.of("src/test/resources/exercise5/program-large.txt"));
        var segments = program.split("\n\n");
        var rulesStr = segments[0];
        var pagesStr = segments[1];

        Map<Integer, List<Integer>> rules = Exercise5.getRules(rulesStr);
        List<List<Integer>> pageList = Exercise5.getPageList(pagesStr);

        var result = Exercise5.middlePageSumFromFixedOrderPages(rules,pageList);
        System.out.println(result);
        Assertions.assertTrue(result>0);
    }


}
