package ru.unn.agile.fraction.model;

public class GreatestCommonDivisor {

    public static int calculate(int first, int second) {
        if (first == 0 && second == 0)
            return 1;

        return Math.abs(second == 0 ? first : calculate(second, first % second));
    }
}
