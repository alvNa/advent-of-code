package com.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise9b {

    public static final String FREE_POSITION = ".";

    public static long fileSystemCheckSum(String diskMap){
        var disk = decodeDiskMap(diskMap);
//        System.out.println(disk);
        var compressedDisk = compressDiskMap(disk);
//        System.out.println(compressedDisk);
        return calculateCheckSum(compressedDisk);
    }

    private static long calculateCheckSum(List<String> compressedDisk) {
        long sum = 0;
        boolean finished = false;
        int i = 0;
        while (i<compressedDisk.size() && !finished){

            if (Objects.equals(compressedDisk.get(i), FREE_POSITION)){
                finished=true;
            }
            else {
                var num = Long.parseLong(compressedDisk.get(i));
                long numValue = i * num;
                sum += numValue;
            }
            i++;
        }
        return sum;
    }

    private static List<String> compressDiskMap(List<String> disk) {
        var compressDisk = new ArrayList<>(disk);

        var indexLeft = 0;
        var indexRight = disk.size()-1;

        while (indexLeft < indexRight){

            while (!Objects.equals(compressDisk.get(indexLeft), FREE_POSITION)){
                indexLeft++;
            }

            while (Objects.equals(compressDisk.get(indexRight), FREE_POSITION)){
                indexRight--;
            }

            if (indexLeft < indexRight) {
                String number = compressDisk.get(indexRight);
                compressDisk.remove(indexLeft);
                compressDisk.add(indexLeft, number);
                compressDisk.remove(indexRight);
                compressDisk.add(indexRight, FREE_POSITION);
            }
        }

        return compressDisk;
    }

    private static List<String> decodeDiskMap(String diskMap) {
        var diskArray = new ArrayList<String>();
        int idNumber=0;
        for (int i=0;i<diskMap.length();i++){
            int num = Integer.parseInt(String.valueOf(diskMap.charAt(i)));

            for (int j=0;j<num; j++){
                String value = (i %2 == 0) ? Integer.toString(idNumber):FREE_POSITION;
                diskArray.add(value);
            }

            if (i %2 == 0){
                idNumber++;
            }
        }

        return diskArray;
    }
}
