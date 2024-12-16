package com.advent.exercise13;

public record ClawState(
    int numButtonAPress, int numButtonBPress,
    int currentXValue, int currentYValue, int currentCost){
}
