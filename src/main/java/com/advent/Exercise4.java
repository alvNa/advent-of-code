package com.advent;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Exercise4 {
    private static final String MATCH_WORD = "XMAS";
    private static final int MATCH_WORD_LENGTH = MATCH_WORD.length();

    public static int findXMAS(char[][] matrix){
        var case1 = findHorizontalRight(matrix);
        var case2 = findHorizontalLeft(matrix);
        var case3 = findVerticalDown(matrix);
        var case4 = findVerticalUp(matrix);
        var case5 = diagonalRightDown(matrix);
        var case6 = diagonalRightUp(matrix);
        var case7 = diagonalLeftUp(matrix);
        var case8 = diagonalLeftDown(matrix);

        return case1 + case2 + case3 + case4 +
                case5 + case6 + case7 + case8;
    }

    private static int findHorizontalRight(char[][] matrix/*, int row, int col*/){
        String matchCandidate = "";

        int counter = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                var currentLetter = matrix[row][col];
                matchCandidate = getMatchCandidate(matchCandidate, currentLetter);

                if (isMatch(matchCandidate)) {
                    counter++;
                    matchCandidate = "";
                }
            }
        }
        return counter;
    }

    private static int findHorizontalLeft(char[][] matrix/*, int row, int col*/){
        String matchCandidate = "";

        int counter = 0;

        for (int row = matrix.length-1; row >= 0; row--) {
            for (int col = 0; col < matrix[row].length; col++) {
                var currentLetter = matrix[row][col];
                matchCandidate = getMatchCandidate(matchCandidate, currentLetter);

                if (isMatch(matchCandidate)) {
                    counter++;
                    matchCandidate = "";
                }
            }
        }
        return counter;
    }

    private static int findVerticalDown(char[][] matrix/*, int row, int col*/){
        String matchCandidate = "";

        int counter = 0;

        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                var currentLetter = matrix[row][col];
                matchCandidate = getMatchCandidate(matchCandidate, currentLetter);

                if (isMatch(matchCandidate)) {
                    counter++;
                    matchCandidate = "";
                }
            }
        }
        return counter;
    }

    private static int findVerticalUp(char[][] matrix/*, int row, int col*/){
        String matchCandidate = "";

        int counter = 0;

        for (int col = 0; col < matrix.length; col++) {
            for (int row = matrix.length-1; row >= 0; row--) {
                var currentLetter = matrix[row][col];
                matchCandidate = getMatchCandidate(matchCandidate, currentLetter);

                if (isMatch(matchCandidate)) {
                    counter++;
                    matchCandidate = "";
                }
            }
        }
        return counter;
    }

    private static int diagonalRightDown(char[][] matrix){
        int counter = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isDiagonalRightDownMatchCandidate(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int diagonalRightUp(char[][] matrix){
        int counter = 0;

        for (int row = matrix.length-1; row >= 0 ; row--) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isDiagonalUpMatchCandidate(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int diagonalLeftDown(char[][] matrix){
        int counter = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = matrix.length-1; col >= 0; col--) {
                System.out.println("row: " + row + " col: " + col);
                if (isDiagonalLeftDownMatchCandidate(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int diagonalLeftUp(char[][] matrix){
        return 0;
    }



    private static boolean isDiagonalUpMatchCandidate(char[][] matrix, int row, int colum) {
        if (row < MATCH_WORD_LENGTH || colum + MATCH_WORD_LENGTH > matrix.length) {
            return false;
        }
        else {
            return matrix[row][colum] =='X' && matrix[row-1][colum+1] =='M' && matrix[row-2][colum+2] =='A' && matrix[row-3][colum+3] =='S';
        }
    }

    private static boolean isDiagonalRightDownMatchCandidate(char[][] matrix, int row, int colum) {
        var top = matrix.length - MATCH_WORD_LENGTH;
        if (row > top || colum > top) {
            return false;
        }
        else {
            return matrix[row][colum] =='X' && matrix[row+1][colum+1] =='M' && matrix[row+2][colum+2] =='A' && matrix[row+3][colum+3] =='S';
        }
    }

    private static boolean isDiagonalLeftDownMatchCandidate(char[][] matrix, int row, int colum) {
        var top = matrix.length - MATCH_WORD_LENGTH;
        if (row > top || colum < MATCH_WORD_LENGTH) {
            return false;
        }
        else {
            return matrix[row][colum] =='X' && matrix[row+1][colum-1] =='M' && matrix[row+2][colum-2] =='A' && matrix[row+3][colum-3] =='S';
        }
    }

    private static boolean isMatch(String matchCandidate) {
        return MATCH_WORD.equals(matchCandidate);
    }

    private static String getMatchCandidate(String currentCandidate, char currentLetter) {
        var matchCandidate = currentCandidate;

        if (matchCandidate.isEmpty() && currentLetter == 'X') {
            matchCandidate ="X";
        } else if ("X".equals(matchCandidate) && currentLetter == 'M') {
            matchCandidate ="XM";
        } else if ("XM".equals(matchCandidate) && currentLetter == 'A') {
            matchCandidate ="XMA";
        }
        else if ("XMA".equals(matchCandidate) && currentLetter == 'S') {
            matchCandidate ="XMAS";
        }
        else{
            matchCandidate ="";
        }
        return matchCandidate;
    }
}
