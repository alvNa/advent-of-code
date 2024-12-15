package com.advent.exercise6;

import java.util.*;

import static com.advent.exercise6.Exercise6.*;

public class Exercise6c {
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
        var obstructions = resolvePuzzle(matrix);
        //System.out.println(matrixSolved);
        return obstructions.size();
    }

    private static Set<Position> resolvePuzzle(char[][] initMatrix) {
        Set<Position> obstructionSet = new HashSet<>();
        char[][] matrix = initMatrix;
        Vector currentVector = findGuard(matrix).get();

        while (positionInRange(currentVector.pos(),matrix.length)){
            var position = currentVector.pos();
            var direction = currentVector.direction();

//            var nextPosition = switch (direction){
//                case NORTH -> new Position(position.x()-1,position.y());
//                case EAST -> new Position(position.x(),position.y()+1);
//                case SOUTH -> new Position(position.x()+1,position.y());
//                case WEST -> new Position(position.x(),position.y()-1);
//            };
            var nextPosition = nextPosition(position,direction);

            var maybeObstruction = findObstructionMatch(matrix, currentVector);

            if (maybeObstruction.isPresent() && !isObstacle(matrix, maybeObstruction.get())){
                obstructionSet.add(maybeObstruction.get());
            }
            //maybeObstruction.ifPresent(obstructionSet::add);
            
            
            if (containsObstacle(matrix,nextPosition)){
                var newDirection=changeGuardDirection(direction);

                var nextVector = new Vector(position, newDirection);
                moveGuard(matrix,currentVector,nextVector);

                var newPosition = nextPosition(position,newDirection);
                nextVector = new Vector(newPosition, newDirection);
                currentVector = nextVector;

                //squareCornersQueue.add(position);
            }
            else{
                var nextVector = new Vector(nextPosition,direction);
                moveGuard(matrix,currentVector,nextVector);
                currentVector = nextVector;
            }

//            if (squareCornersQueue.size()==3){
//                var pos1 = squareCornersQueue.get(0);
//                var pos2 = squareCornersQueue.get(1);
//                var pos3 = squareCornersQueue.get(2);
//
//                if (pos1.x()==pos2.x() && pos2.y()==pos3.y() && pos1.x()<pos3.x() && pos1.y() < pos3.y()){
//                    var obs = new Position(pos3.x(), pos1.y()-1);
//                    if (!obstacleCollision(matrix,pos3,obs,Direction.WEST)){
//                        obstructionSet.add(obs);
//                    }
//                    squareCornersQueue.remove(0);
//                }
//                else if (pos1.y()==pos2.y() && pos2.x()==pos3.x() && pos1.y() > pos3.y()){
//                    var obs = new Position(pos1.x()-1, pos3.y());
//                    if (!obstacleCollision(matrix,pos3,obs,Direction.NORTH)){
//                        obstructionSet.add(obs);
//                    }
//                    squareCornersQueue.remove(0);
//                }
//                else if (pos1.x()==pos2.x() && pos2.y()==pos3.y() && pos1.y() > pos3.y()){
//                    var obs = new Position(pos3.x(), pos1.y()+1);
//                    if (!obstacleCollision(matrix,pos3,obs,Direction.EAST)){
//                        obstructionSet.add(obs);
//                    }
//                    squareCornersQueue.remove(0);
//                }
//                else if (pos1.y()==pos2.y() && pos2.x()==pos3.x() && pos1.y() < pos3.y()){
//                    var obs = new Position(pos1.x()+1, pos3.y());
//                    if (!obstacleCollision(matrix,pos3,obs,Direction.SOUTH)){
//                        obstructionSet.add(obs);
//                    }
//                    squareCornersQueue.remove(0);
//                }
//            }
        }

        return obstructionSet;
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

    private static Optional<Position> findObstructionMatch(char[][] matrix, Vector currentVector) {
        var pos = currentVector.pos();
        var dir = currentVector.direction();
        //Optional<Position> result;

         if (dir== Direction.WEST){
            if (intersection(matrix, pos) && trailUp(matrix, pos)){
                return Optional.of(getLeftPosition(pos));
            }
            else if (pos.y()==0){
                return Optional.of(pos);
            }
        } else if (dir== Direction.SOUTH) {
            if (intersection(matrix, pos) && trailLeft(matrix, pos)){
                return Optional.of(getDownPosition(pos));
            }
            else if (pos.x()==matrix.length-1){
                return Optional.of(pos);
            }
        } else if (dir== Direction.EAST) {
            if (intersection(matrix,pos) && trailDown(matrix, pos)){
                return Optional.of(getRightPosition(pos));
            }
            else if (pos.y()==matrix.length-1){
                return Optional.of(pos);
            }
        } else if (dir== Direction.NORTH) {
            if (intersection(matrix, pos) || trailRight(matrix, pos)) {
                return Optional.of(getUpPosition(pos));
            }
            else if (pos.x()==0){
                return Optional.of(pos);
            }
        }

        return Optional.empty();
    }

    private static boolean intersection(char[][] matrix, Position p) {
        return matrix[p.x()][p.y()]==INTERSECTION;
    }

    private static boolean trail(char[][] matrix, Position p, char trail) {
        return matrix[p.x()][p.y()]==trail;
    }

    private static boolean trailUp(char[][] matrix, Position p) {
//        var found = false;
//
//        int i = p.x();
//        while (i>0 && !found){
//            found = matrix[i][p.y()] == UP_DOWN /*|| matrix[i][p.y()] == OBSTACLE*/;
//            i--;
//        }
//
//        return found;

        var foundTrail = false;
        var foundObstacle = false;

        int i = p.x();
        while (i>=0 && !foundObstacle){
            foundObstacle = foundTrail && matrix[i][p.y()] == OBSTACLE;
            foundTrail = matrix[i][p.y()] == UP_DOWN || matrix[i][p.y()] == INTERSECTION;
            i--;
        }

        return foundObstacle;
    }

    private static boolean trailDown(char[][] matrix, Position p) {
        var foundTrail = false;
        var foundObstacle = false;

        int i = p.x();
        while (i<matrix.length && !foundObstacle){
            foundObstacle = foundTrail && matrix[i][p.y()] == OBSTACLE;
            foundTrail = matrix[i][p.y()] == UP_DOWN || matrix[i][p.y()] == INTERSECTION;
            i++;
        }

        return foundObstacle;
    }

    private static boolean trailLeft(char[][] matrix, Position p) {
        var found = false;

        int j = p.y();
        while (j>=0 && !found){
            found = matrix[p.x()][j] == LEFT_RIGHT;
            j--;
        }

        return found;
    }

    private static boolean trailRight(char[][] matrix, Position p) {
        var found = false;

        int j = p.y();
        while (j<matrix.length && !found){
            found = matrix[p.x()][j] == LEFT_RIGHT;
            j++;
        }

        return found;
    }

    private static Position getLeftPosition(Position pos){
        return new Position(pos.x(),pos.y()-1);
    }

    private static Position getRightPosition(Position pos){
        return new Position(pos.x(),pos.y()+1);
    }

    private static Position getUpPosition(Position pos){
        return new Position(pos.x()-1,pos.y());
    }

    private static Position getDownPosition(Position pos){
        return new Position(pos.x()+1,pos.y());
    }

    private static boolean isObstacle(char[][] matrix, Position p){
        return matrix[p.x()][p.y()]==OBSTACLE;
    }

    private static Position nextPosition(Position p, Direction d){
        return switch (d){
            case NORTH: yield  new Position(p.x()-1,p.y());
            case SOUTH: yield new Position(p.x()+1,p.y());
            case EAST: yield new Position(p.x(),p.y()+1);
            case WEST: yield new Position(p.x(),p.y()-1);
        };
    }
}
