package com.akrecev.caloriecounter;

public class StepTracker {
    MonthData[] monthToData;
    int targetQuantity = 10000; // целевое значение шагов в день по умолчанию

    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    // задать количество шагов в выбранный день
    void setStepsPerDay(int currentMonth, int currentDay, int stepsPerDay) {
        monthToData[currentMonth].days[currentDay - 1] = stepsPerDay;
    }

    // количество шагов по дням за месяц
    void printStepsByDay(int currentMonth) {
        for (int i = 0; i < monthToData[currentMonth].days.length - 1; i++) {
            System.out.print((i + 1) + " день: " + monthToData[currentMonth].days[i] + ", ");
        }
        System.out.print((monthToData[currentMonth].days.length) + " день: "
                + monthToData[currentMonth].days[monthToData[currentMonth].days.length - 1]);
        System.out.println();
    }

    // общее количество шагов за месяц
    int getTotalSteps(int currentMonth) {
        int totalSteps = 0;
        for (int i = 0; i < monthToData[currentMonth].days.length; i++) {
            totalSteps = totalSteps + monthToData[currentMonth].days[i];
        }
        return totalSteps;
    }

    // максимальное количество шагов в день за месяц
    int getMaxStepsByDay(int currentMonth) {
        int maxSteps = monthToData[currentMonth].days[0];
        for (int i = 0; i < monthToData[currentMonth].days.length; i++) {
            if (monthToData[currentMonth].days[i] > maxSteps) {
                maxSteps = monthToData[currentMonth].days[i];
            }
        }
        return maxSteps;
    }

    // лучшая серия дней с превышением целевого значения
    int findBestSeries(int currentMonth) {
        int bestSeries = 0;
        int currentSeries = 0;
        for (int i = 0; i < monthToData[currentMonth].days.length; i++) {
            if (monthToData[currentMonth].days[i] >= targetQuantity) {
                currentSeries++;
            } else {
                currentSeries = 0;
            }

            if (currentSeries > bestSeries) {
                bestSeries = currentSeries;
            }
        }
        return bestSeries;
    }

    class MonthData {
        int[] days = new int[30];
    }
}