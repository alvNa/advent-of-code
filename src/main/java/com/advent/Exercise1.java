package com.advent;

import com.google.common.collect.Streams;

import java.util.List;

public class Exercise1 {

    public static int totalDistance(List<Integer> left, List<Integer> right){

        return Streams.zip(left.stream().sorted(), right.stream().sorted(),
                        (l, r) -> Math.abs(r-l))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
