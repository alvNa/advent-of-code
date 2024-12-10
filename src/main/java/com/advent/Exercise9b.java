package com.advent;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.advent.Exercise9.decodeDiskMap;
import static com.advent.Exercise9.swapData;

public class Exercise9b {

    public static final String FREE_POSITION = ".";

    public static long fileSystemCompactCheckSum(String diskMap){
        var disk = decodeDiskMap(diskMap);
//        System.out.println(disk);
        System.out.println("Empty blocks: " + findEmptyBlocks(disk));
        var compressedDisk = compressDiskMapByBlock(disk, diskMap.length()/2);
//        System.out.println(compressedDisk);
        System.out.println("Empty blocks: " + findEmptyBlocks(compressedDisk));
        return calculateCheckSumWithSpaces(compressedDisk);
    }

    private static long calculateCheckSumWithSpaces(List<String> compressedDisk) {
        BigInteger sum = new BigInteger(String.valueOf(0L));
        long max = 0;
        for (int i=0; i<compressedDisk.size(); i++){
            var content = compressedDisk.get(i);

            if (!Objects.equals(content, FREE_POSITION)){
                var num = Integer.parseInt(content);
                long numValue = (long) i * num;
                if (numValue> max){
                    max = numValue;
                }
//                sum += numValue;
                sum = sum.add(new BigInteger(String.valueOf(numValue)));
            }
        }

        return sum.longValue();
    }

    private static List<String> compressDiskMapByBlock(List<String> disk, int maxIdNumber) {
        var compressDisk = new ArrayList<>(disk);

        var indexLeft = 0;
        var indexRight = compressDisk.size()-1;
        var currentIdNumber = maxIdNumber;

        while (currentIdNumber>0){
            var blockDataSize=0;

            //skipSpaces
            if (compressDisk.get(indexRight).equals(FREE_POSITION)){
                while (Objects.equals(compressDisk.get(indexRight), FREE_POSITION)){
                    indexRight--;
                }
            }

            int currentNum = Integer.parseInt(compressDisk.get(indexRight));
            if (currentNum == currentIdNumber){
                while (Objects.equals(compressDisk.get(indexRight), String.valueOf(currentIdNumber))){
                    blockDataSize++;
                    indexRight--;
                }
                currentIdNumber--;
            }
            else if (currentNum > currentIdNumber){
                //Skip Block
                while (!compressDisk.get(indexRight).equals(FREE_POSITION) && currentNum == Integer.parseInt(compressDisk.get(indexRight))){
                    indexRight--;
                }
            }
            else {
                currentIdNumber--;
            }

            var blockDataFreeSpace = 0;
            while (blockDataFreeSpace < blockDataSize && indexLeft < indexRight){
                if (Objects.equals(compressDisk.get(indexLeft), FREE_POSITION)){
                    blockDataFreeSpace++;
                }

                if (!Objects.equals(compressDisk.get(indexLeft), FREE_POSITION)){
                    blockDataFreeSpace=0;
                }
                indexLeft++;
            }

            if (blockDataFreeSpace >= blockDataSize){
                for (int i=0; i<blockDataSize; i++){
                    var positionRight = indexRight+1+i;
                    var positionLeft = indexLeft-blockDataSize+i;

                    swapData(compressDisk, positionRight, positionLeft);
                }
            }

            indexLeft = getFirstFreePosition(compressDisk);
        }

        return compressDisk;
    }

    private static int getFirstFreePosition(ArrayList<String> compressDisk) {
        int i=0;
        while (!Objects.equals(compressDisk.get(i), FREE_POSITION)){
            i++;
        }
        return i;
    }

    private static long findEmptyBlocks(List<String> disk){
        return disk.stream()
                .filter(s -> s.equals(FREE_POSITION))
                .count();
    }
}
