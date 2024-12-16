package com.advent.exercise13;

import com.advent.exercise10.HeightEntry;
import com.advent.exercise10.Position;
import com.advent.util.Tree;
import com.advent.util.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise13 {

    private static final Map<Tuple2<Long,Integer>,Long> cache = new HashMap<>();

    public static int minTokenForAllPrices(List<ClawMachine> clawMachines){
        //var l = clawMachines.stream().map(Exercise13::calculateMin).toList();
        calculateMin(clawMachines.get(0));
        return 0;
    }

    private static int calculateMin(ClawMachine clawMachine) {
        var tree = buildClawTree(clawMachine);
        return 0;
    }

    private static Tree<ClawState> buildClawTree(ClawMachine clawMachine) {
        var tree = new Tree<>(new ClawState(0,0,0,0,0));
        tree.getRoot().setChildren(new ArrayList<>());
        addNodes(tree.getRoot(), clawMachine);
        return tree;
    }

    private static void addNodes(Tree.Node<ClawState> node, ClawMachine clawMachine) {
        var priceX= clawMachine.price.get(0).operator2();
        var priceY= clawMachine.price.get(1).operator2();

        if (node.getData().currentXValue()<priceX &&
                node.getData().currentYValue()<priceY ) {
            var buttonA=node.getData().numButtonAPress();
            var buttonB=node.getData().numButtonBPress();

            if (calculatePrizeStatusPulseAOK(node.getData(),clawMachine)){
                var child1 = new Tree.Node<ClawState>();
                child1.setChildren(new ArrayList<>());
                var incrementXA = clawMachine.buttonA.get(0).operator2();
                var incrementYA = clawMachine.buttonA.get(1).operator2();

                child1.setData(new ClawState(buttonA+1,buttonB,
                        node.getData().currentXValue()+incrementXA,
                        node.getData().currentYValue()+incrementYA,
                        node.getData().currentCost()+3));
                node.getChildren().add(child1);
                addNodes(child1,clawMachine);
            }

            if (calculatePrizeStatusPulseBOK(node.getData(),clawMachine)){
                var incrementXB = clawMachine.buttonB.get(0).operator2();
                var incrementYB = clawMachine.buttonB.get(1).operator2();
                var child2 = new Tree.Node<ClawState>();
                child2.setChildren(new ArrayList<>());
                child2.setData(new ClawState(buttonA,buttonB+1,
                        node.getData().currentXValue()+incrementXB,
                        node.getData().currentYValue()+incrementYB,
                        node.getData().currentCost()+1));
                node.getChildren().add(child2);
                addNodes(child2,clawMachine);
            }
        }
        else{
            System.out.println(node);
        }
    }

    static boolean calculatePrizeStatusPulseAOK(ClawState cs, ClawMachine clawMachine){
        var priceX= clawMachine.price.get(0).operator2();
        var priceY= clawMachine.price.get(1).operator2();
        var incrementXA = clawMachine.buttonA.get(0).operator2();
        var incrementYA = clawMachine.buttonA.get(1).operator2();

        return cs.currentXValue()+incrementXA < priceX &&
                cs.currentYValue()+incrementYA < priceY;
    }

    static boolean calculatePrizeStatusPulseBOK(ClawState cs, ClawMachine clawMachine){
        var priceX= clawMachine.price.get(0).operator2();
        var priceY= clawMachine.price.get(1).operator2();
        var incrementXB = clawMachine.buttonB.get(0).operator2();
        var incrementYB = clawMachine.buttonB.get(1).operator2();

        return cs.currentXValue()+incrementXB < priceX &&
                cs.currentYValue()+incrementYB < priceY;
    }
}
