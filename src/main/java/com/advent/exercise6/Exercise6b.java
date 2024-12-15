package com.advent.exercise6;

import java.util.*;

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

    private static Set<Position> loopPoints = new HashSet<>();
    private static List<Position> squareCornersQueue = new ArrayList<>();
    private static Set<Position> obstructionSet = new HashSet<>();

    public static int findNumObstructionsPositions(char[][] matrix){
        squareCornersQueue.clear();
        obstructionSet.clear();
        var matrixSolved = resolvePuzzle(matrix);
        //System.out.println(matrixSolved);
        return obstructionSet.size();
    }

    private static char[][] resolvePuzzle(char[][] initMatrix) {
        char[][] matrix = initMatrix;
        Vector vector = findGuard(matrix).get();

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
                moveGuard(matrix,vector,nextVector);
                vector = nextVector;
                squareCornersQueue.add(position);
            }
            else{
                var nextVector = new Vector(nextPosition,direction);
                moveGuard(matrix,vector,nextVector);
                vector = nextVector;
            }

            if (squareCornersQueue.size()==3){
                var pos1 = squareCornersQueue.get(0);
                var pos2 = squareCornersQueue.get(1);
                var pos3 = squareCornersQueue.get(2);

                if (pos1.x()==pos2.x() && pos2.y()==pos3.y() && pos1.x()<pos3.x() && pos1.y() < pos3.y()){
                    var obs = new Position(pos3.x(), pos1.y()-1);
                    if (!obstacleCollision(matrix,pos3,obs,Direction.WEST)){
                        obstructionSet.add(obs);
                    }
                    squareCornersQueue.remove(0);
                }
                else if (pos1.y()==pos2.y() && pos2.x()==pos3.x() && pos1.y() > pos3.y()){
                    var obs = new Position(pos1.x()-1, pos3.y());
                    if (!obstacleCollision(matrix,pos3,obs,Direction.NORTH)){
                        obstructionSet.add(obs);
                    }
                    squareCornersQueue.remove(0);
                }
                else if (pos1.x()==pos2.x() && pos2.y()==pos3.y() && pos1.y() > pos3.y()){
                    var obs = new Position(pos3.x(), pos1.y()+1);
                    if (!obstacleCollision(matrix,pos3,obs,Direction.EAST)){
                        obstructionSet.add(obs);
                    }
                    squareCornersQueue.remove(0);
                }
                else if (pos1.y()==pos2.y() && pos2.x()==pos3.x() && pos1.y() < pos3.y()){
                    var obs = new Position(pos1.x()+1, pos3.y());
                    if (!obstacleCollision(matrix,pos3,obs,Direction.SOUTH)){
                        obstructionSet.add(obs);
                    }
                    squareCornersQueue.remove(0);
                }
            }
        }

        return matrix;
    }

    private static boolean obstacleCollision(char[][] matrix, Position initPosition, Position endPosition, Direction direction){
        boolean found = false;
        var row = initPosition.x();
        var colum = initPosition.y();
        var topRow = endPosition.x();
        var topColumn = endPosition.y();

        switch (direction){
            case WEST -> {
                int j = colum;
                while (j>= topColumn && !found){
                    found = matrix[row][j] == OBSTACLE;
                    j--;
                }
            }
            case NORTH -> {
                int i = row;
                while (i>=topRow && !found){
                    found = matrix[i][colum] == OBSTACLE;
                    i--;
                }
            }
            case EAST -> {
                int j = colum;
                while (j<=topColumn && !found){
                    found = matrix[row][j] == OBSTACLE;
                    j++;
                }
            }
            case SOUTH -> {
                int i = row;
                while (i<=topRow && !found){
                    found = matrix[i][colum] == OBSTACLE;
                    i++;
                }
            }
        }

        return found;
    }

    private static void moveGuard(char[][] matrix, Vector vector, Vector nextVector) {

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
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_NORTH;
                    }
                }
                case EAST -> {
                    if (current == UP_DOWN){
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_EAST;
                    }
                }
                case SOUTH -> {
                    if (current == LEFT_RIGHT) {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_SOUTH;
                    }
                }
                case WEST -> {
                    if (current == UP_DOWN){
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = INTERSECTION;
                    }
                    else {
                        matrix[nextVector.pos().x()][nextVector.pos().y()] = GUARD_WEST;
                    }
                }
            }
        }
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

//    private static int countObstructions(char[][] matrix) {
//        var candidates = new HashSet<Position>();
//        var count = 0;
//        for (int row = 0; row < matrix.length; row++) {
//            for (int column = 0; column < matrix.length; column++) {
//                char currentValue = matrix[row][column];
//                var maybeObstacle = surroundedByObstacle(matrix,row,column);
//                if (currentValue == INTERSECTION && maybeObstacle.isPresent()){
//                    candidates.add(maybeObstacle.get());
//                }
//            }
//        }
//        return candidates.size();
//    }

//    private static Optional<Position> surroundedByObstacle(char[][] matrix, int row, int column) {
//        int i=0;
//        Optional<Position> result = Optional.empty();
//        if (row-1 > 0 && matrix[row-1][column] == OBSTACLE ){
//            i++;
//            result = Optional.of(new Position(row-1,column));
//        }
//        if (row+1 < matrix.length && matrix[row+1][column] == OBSTACLE ){
//            i++;
//            result = Optional.of(new Position(row+1,column));
//        }
//        if (column-1 > 0 && matrix[row][column-1] == OBSTACLE ){
//            i++;
//            result = Optional.of(new Position(row-1,column));
//        }
//        if (column+1 < matrix.length && matrix[row][column+1] == OBSTACLE ){
//            i++;
//            result = Optional.of(new Position(row,column+1));
//        }
//        if (i>1) {
//            System.out.println("i " + i);
//        }
//        return result;
////        return matrix[row-1][column] == OBSTACLE || matrix[row+1][column] == OBSTACLE ||
////                matrix[row][column-1] == OBSTACLE || matrix[row+1][column+1] == OBSTACLE;
//    }

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
