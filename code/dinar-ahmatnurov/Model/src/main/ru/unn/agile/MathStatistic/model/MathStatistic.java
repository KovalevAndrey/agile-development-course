package ru.unn.agile.MathStatistic;

import static java.lang.Math.pow;

public class MathStatistic {

    public static float calcExpectedValue(float[] inputData) {
        if(inputData.length == 0)
            throw new IllegalArgumentException();
        float result = 0;
        for (float item: inputData) {
            result += item;
        }
        result /= inputData.length;
        return result;
    }

    public static float calcVariance(float[] inputData){
        if(inputData.length == 0)
            throw new IllegalArgumentException();
        return calcMoment(inputData,2);
    }

    public static float calcThirdCentralMoment(float[] inputData) {
        if(inputData.length == 0)
            throw new IllegalArgumentException();
        return calcMoment(inputData,3);
    }

    public static float calcFourthCentralMoment(float[] inputData){
        if(inputData.length == 0)
            throw new IllegalArgumentException();

        return calcMoment(inputData,4);
    }

    private static float calcMoment(float[] inputData, int order)  {
        float expectedValue = calcExpectedValue(inputData);
        float result = 0;
        for (float item: inputData) {
            result += pow((item - expectedValue),order);
        }

        result /= inputData.length;
        return result;
    }
}
