package com.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise9b {

    public static final String FREE_POSITION = ".";

    public static int fileSystemCheckSum(String diskMap){
        var disk = decodeDiskMap(diskMap);
//        System.out.println(disk);
        var compressedDisk = compressDiskMap(disk);
//        System.out.println(compressedDisk);
        return calculateCheckSum(compressedDisk);
    }

    private static int calculateCheckSum(List<String> compressedDisk) {
        int sum = 0;
        boolean finished = false;
        int i = 0;
        while (i<compressedDisk.size() && !finished){

            if (Objects.equals(compressedDisk.get(i), FREE_POSITION)){
                finished=true;
            }
            else {
                var num = Integer.parseInt(compressedDisk.get(i));
                var numValue = num * i;
                sum = sum + numValue;
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
//        int size = calculateSize(diskMap);
//        char[] diskArray = new char[size];
        var diskArray = new ArrayList<String>();

//        int currentPosition=0;
        Integer idNumber=0;
        for (int i=0;i<diskMap.length();i++){
            int num = Integer.parseInt(String.valueOf(diskMap.charAt(i)));

            if (i %2 == 0){
//                if (idNumber.toString().length()>1){
                    for (int j=0;j<num; j++){
                        diskArray.add(idNumber.toString());
                    }
//                }
//                else{
//                    var c = Character.forDigit(idNumber,10);
//                    Arrays.fill(diskArray, currentPosition, currentPosition+num, c);
//                    currentPosition+=num;
//                }

                idNumber++;
            }
            else {
                for (int k=0;k<num; k++){
                    diskArray.add(FREE_POSITION);
                }
//                Arrays.fill(diskArray, currentPosition, currentPosition+num, FREE_POSITION);
//                currentPosition+=num;
            }
        }

        return diskArray;
    }

    //2333133121414131402
//    private static int calculateSize(String diskMap) {
//        int size = 0;
//        var idNumber=0;
//        for (int i=0;i<diskMap.length();i++){
//            int numRepeat = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
//            if (i %2 == 0){
//                var numDigits = String.valueOf(idNumber).length();
//                size+= (numRepeat * numDigits);
//                idNumber++;
//            }
//            else{
//                size+= numRepeat;
//            }
////            if (String.valueOf(num).length()>1){
////                size+= num * String.valueOf(num).length();
////            }
////            size+= num;
//        }
//        return size;
//    }


}
