package com.advent.exercise10;

import com.advent.util.Tree;

import java.util.ArrayList;
import java.util.List;

import static com.advent.util.TreeUtils.getAllLeafNodes;
import static com.advent.util.TreeUtils.getAllPaths;

public class Exercise10 {

    public static int sumOfTrailHeadsRatings(int[][] topographicMap) {
        return getTrailHeads(topographicMap).stream()
                .map(trailHead-> buildHeightTree(trailHead, topographicMap))
                .map(Exercise10::calculateRating)
                .reduce(0, Integer::sum);
    }

    public static int sumOfTrailHeadsScores(int[][] topographicMap) {
        return getTrailHeads(topographicMap).stream()
                .map(trailHead-> buildHeightTree(trailHead, topographicMap))
                .map(Exercise10::calculateScore)
                .reduce(0, Integer::sum);
    }

    private static int calculateRating(Tree<HeightEntry> tree) {
        return (int) getAllPaths(tree).stream()
                .filter(path -> path.size() == 10)
                .count();
    }

    private static int calculateScore(Tree<HeightEntry> tree) {
        return (int) getAllLeafNodes(tree.getRoot()).stream()
                .filter(node -> node.getData().height() == 9)
                .distinct()
                .count();
    }

    private static Tree<HeightEntry> buildHeightTree(Position trailHead, int[][] topographicMap) {
        var tree = new Tree<>(new HeightEntry(trailHead, 0));
        addNodes(tree.getRoot(), topographicMap);
        return tree;
    }

    private static void addNodes(Tree.Node<HeightEntry> node, int[][] topographicMap) {
        var height = node.getData().height();
        var positions = getValidAdjacentPositions(topographicMap, node);

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
}
