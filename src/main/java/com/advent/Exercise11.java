package com.advent;

import com.advent.util.Tuple;

import java.util.ArrayList;
import java.util.List;

public class Exercise11 {

    public static long blinkNTimesAndSum(List<Long> stones, int nTimes) {
        List<Long> newStones=stones;

        for (int i = 0; i < nTimes; i++) {
            newStones = blink(newStones);
        }

        return newStones.size();
    }

    public static List<Long> blink(List<Long> stones) {
        List<Long> transformedStones = new ArrayList<>();

        for(long stone: stones){
            var transformation = applyRule(stone);
            switch (transformation) {
                case Long newStone:    transformedStones.add(newStone); break;
                case Tuple tuple:  {
                    transformedStones.add((long) tuple.x());
                    transformedStones.add((long) tuple.y());
                } break;
                default: break;
            }
        }

        return transformedStones;
    }

    private static Object applyRule(Long stone) {
        var stoneString = stone.toString();
        if (stone == 0) {
            return 1L;
        }
        else if (stoneString.length()>1 && stoneString.length() % 2 == 0) {
            var index = stoneString.length() / 2;
            var leftStr = stoneString.substring(0, index);
            var rightStr = stoneString.substring(index, stone.toString().length());
            return new Tuple<>(Long.parseLong(leftStr), Long.parseLong(rightStr));
        }
        else {
            return stone * 2024;
        }
    }
}
