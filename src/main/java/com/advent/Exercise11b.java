package com.advent;

import com.advent.util.Tuple2;

import java.util.HashMap;
import java.util.Map;

public class Exercise11b {
    private static final Map<Tuple2<Long,Integer>,Long> cache = new HashMap<>();

    public static long blinkNTimesAndSum(long[] stones, int nTimes) {
        cache.clear();
        long result = 0;

        for (long stone : stones) {
            result+= getNumStones(stone, nTimes);
        }

        return result;
    }

    public static long getNumStones(long stone, int nTimes){
        var tuple = new Tuple2<>(stone, nTimes);

        if (nTimes == 0) {
            cache.put(tuple, 1L);
            return 1;
        }
        else {
            long result;

            if (cache.containsKey(tuple)) {
                result = cache.get(tuple);
            }
            else{
                if (stone == 0) {
                    result = getNumStones(1, nTimes - 1);
                }
                else if(evenNumberOfDigits(stone)) {
                    String stoneString = Long.toString(stone);
                    int m = stoneString.length() / 2;

                    long left = Long.parseLong(stoneString.substring(0, m));
                    long right = Long.parseLong(stoneString.substring(m));

                    var tupleLeft = new Tuple2<>(left, nTimes-1);
                    var tupleRight= new Tuple2<>(right, nTimes-1);

                    var resultLeft = (cache.containsKey(tupleLeft)) ? cache.get(tupleLeft) : getNumStones(left,nTimes-1);
                    var resultRight = (cache.containsKey(tupleRight)) ? cache.get(tupleRight) : getNumStones(right,nTimes-1);

                    result = resultLeft + resultRight;
                }
                else {
                    result = getNumStones(stone * 2024, nTimes-1);
                }
                cache.put(tuple, result);
            }

            return result;
        }
    }

    private static boolean evenNumberOfDigits(Long stone) {
        return stone > 9 && stone.toString().length() % 2 == 0;
    }
}
