package com.advent;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Exercise4 {
    private static final String MATCH_WORD = "XMAS";
    private static final String MATCH_WORD_REVERSED = "SAMX";
    private static final int MATCH_WORD_LENGTH = MATCH_WORD.length();

    public static int findXMAS(char[][] matrix) {
        var x = findHorizontal2(matrix); //5
        var y = findVertical(matrix); //1+2
        var d1 = findDiagonalRight(matrix);//1+4
        var d2 = findDiagonalLeft(matrix);//1+4
        return x + y + d1 + d2;
    }

    private int findHorizontal2(char[][] matrix) {
        var count = 0;
        var top = matrix.length - MATCH_WORD_LENGTH;

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column <= top; column++) {

                if (isHorizontal(matrix, row, column)) {
                    count++;
                }
            }
        }
        return count;
    }

    private int findVertical(char[][] matrix) {
        var count = 0;
        var top = matrix.length - MATCH_WORD_LENGTH;

        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i <= top; i++) {

                if (isVertical(matrix, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private int findDiagonalRight(char[][] matrix) {
        int counter = 0;

        var top = matrix.length - MATCH_WORD_LENGTH;

        for (int row = 0; row <= top; row++) {
            for (int col = 0; col <= top; col++) {
                if (isDiagonalRight(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int findDiagonalLeft(char[][] matrix) {
        int counter = 0;
        var top = matrix.length - MATCH_WORD_LENGTH;

        for (int row = 0; row <= top; row++) {
            for (int col = MATCH_WORD_LENGTH - 1; col < matrix.length; col++) {
                if (isDiagonalLeft(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean isHorizontal(char[][] matrix, int row, int colum) {
        boolean normal = matrix[row][colum] == 'X' && matrix[row][colum + 1] == 'M' && matrix[row][colum + 2] == 'A' && matrix[row][colum + 3] == 'S';
        boolean reversed = matrix[row][colum] == 'S' && matrix[row][colum + 1] == 'A' && matrix[row][colum + 2] == 'M' && matrix[row][colum + 3] == 'X';
        return normal || reversed;
    }

    private static boolean isVertical(char[][] matrix, int row, int colum) {
        boolean normal = matrix[row][colum] == 'X' && matrix[row + 1][colum] == 'M' && matrix[row + 2][colum] == 'A' && matrix[row + 3][colum] == 'S';
        boolean reversed = matrix[row][colum] == 'S' && matrix[row + 1][colum] == 'A' && matrix[row + 2][colum] == 'M' && matrix[row + 3][colum] == 'X';
        return normal || reversed;
    }

    private static boolean isDiagonalRight(char[][] matrix, int row, int colum) {
        boolean normal = matrix[row][colum] == 'X' && matrix[row + 1][colum + 1] == 'M' && matrix[row + 2][colum + 2] == 'A' && matrix[row + 3][colum + 3] == 'S';
        boolean reversed = matrix[row][colum] == 'S' && matrix[row + 1][colum + 1] == 'A' && matrix[row + 2][colum + 2] == 'M' && matrix[row + 3][colum + 3] == 'X';
        return normal || reversed;
    }

    private static boolean isDiagonalLeft(char[][] matrix, int row, int colum) {
        boolean normal = matrix[row][colum] == 'X' && matrix[row + 1][colum - 1] == 'M' && matrix[row + 2][colum - 2] == 'A' && matrix[row + 3][colum - 3] == 'S';
        boolean reversed = matrix[row][colum] == 'S' && matrix[row + 1][colum - 1] == 'A' && matrix[row + 2][colum - 2] == 'M' && matrix[row + 3][colum - 3] == 'X';
        return normal || reversed;
    }
}
