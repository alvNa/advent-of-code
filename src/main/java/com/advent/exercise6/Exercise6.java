package com.advent.exercise6;

import java.util.Optional;

public class Exercise6 {
    private static final char GUARD_NORTH = '^';
    private static final char GUARD_SOUTH = 'v';
    private static final char GUARD_EAST = '>';
    private static final char GUARD_WEST = '<';
    private static final char OBSTACLE = '#';
    private static final char VISITED = 'X';
    private static final char FREE_FIELD = '.';

    public static int numGuardPositions(char[][] matrix){
        var finalState = resolvePuzzle(matrix);
        var visits = countGuardVisits(finalState);
        return visits;
    }

    private static char[][] resolvePuzzle(char[][] initMatrix) {
        char[][] matrix = initMatrix;
        Vector initVector = findGuard(matrix).get();
        var position = initVector.pos();
        Direction direction = initVector.direction();

        while (positionInRange(position,matrix.length)){

            var nextPosition = switch (direction){
                case NORTH -> new Position(position.x()-1,position.y());
                case EAST -> new Position(position.x(),position.y()+1);
                case SOUTH -> new Position(position.x()+1,position.y());
                case WEST -> new Position(position.x(),position.y()-1);
            };

            if (containsObstacle(matrix,nextPosition)){
                direction=changeGuardDirection(direction);
                changeGuardDirection(matrix,position,direction);
            }
            else{
                moveGuard(matrix,position,nextPosition,direction);
                position = nextPosition;
            }
        }

        return matrix;
    }

    private static void moveGuard(char[][] matrix, Position position, Position nextPosition, Direction direction) {
        matrix[position.x()][position.y()]=VISITED;
        if (positionInRange(nextPosition,matrix.length)) {
            switch (direction) {
                case NORTH -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_NORTH;
                case EAST -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_EAST;
                case SOUTH -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_SOUTH;
                case WEST -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_WEST;
            }
        }
    }

    private static void changeGuardDirection(char[][] matrix, Position position, Direction dir) {
        switch (dir) {
            case NORTH -> matrix[position.x()][position.y()] = GUARD_NORTH;
            case EAST -> matrix[position.x()][position.y()] = GUARD_EAST;
            case SOUTH -> matrix[position.x()][position.y()] = GUARD_SOUTH;
            case WEST -> matrix[position.x()][position.y()] = GUARD_WEST;
        }
    }

    /**
     * Rotates the direction 90º
     */
    static Direction changeGuardDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    private static int countGuardVisits(char[][] matrix) {
        var count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if (matrix[row][column]==VISITED){
                    count++;
                }
            }
        }
        return count;
    }

    static Optional<Vector> findGuard(char[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {

                char currentVal = matrix[row][column];
                switch (currentVal){
                    case GUARD_NORTH: return Optional.of(new Vector(new Position(row,column),Direction.NORTH));
                    case GUARD_SOUTH: return Optional.of(new Vector(new Position(row,column),Direction.SOUTH));
                    case GUARD_EAST: return Optional.of(new Vector(new Position(row,column),Direction.EAST));
                    case GUARD_WEST: return Optional.of(new Vector(new Position(row,column),Direction.WEST));
                }
            }
        }
        return Optional.empty();
    }

    static boolean containsObstacle(char[][] matrix, Position pos){
        return positionInRange(pos,matrix.length) && matrix[pos.x()][pos.y()]==OBSTACLE;
    }

    static boolean positionInRange(Position position, int maxSize){
        return position.x()>=0 && position.x() < maxSize &&
               position.y()>=0 && position.y() < maxSize;
    }
}
