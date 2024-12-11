package com.advent.exercise10;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<>();
        root.data = rootData;
        root.children = new ArrayList<>();
    }

    @Data
    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
}