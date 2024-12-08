package com.advent;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise5 {
    private Map<Integer, List<Integer>> rules;

    public static int middlePageSumFromFixedOrderPages(Map<Integer, List<Integer>> rules, List<List<Integer>> pageList){
        return pageList.stream()
                .filter(pages -> !pagesValid(rules, pages))
                .map(pages -> fixOrder(rules,pages))
                .map(Exercise5::middlePageNum)
                .reduce(0, Integer::sum);
    }

    private static List<Integer> fixOrder(Map<Integer, List<Integer>> rules, List<Integer> pages) {
        var result = new ArrayList<>(pages);

        while (!pagesValid(rules, result)){

            for (int i=0;i<result.size();i++){
                var page = result.get(i);

                if (rules.containsKey(page)) {
                    var constraints = rules.get(page);

                    for(int j=i-1; j>=0; j--){
                        var pageLeft = result.get(j);

                        if (constraints.contains(pageLeft)) {
                            result.remove(j);
                            result.add(i,pageLeft);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static int middlePageSumFromCorrectlyOrder(Map<Integer, List<Integer>> rules, List<List<Integer>> pageList){
        return pageList.stream()
                .filter(pages -> pagesValid(rules, pages))
                .map(Exercise5::middlePageNum)
                .reduce(0, Integer::sum);
    }

    private static int middlePageNum(List<Integer> pages) {
        int middle = pages.size() / 2;
        return pages.get(middle);
    }

    public static boolean pagesValid(Map<Integer, List<Integer>> rules, List<Integer> pages) {

        for (int i=0; i<pages.size(); i++){
            var page = pages.get(i);
            if (rules.containsKey(page)){
                var constraints = rules.get(page);

                for(int j=i-1; j>=0; j--){
                    if (constraints.contains(pages.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Map<Integer, List<Integer>> getRules(String rulesStr) {
        var pattern = Pattern.compile("(\\d+)\\|(\\d+)");
        var scanner = new Scanner(rulesStr);
        Map<Integer, List<Integer>> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));

                if (map.containsKey(x)){
                    map.get(x).add(y);
                }
                else{
                    List<Integer> l = new ArrayList();
                    l.add(y);
                    map.put(x,l);
                }
            }
        }

        return map;
    }

    public static List<List<Integer>> getPageList(String pagesStr) {
        return pagesStr.lines().map(line -> Arrays.stream(line.split(","))
                .map(Integer::parseInt)
                .toList())
                .toList();
    }
}
