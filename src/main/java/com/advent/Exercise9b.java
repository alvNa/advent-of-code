package com.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.advent.Exercise9.decodeDiskMap;
import static com.advent.Exercise9.swapData;

public class Exercise9b {

    public static final String FREE_POSITION = ".";

    public static long fileSystemCompactCheckSum(String diskMap){
        var disk = decodeDiskMap(diskMap);
        // System.out.println(disk);
        var compressedDisk = compressDiskMapByBlock(disk, diskMap.length()/2);
        // System.out.println(compressedDisk);
        return calculateCheckSumWithSpaces(compressedDisk);
    }

    private static long calculateCheckSumWithSpaces(List<String> compressedDisk) {
        long sum = 0;
        for (int i=0; i<compressedDisk.size(); i++){
            var content = compressedDisk.get(i);

            if (!Objects.equals(content, FREE_POSITION)){
                var num = Integer.parseInt(content);
                long numValue = (long) i * num;
                sum += numValue;
            }
        }

        return sum;
    }

    private static List<String> compressDiskMapByBlock(List<String> disk, int maxIdNumber) {
        var compressDisk = new ArrayList<>(disk);

        var indexLeft = 0;
        var indexRight = compressDisk.size()-1;
        var currentIdNumber = maxIdNumber;

        while (indexLeft<indexRight && currentIdNumber>0){
            var blockDataSize=0;
            var currentRightValue = compressDisk.get(indexRight);

            if(currentRightValue.equals(FREE_POSITION)){
                indexRight= skipRightSpaces(compressDisk,indexRight);
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
                indexRight = skipBLock(compressDisk, indexRight, currentNum);
            }

            var blockDataFreeSpace = 0;
            while (blockDataFreeSpace < blockDataSize && indexLeft <= indexRight){
                if (Objects.equals(compressDisk.get(indexLeft), FREE_POSITION)){
                    blockDataFreeSpace++;
                }
                else {
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

    private static int skipBLock(List<String> compressDisk, int indexRight, int currentNum) {
        boolean numInCurrentBlock = true;

        while (numInCurrentBlock){
            if (compressDisk.get(indexRight).equals(FREE_POSITION)){
                numInCurrentBlock = false;
            }
            else if (currentNum != Integer.parseInt(compressDisk.get(indexRight))){
                numInCurrentBlock = false;
            }
            else {
                indexRight--;
            }
        }

        return indexRight;
    }

    private static int skipRightSpaces(List<String> compressDisk, int indexRight) {
        if (compressDisk.get(indexRight).equals(FREE_POSITION)){
            while (Objects.equals(compressDisk.get(indexRight), FREE_POSITION)){
                indexRight--;
            }
        }
        return indexRight;
    }

    private static int getFirstFreePosition(ArrayList<String> compressDisk) {
        int i=0;
        while (!Objects.equals(compressDisk.get(i), FREE_POSITION)){
            i++;
        }
        return i;
    }
}
