package com.advent.exercise10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise10 {

    public static int sumOfTrailHeadsScores(int[][] topographicMap) {
        var trailHeads = getTrailHeads(topographicMap);
        var totalScore = 0;

        for (var trailHead : trailHeads) {
            var tree = buildTree(trailHead, topographicMap);
            var score = calculateScore(tree);
            totalScore += score;
        }

        return totalScore;
    }

    private static int calculateScore(Tree<HeightEntry> tree) {
//        return countScores(tree.getRoot());
        var leaves = getAllLeafNodes(tree.getRoot());
        var peaks = leaves.stream()
                .filter(node -> node.getData().height() == 9)
//                .map(node -> node.getData().position())
                .distinct()
                .toList();


//        var nines = getNines(tree.getRoot());
        return peaks.size();
//        return (int) nines.stream()
//                .map(HeightEntry::position)
//                .filter()
//                .distinct()
//                .count();
    }

    private static int countScores(Tree.Node<HeightEntry> node) {
        if(isLeaf(node)) {
            int leafValue = node.getData().height();
            return (leafValue == 9) ? 1 : 0;
        }
        else {
            return node.getChildren().stream()
                    .map(Exercise10::countScores)
                    .reduce(0, Integer::sum);
        }
    }

    private static List<List<HeightEntry>> getNines(Tree.Node<HeightEntry> node) {
        if(isLeaf(node)) {
            int height = node.getData().height();
            if (height == 9) {
                return List.of(List.of(node.getData()));
            }
            else {
                return List.of();
            }
        }
        else {
            return node.getChildren().stream()
                    .flatMap(childNode -> getNines(childNode).stream())
                    .toList();
        }
    }


    private static <T> boolean isLeaf(Tree.Node<T> node) {
        return node.getChildren() == null || node.getChildren().isEmpty();
    }

    private static Tree<HeightEntry> buildTree(Position trailHead, int[][] topographicMap) {
        var tree = new Tree<>(new HeightEntry(trailHead, 0));
        addNodes(tree.getRoot(), /*0,*/ topographicMap);
        return tree;
    }

    private static /*Tree.Node<Position>*/void addNodes(Tree.Node<HeightEntry> node, /*int height,*/ int[][] topographicMap) {
        var height = node.getData().height();
        var positions = getValidAdjacentPositions(topographicMap, node);//.getData().position(), height);

        if (node.getData().height() == 9) {
            for (var position : positions) {
                var leafNode = new Tree.Node<HeightEntry>();
                leafNode.setData(new HeightEntry(position,height));
                leafNode.setChildren(null);

                node.getChildren().add(leafNode);
            }
        }
        else {
            for (var position : positions) {
                var newNode = new Tree.Node<HeightEntry>();
                newNode.setData(new HeightEntry(position,height+1));
                newNode.setChildren(new ArrayList<>());

                node.getChildren().add(newNode);
                addNodes(newNode, topographicMap);
            }
        }
    }

    private static List<Position> getValidAdjacentPositions(int[][] topographicMap, Tree.Node<HeightEntry> node){ //Position trailHead, int height) {
        List<Position> result = new ArrayList<>();
        Position trailHead = node.getData().position();
        int height = node.getData().height();

        if (trailHead.y() + 1 < topographicMap.length && topographicMap[trailHead.x()][trailHead.y()+1] == height + 1) {
            result.add(new Position(trailHead.x(), trailHead.y()+1));
        }

        if (trailHead.x() - 1 >= 0 && topographicMap[trailHead.x() - 1][trailHead.y()] == height + 1) {
            result.add(new Position(trailHead.x() - 1, trailHead.y()));
        }

        if (trailHead.y() - 1 >= 0 && topographicMap[trailHead.x()][trailHead.y()-1] == height + 1) {
            result.add(new Position(trailHead.x(), trailHead.y()-1));
        }

        if (trailHead.x() + 1 < topographicMap.length && topographicMap[trailHead.x() + 1][trailHead.y()] == height + 1) {
            result.add(new Position(trailHead.x() + 1, trailHead.y()));
        }

        return result;
    }


    private static List<Position> getTrailHeads(int[][] topographicMap){
        var result = new ArrayList<Position>();

        for (int i = 0; i < topographicMap.length; i++) {
            for (int j = 0; j < topographicMap[i].length; j++) {
                if (topographicMap[i][j] == 0) {
                    result.add(new Position(i, j));
                }
            }
        }
        return result;
    }

    public static void printAllPathsToLeaf(Tree<HeightEntry> tree) {
        var path = tree.getRoot().getData().position().toString() + " (" + tree.getRoot().getData().height() + ")";
        printAllPathsToLeaf(tree.getRoot(), path);
    }

    public static void printAllPathsToLeaf(Tree.Node<HeightEntry> node, String path) {
        if (isLeaf(node)) {
            if (node.getData().height() == 9) {
                path += node.getData().position().toString() + " (" + node.getData().height() + ")";
                System.out.println(path);
            }
        }
        else{
            for(Tree.Node<HeightEntry> childNode : node.getChildren()) {
                String childPath = path + childNode.getData().position().toString() + " (" + childNode.getData().height() + ")";
                printAllPathsToLeaf(childNode, childPath);
            }
        }
    }

    public static <T> List<Tree.Node<T>> getAllLeafNodes(Tree.Node<T> node) {
        List<Tree.Node<T>> leafNodes = new ArrayList<>();
        if (isLeaf(node)) {
            leafNodes.add(node);
        } else {
            for (Tree.Node<T> child : node.getChildren()) {
                leafNodes.addAll(getAllLeafNodes(child));
            }
        }
        return leafNodes;
    }
}
