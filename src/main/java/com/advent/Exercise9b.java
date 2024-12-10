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
//        System.out.println(disk);
        var compressedDisk = compressDiskMapByBlock(disk, diskMap.length()/2);
//        System.out.println(compressedDisk);
        return calculateCheckSumWithSpaces(compressedDisk);
    }

    private static long calculateCheckSumWithSpaces(List<String> compressedDisk) {
        long sum = 0;
        for (int i=0; i<compressedDisk.size(); i++){
            var content = compressedDisk.get(i);
            if (!Objects.equals(content, FREE_POSITION)){
                var num = Long.parseLong(compressedDisk.get(i));
                long numValue = i * num;
                sum += numValue;
            }
        }
        return sum;
    }

    private static List<String> compressDiskMapByBlock(List<String> disk, int maxIdNumber) {
        var compressDisk = new ArrayList<>(disk);

        var indexLeft = 0;
        var indexRight = disk.size()-1;
        var currentIdNumber = maxIdNumber;

        while (indexLeft < indexRight && currentIdNumber>=0){
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
                //contrarrest next iteration
//                currentIdNumber++;
            }
            else { //if (currentNum < currentIdNumber)
                currentIdNumber--;
            }

            var blockDataFreeSpace = 0;
            var indexLeftAux = indexLeft;
            while (blockDataFreeSpace < blockDataSize && indexLeftAux < indexRight){
                if (Objects.equals(compressDisk.get(indexLeftAux), FREE_POSITION)){
                    blockDataFreeSpace++;
                }

                if (!Objects.equals(compressDisk.get(indexLeftAux), FREE_POSITION)){
                    blockDataFreeSpace=0;
                }
                indexLeftAux++;
            }

            if (blockDataFreeSpace >= blockDataSize){
                for (int i=0; i<blockDataSize; i++){
                    var positionRight = indexRight+1+i;
                    var positionLeft = indexLeftAux-blockDataSize+i;

                    swapData(compressDisk, positionRight, positionLeft);
                }
            }

//            currentIdNumber--;


            //Set left index
            indexLeft = 0;
            while (!Objects.equals(compressDisk.get(indexLeft), FREE_POSITION)){
                indexLeft++;
            }
        }

        return compressDisk;
    }
}
