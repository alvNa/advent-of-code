package com.advent.exercise6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Exercise6b {
    private static final char GUARD_NORTH = '^';
    private static final char GUARD_SOUTH = 'v';
    private static final char GUARD_EAST = '>';
    private static final char GUARD_WEST = '<';
    private static final char OBSTACLE = '#';
    private static final char VISITED = 'X';
    private static final char FREE_FIELD = '.';
    private static final char OBSTRUCTION = 'O';
    private static final char UP_DOWN = '|';
    private static final char LEFT_RIGHT = '-';
    private static final char INTERSECTION = '+';

    private static int obstructions =0;

    public static int findNumObstructionsPositions(char[][] matrix){
        var finalState = resolvePuzzle(matrix);
        //var obs = countObstructions(finalState);
        return obstructions;
    }

    private static char[][] resolvePuzzle(char[][] initMatrix) {
        char[][] matrix = initMatrix;
        Vector vector = findGuard(matrix).get();
        obstructions = 0;
        List<Position> crossPositions = new ArrayList<Position>();

        while (positionInRange(vector.pos(),matrix.length)){
            var position = vector.pos();
            var direction = vector.direction();

            var nextPosition = switch (direction){
                case NORTH -> new Position(position.x()-1,position.y());
                case EAST -> new Position(position.x(),position.y()+1);
                case SOUTH -> new Position(position.x()+1,position.y());
                case WEST -> new Position(position.x(),position.y()-1);
            };

            if (containsObstacle(matrix,nextPosition)){
                var newDirection=changeGuardDirection(direction);
                var nextVector = new Vector(position, newDirection);
                moveGuard(matrix,vector,nextVector,null);
                vector = nextVector;
                crossPositions.add(position);
            }
            else{
                var nextVector = new Vector(nextPosition,direction);
                moveGuard(matrix,vector,nextVector,crossPositions);
                vector = nextVector;
            }

            if (crossPositions.size()==4){
                crossPositions.clear();
            }
        }

        return matrix;
    }

    private static boolean isLoop(char[][] matrix, Vector vector) {
        //boolean result;

        var pos = vector.pos();
        var dir = vector.direction();

//        if (pos.x()==6 && pos.y()==4){
//            System.out.println(pos);
//        }

        if (positionInRange(pos,matrix.length)) {

            if (matrix[pos.x()][pos.y()] == GUARD_WEST && dir == Direction.WEST) {

                if (pos.y() - 1 >= 0 &&
                        (matrix[pos.x()][pos.y()-1] == UP_DOWN || matrix[pos.x()][pos.y()-1] == INTERSECTION)){
                    System.out.println("(x,y) =("+pos.x()+","+pos.y()+")");
                    return true;
                }
            }

            if (matrix[pos.x()][pos.y()] == GUARD_EAST && dir == Direction.EAST) {

                if (pos.y() + 1 < matrix.length &&
                        (matrix[pos.x()][pos.y()+1] == UP_DOWN || matrix[pos.x()][pos.y()+1] == INTERSECTION)){
                    System.out.println("(x,y) =("+pos.x()+","+pos.y()+")");
                    return true;
                }
            }

            if (matrix[pos.x()][pos.y()] == GUARD_NORTH && dir == Direction.NORTH) {

                if (pos.x() -1 > 0 &&
                        (matrix[pos.x()-1][pos.y()] == LEFT_RIGHT || matrix[pos.x()-1][pos.y()] == INTERSECTION)){
                    System.out.println("(x,y) =("+pos.x()+","+pos.y()+")");
                    return true;
                }
            }

            if (matrix[pos.x()][pos.y()] == GUARD_SOUTH && dir == Direction.SOUTH) {

                if (pos.x() +1 < matrix.length &&
                    (matrix[pos.x()+1][pos.y()] == LEFT_RIGHT || matrix[pos.x()+1][pos.y()] == INTERSECTION)){
                    System.out.println("(x,y) =("+pos.x()+","+pos.y()+")");
                    return true;
                }
            }
        }

        return false;
    }

    private static void moveGuard(char[][] matrix, Vector vector, Vector nextVector, List<Position> crossPositions) {

        if (vector.pos()==nextVector.pos() && vector.direction() != nextVector.direction()){
            matrix[vector.pos().x()][vector.pos().y()] = INTERSECTION;
        }
        else {
            var previous = matrix[vector.pos().x()][vector.pos().y()];
            switch (nextVector.direction()) {
                case NORTH, SOUTH -> {
                    if (previous==LEFT_RIGHT || previous==INTERSECTION){
                        matrix[vector.pos().x()][vector.pos().y()] = INTERSECTION;
                    }
                    else{
                        matrix[vector.pos().x()][vector.pos().y()] = UP_DOWN;
                    }
                }
                case EAST, WEST -> {
                    if (previous==UP_DOWN || previous==INTERSECTION){
                        matrix[vector.pos().x()][vector.pos().y()] = INTERSECTION;
                    }
                    else{
                        matrix[vector.pos().x()][vector.pos().y()] = LEFT_RIGHT;
                    }
                }
            }
        }

        if (positionInRange(nextVector.pos(),matrix.length) && (vector.pos()!=nextVector.pos())) {
            var current = matrix[nextVector.pos().x()][nextVector.pos().y()];

            switch (nextVector.direction()) {
                case NORTH -> {
                    if (current == LEFT_RIGHT) {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                        if (crossPositions.size()==3){
                            obstructions++;
                            //crossPositions.clear();
                            crossPositions.add(nextVector.pos());
                        }
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_NORTH;
                    }
                }
                case EAST -> {
                    if (current == UP_DOWN){
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                        if (crossPositions.size()==3){
                            obstructions++;
                            //crossPositions.clear();
                            crossPositions.add(nextVector.pos());
                        }
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_EAST;
                    }
                }
                case SOUTH -> {
                    if (current == LEFT_RIGHT) {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                        if (crossPositions.size()==3){
                            obstructions++;
                            //crossPositions.clear();
                            crossPositions.add(nextVector.pos());
                        }
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_SOUTH;
                    }
                }
                case WEST -> {
                    if (current == UP_DOWN){
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                        if (crossPositions.size()==3){
                            obstructions++;
                            //crossPositions.clear();
                            crossPositions.add(nextVector.pos());
                        }
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_WEST;
                    }
                }
            }
        }
    }


//    private static void moveGuard(char[][] matrix, Position position, Position nextPosition, Direction direction) {
//
//        if (null==direction) {
//            switch (direction) {
//                case NORTH, SOUTH -> matrix[position.x()][position.y()] = UP_DOWN;
//                case EAST, WEST -> matrix[position.x()][position.y()] = LEFT_RIGHT;
//            }
//        }
//
//        if (positionInRange(nextPosition,matrix.length)) {
//            switch (direction) {
//                case NORTH -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_NORTH;
//                case EAST -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_EAST;
//                case SOUTH -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_SOUTH;
//                case WEST -> matrix[nextPosition.x()][nextPosition.y()] = GUARD_WEST;
//            }
//        }
//    }

    private static void changeGuardDirection(char[][] matrix, Position position) {
//        switch (dir) {
//            case NORTH -> matrix[position.x()][position.y()] = GUARD_NORTH;
//            case EAST -> matrix[position.x()][position.y()] = GUARD_EAST;
//            case SOUTH -> matrix[position.x()][position.y()] = GUARD_SOUTH;
//            case WEST -> matrix[position.x()][position.y()] = GUARD_WEST;
//        }
        matrix[position.x()][position.y()] = INTERSECTION;
    }

    /**
     * Rotates the direction 90º
     */
    private static Direction changeGuardDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    private static int countObstructions(char[][] matrix) {
        var candidates = new HashSet<Position>();
        var count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                char currentValue = matrix[row][column];
                var maybeObstacle = surroundedByObstacle(matrix,row,column);
                if (currentValue == INTERSECTION && maybeObstacle.isPresent()){
                    candidates.add(maybeObstacle.get());
                }
            }
        }
        return candidates.size();
    }

    private static Optional<Position> surroundedByObstacle(char[][] matrix, int row, int column) {
        int i=0;
        Optional<Position> result = Optional.empty();
        if (row-1 > 0 && matrix[row-1][column] == OBSTACLE ){
            i++;
            result = Optional.of(new Position(row-1,column));
        }
        if (row+1 < matrix.length && matrix[row+1][column] == OBSTACLE ){
            i++;
            result = Optional.of(new Position(row+1,column));
        }
        if (column-1 > 0 && matrix[row][column-1] == OBSTACLE ){
            i++;
            result = Optional.of(new Position(row-1,column));
        }
        if (column+1 < matrix.length && matrix[row][column+1] == OBSTACLE ){
            i++;
            result = Optional.of(new Position(row,column+1));
        }
        if (i>1) {
            System.out.println("i " + i);
        }
        return result;
//        return matrix[row-1][column] == OBSTACLE || matrix[row+1][column] == OBSTACLE ||
//                matrix[row][column-1] == OBSTACLE || matrix[row+1][column+1] == OBSTACLE;
    }

    private static Optional<Vector> findGuard(char[][] matrix){
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

    private static boolean containsObstacle(char[][] matrix, Position pos){
        return positionInRange(pos,matrix.length) && matrix[pos.x()][pos.y()]==OBSTACLE;
    }

    private static boolean positionInRange(Position position, int maxSize){
        return position.x()>=0 && position.x() < maxSize &&
               position.y()>=0 && position.y() < maxSize;
    }
}
