package com.advent;

import com.google.common.collect.Streams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Exercise1 {

    public static int totalDistance(List<Integer> left, List<Integer> right){

        return Streams.zip(left.stream().sorted(), right.stream().sorted(),
                        (l, r) -> Math.abs(r-l))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int totalSimilarity(List<Integer> left, List<Integer> right){
        var simMap = getSimilarityMap(left,right);

        return left.stream()
                .map(x -> x * simMap.get(x))
                .filter(x -> x > 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int findSimilarity(Integer x, List<Integer> right){
        return (int) right.stream().filter(x::equals).count();
    }

    public static Map<Integer,Integer> getSimilarityMap(List<Integer> left, List<Integer> right){
        Map<Integer,Integer> simMap = new HashMap<>();

        left.forEach(l -> {
            if (!simMap.containsKey(l)){
                simMap.put(l,findSimilarity(l,right));
            }
        });
        return simMap;
    }
}
