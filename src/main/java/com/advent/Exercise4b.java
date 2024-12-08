package com.advent;

public class Exercise4b {

    public static int findCrossMAS(char[][] matrix) {
        int counter = 0;

        for (int row = 1; row < matrix.length-1; row++) {
            for (int col = 1; col < matrix.length-1; col++) {
                if (isCrossMAS(matrix, row, col)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean isCrossMAS(char[][] matrix, int row, int colum) {
        boolean cross1 = matrix[row][colum] == 'A' &&
                matrix[row - 1][colum - 1] == 'M' && matrix[row - 1][colum + 1] == 'S' &&
                matrix[row + 1][colum - 1] == 'M' && matrix[row + 1][colum + 1] == 'S';
        boolean cross2 = matrix[row][colum] == 'A' &&
                matrix[row - 1][colum - 1] == 'S' && matrix[row - 1][colum +1] == 'M' &&
                matrix[row + 1][colum - 1] == 'S' && matrix[row + 1][colum +1] == 'M';
        boolean cross3 = matrix[row][colum] == 'A' &&
                matrix[row - 1][colum - 1] == 'S' && matrix[row - 1][colum +1] == 'S' &&
                matrix[row + 1][colum - 1] == 'M' && matrix[row + 1][colum +1] == 'M';
        boolean cross4 = matrix[row][colum] == 'A' &&
                matrix[row - 1][colum - 1] == 'M' && matrix[row - 1][colum +1] == 'M' &&
                matrix[row + 1][colum - 1] == 'S' && matrix[row + 1][colum +1] == 'S';
        return cross1 || cross2 || cross3 || cross4;
    }
}
