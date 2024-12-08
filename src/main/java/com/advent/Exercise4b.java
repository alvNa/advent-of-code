package com.advent;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Exercise4b {
    private static final String MATCH_WORD = "XMAS";
    private static final String MATCH_WORD_REVERSED = "SAMX";
    private static final int MATCH_WORD_LENGTH = MATCH_WORD.length();

    public static int findXMAS(char[][] matrix){
        var x = findHorizontal(matrix);
        var y = findVertical(matrix); //1+2
        var d1 = findDiagonalRight(matrix);//1+3
        var d2 = findDiagonalLeft(matrix);//1+4
        return x + y + d1 + d2;
    }

    private static int findHorizontal(char[][] matrix) {
        var candidate1 ="";
        var candidate2 ="";
        var count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                var letter = Character.toString(matrix[i][j]);
                candidate1 = findMatch(candidate1, letter);
                candidate2 = findMatchReverse(candidate2, letter);

                if (candidate1.equals(MATCH_WORD)){
                    count++;
                    candidate1="";
                }
                if (candidate2.equals(MATCH_WORD_REVERSED)){
                    count++;
                    candidate2="";
                }
            }
        }
        return count;
    }

    private int findVertical(char[][] matrix) {
        var candidate1 ="";
        var candidate2 ="";
        var count = 0;
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                //var letter = Character.toString(matrix[i][j]);
//                candidate1 = findMatch(candidate1, letter);
//                candidate2 = findMatchReverse(candidate2, letter);

                if (isVertical(matrix,i,j)){
                    count++;
                    //candidate1="";
                }
//                if (candidate2.equals(MATCH_WORD_REVERSED)){
//                    count++;
//                    candidate2="";
//                }
            }
        }
        return count;
    }

    private int findDiagonalRight(char[][] matrix) {
        int counter = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (isDiagonalRight(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int findDiagonalLeft(char[][] matrix) {
        int counter = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (isDiagonalLeft(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static String findMatch(String currentWord, String letter) {
        var nextWord="";
        switch (letter){
            case "X":
                nextWord = letter; break;
            case "M": if (currentWord.equals("X")){
                nextWord = currentWord + letter;
            } break;
            case "A": if (currentWord.equals("XM")){
                nextWord = currentWord + letter;
            } break;
            case "S": if (currentWord.equals("XMA")){
                nextWord = currentWord + letter;
            } break;
        }

        return nextWord;
    }

    private static String findMatchReverse(String currentWord, String letter) {
        var nextWord="";
        switch (letter){
            case "S":
                nextWord = letter; break;
            case "A": if (currentWord.equals("S")){
                nextWord = currentWord + letter;
            } break;
            case "M": if (currentWord.equals("SA")){
                nextWord = currentWord + letter;
            } break;
            case "X": if (currentWord.equals("SAM")){
                nextWord = currentWord + letter;
            } break;
        }
        return nextWord;
    }

    private static boolean isVertical(char[][] matrix, int row, int colum) {
        var top = matrix.length - MATCH_WORD_LENGTH;
        if (row > top || colum < MATCH_WORD_LENGTH) {
            return false;
        }
        else {
            boolean normal = matrix[row][colum] == 'X' && matrix[row + 1][colum] == 'M' && matrix[row + 2][colum] == 'A' && matrix[row + 3][colum] == 'S';
            boolean reversed = matrix[row][colum] == 'S' && matrix[row + 1][colum] == 'A' && matrix[row + 2][colum] == 'M' && matrix[row + 3][colum] == 'X';
            return normal || reversed;
        }
    }

    private static boolean isDiagonalRight(char[][] matrix, int row, int colum) {
        var top = matrix.length - MATCH_WORD_LENGTH;
        //if (row > top || colum > top) {
        if (row + 3 >= matrix.length || colum + 3 >= matrix.length) {
            return false;
        }
        else {
            boolean normal = matrix[row][colum] == 'X' && matrix[row + 1][colum + 1] == 'M' && matrix[row + 2][colum + 2] == 'A' && matrix[row + 3][colum + 3] == 'S';
            boolean reversed = matrix[row][colum] == 'S' && matrix[row + 1][colum + 1] == 'A' && matrix[row + 2][colum + 2] == 'M' && matrix[row + 3][colum + 3] == 'X';
            return normal || reversed;
        }
    }

    private static boolean isDiagonalLeft(char[][] matrix, int row, int colum) {
        //var top = matrix.length - MATCH_WORD_LENGTH;
        if (row + 3 >= matrix.length || colum - 3 < 0) {
            return false;
        }
        else {
            boolean normal = matrix[row][colum] == 'X' && matrix[row + 1][colum - 1] == 'M' && matrix[row + 2][colum - 2] == 'A' && matrix[row + 3][colum - 3] == 'S';
            boolean reversed = matrix[row][colum] == 'S' && matrix[row + 1][colum - 1] == 'A' && matrix[row + 2][colum - 2] == 'M' && matrix[row + 3][colum - 3] == 'X';
            return normal || reversed;
        }
    }
}
