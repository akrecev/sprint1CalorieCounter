package com.akrecev.caloriecounter;

public class Converter {

    final int FOR_DISTANCE = 750;
    final int FOR_KILOCALORIES = 20;

    double convertDistance;
    double convertKCalories;

    double convertToKilometer(int steps) {
        convertDistance = steps / FOR_DISTANCE;
        return convertDistance;
    }

    double convertToKCalories(int steps) {
        convertKCalories = steps / FOR_KILOCALORIES;
        return convertKCalories;
    }
}
