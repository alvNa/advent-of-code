package com.advent;

import java.nio.charset.Charset;
import java.util.Arrays;

public class Exercise9 {

    public static final char FREE_POSITION = '.';

    public static int fileSystemCheckSum(String diskMap){
        var disk = decodeDiskMap(diskMap);
        //System.out.println(disk);
        var compressedDisk = compressDiskMap(disk);
        System.out.println(compressedDisk);
        return calculateCheckSum(compressedDisk);
    }

    private static int calculateCheckSum(char[] compressedDisk) {
        int sum = 0;
        boolean finished = false;
        int i = 0;
        while (i<compressedDisk.length && !finished){

            if (compressedDisk[i] == FREE_POSITION){
                finished=true;
            }
            else {
                char c = compressedDisk[i];
//                System.out.println("c: " + c);
                var num = Integer.parseInt(String.valueOf(c));
                sum+=num*i;
            }
            i++;
        }
        return sum;
    }

    private static char[] compressDiskMap(char[] disk) {
        var compressDisk = Arrays.copyOf(disk,disk.length);

        var indexLeft = 0;
        var indexRight = disk.length-1;

        while (indexLeft < indexRight){

            while (compressDisk[indexLeft] != FREE_POSITION){
                indexLeft++;
            }

            while (compressDisk[indexRight] == FREE_POSITION){
                indexRight--;
            }

            if (indexLeft < indexRight) {
                char number = compressDisk[indexRight];
                compressDisk[indexLeft] = number;
                compressDisk[indexRight] = FREE_POSITION;
            }
        }

        return compressDisk;
    }

    private static char[] decodeDiskMap(String diskMap) {
        int size = calculateSize(diskMap);
        char[] diskArray = new char[size];
        int currentPosition=0;
        Integer idNumber=0;
        for (int i=0;i<diskMap.length();i++){
            int num = Integer.parseInt(String.valueOf(diskMap.charAt(i)));

            if (i %2 == 0){
//                if (idNumber.toString().length()>1){
                    for (int j=0;j<num; j++){
                        for(char c: idNumber.toString().toCharArray()){
                            diskArray[currentPosition++] = c;
                        }
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
                Arrays.fill(diskArray, currentPosition, currentPosition+num, FREE_POSITION);
                currentPosition+=num;
            }
        }

        return diskArray;
    }

    //2333133121414131402
    private static int calculateSize(String diskMap) {
        int size = 0;
        var idNumber=0;
        for (int i=0;i<diskMap.length();i++){
            int numRepeat = Integer.parseInt(String.valueOf(diskMap.charAt(i)));
            if (i %2 == 0){
                var numDigits = String.valueOf(idNumber).length();
                size+= (numRepeat * numDigits);
                idNumber++;
            }
            else{
                size+= numRepeat;
            }
//            if (String.valueOf(num).length()>1){
//                size+= num * String.valueOf(num).length();
//            }
//            size+= num;
        }
        return size;
    }


}
