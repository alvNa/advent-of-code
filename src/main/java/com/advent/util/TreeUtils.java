package com.advent.util;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static <T> boolean isLeaf(Tree.Node<T> node) {
        return node.getChildren() == null || node.getChildren().isEmpty();
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

    public static <T> List<List<T>> getAllPaths(Tree<T> tree) {
        List<List<T>> paths = new ArrayList<>();
        getAllPathsHelper(tree.getRoot(), new ArrayList<>(), paths);
        return paths;
    }

    private static <T> void getAllPathsHelper(Tree.Node<T> node, List<T> currentPath, List<List<T>> paths) {
        if (node == null) {
            return;
        }

        currentPath.add(node.getData());

        if (isLeaf(node)) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            for (Tree.Node<T> child : node.getChildren()) {
                getAllPathsHelper(child, currentPath, paths);
            }
        }

        currentPath.remove(currentPath.size() - 1);
    }
}
