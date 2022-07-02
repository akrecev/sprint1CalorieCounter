package com.akrecev.caloriecounter;

import java.util.Scanner;

public class Main {
    static int currentMonth;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StepTracker stepTracker = new StepTracker();
        Converter converter = new Converter();

        printMenu();

        if (scanner.hasNextInt()) {
            int userInput = scanner.nextInt();

            while (userInput != 0) {
                // обработка разных случаев
                switch (userInput) {

                    case 1:
                        // ввод месяца начиная с 0
                        enterMonth();

                        // ввод дня
                        System.out.println("Введите день - число от 1 до 30");
                        int currentDay;
                        do {
                            if (scanner.hasNextInt()) {
                                currentDay = scanner.nextInt();
                                if (currentDay < 1 || currentDay > 30) {
                                    System.out.println("Введите число от 1 до 30");
                                }
                            } else {
                                printWarning();
                                return;
                            }
                        } while (currentDay < 1 || currentDay > 30);

                        // ввод шагов
                        System.out.println("Введите количество пройденных шагов в этот день");
                        int stepsPerDay;
                        do {
                            if (scanner.hasNextInt()) {
                                stepsPerDay = scanner.nextInt();
                                if (stepsPerDay < 0) {
                                    System.out.println("Необходимо ввести неотрицательное число");
                                }
                            } else {
                                printWarning();
                                return;
                            }
                        } while (stepsPerDay < 0);

                        stepTracker.setStepsPerDay(currentMonth, currentDay, stepsPerDay);

                        break;

                    case 2:
                        // вывод статистики за определенный месяц
                        enterMonth();

                        // количество шагов по дням за месяц
                        stepTracker.printStepsByDay(currentMonth);
                        // общее количество шагов
                        System.out.println("Общее количество пройденных шагов в выбранном месяце: " +
                                stepTracker.getTotalSteps(currentMonth));
                        // максимальное количество шагов
                        System.out.println("Максимальное количество пройденных шагов в день: " +
                                stepTracker.getMaxStepsByDay(currentMonth));
                        // среднее количество шагов
                        System.out.println("Среднее количество пройденных шагов в день: " +
                                stepTracker.getTotalSteps(currentMonth) / stepTracker.monthToData[currentMonth].days.length);
                        // пройденное расстояние
                        System.out.println("Пройденная дистанция: " +
                                converter.convertToKilometer(stepTracker.getTotalSteps(currentMonth)) + " км");
                        // сожженные килокалории
                        System.out.println("Количество сожжённых килокалорий: " +
                                converter.convertToKCalories(stepTracker.getTotalSteps(currentMonth)));
                        // лучшая серия
                        System.out.println("Лучшая серия: максимальное количество подряд идущих дней, в течение которых");
                        System.out.println("    количество шагов за день было равно или выше целевого: " +
                                stepTracker.findBestSeries(currentMonth));
                        System.out.println("Текущее целевое значение: " + stepTracker.targetQuantity);

                        break;

                    case 3:
                        // изменение целевого количества шагов
                        System.out.println("Введите новое значение целевого количества шагов");
                        System.out.println("Значение по умолчанию - 10000 шагов в день");
                        System.out.println("Введённое значение не должно быть отрицательным");
                        int targetQuantity;
                        do {
                            if (scanner.hasNextInt()) {
                                targetQuantity = scanner.nextInt();
                                if (targetQuantity < 0) {
                                    System.out.println("Введённое значение не должно быть отрицательным");
                                }
                            } else {
                                printWarning();
                                return;
                            }
                        } while (targetQuantity < 0);

                        stepTracker.targetQuantity = targetQuantity;
                        System.out.println("Новое значение целевого количества шагов в день: " + stepTracker.targetQuantity);

                        break;
                    default:
                        System.out.println("Вы ввели неверную команду");
                }

                printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
                userInput = scanner.nextInt(); // повторное считывание данных от пользователя
            }
            System.out.println("Программа завершена");
        } else {
            printWarning();
        }

    }

    private static void printMenu() {
        System.out.println("Выберите одно из действий:");
        System.out.println("1 - Ввести количество шагов за определенный день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }

    private static void printWarning() {
        System.out.println("Необходимо ввести целое число. Программа остановлена");
    }

    private static void enterMonth() {
        System.out.println("Введите номер месяца");
        System.out.println("нумерация идет с нуля: 0 - январь, 1 - февраль, и т.д.)");
        int currentMonth;
        do {
            if (scanner.hasNextInt()) {
                currentMonth = scanner.nextInt();
                if (currentMonth < 0 || currentMonth > 11) {
                    System.out.println("Введите месяц - число от 0 (январь) до 11 (декабрь)");
                }
                return;
            }
            return;
        } while (currentMonth < 0 || currentMonth > 11);
    }
}
