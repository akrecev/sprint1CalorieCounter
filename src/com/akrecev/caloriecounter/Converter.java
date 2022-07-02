package com.akrecev.caloriecounter;

public class Converter {

    final int FOR_DISTANCE = 750;
    final int FOR_KILOCALORIES = 20;

    double convertToKilometer(int steps) {
        return steps / FOR_DISTANCE;
    }

    double convertToKCalories(int steps) {
        return steps / FOR_KILOCALORIES;
    }
}
