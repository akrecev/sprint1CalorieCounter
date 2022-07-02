package com.akrecev.caloriecounter;

public class Converter {

    double convertDistance;
    double convertKCalories;

    double convertToKilometer(int steps) {
        convertDistance = steps * 0.75 / 1000;
        return convertDistance;
    }

    double convertToKCalories(int steps) {
        convertKCalories = steps * 50 / 1000;
        return convertKCalories;
    }
}