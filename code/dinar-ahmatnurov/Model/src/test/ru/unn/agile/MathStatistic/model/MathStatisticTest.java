package ru.unn.agile.MathStatistic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathStatisticTest {
    private MathStatistic mathStatistic;
    private double eps = 1e-5;
    private float[] tinyInputData = {0.0f};
    private float[] mediumInputData = {0.0f, 0.0f, 0.1f, 0.2f, 1.3f, 1.4f, 1.3f, 1.5f, 2.0f, 2.0f, 2.0f, 2.3f, 2.5f};
    private float[] bigInputData  = {0.0f, 0.0f, 0.1f, 0.2f, 1.3f, 1.4f, 1.3f, 1.5f, 2.0f, 2.0f, 2.0f, 2.3f, 2.5f, 2.6f, 3.1f, 4.2f, 4.5f, 5.1f, 5.1f};

    @Before
    public void setUp() {
        mathStatistic = new MathStatistic();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnEmptyDataCalcExpectedValue() {
        float[] inputData = {};
        mathStatistic.calcExpectedValue(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnEmptyDataCalcVariance() {
        float[] inputData = {};
        mathStatistic.calcVariance(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnEmptyDataCalcThirdCentralMoment() {
        float[] inputData = {};
        mathStatistic.calcThirdCentralMoment(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnEmptyDataCalcFourthCentralMoment() {
        float[] inputData = {};
        mathStatistic.calcFourthCentralMoment(inputData);
    }

    @Test
    public void isExpectedValueCorrectOnTinyData() {
        float result;
        result =   mathStatistic.calcExpectedValue(tinyInputData);
        assertEquals(0, result, eps);
    }

    @Test
    public void isExpectedValueCorrectOnMediumData() {
        float result;
        result =   mathStatistic.calcExpectedValue(mediumInputData);
        assertEquals(1.27692, result, eps);
    }

    @Test
    public void isExpectedValueCorrectOnBigData() {
        float result;
        result =   mathStatistic.calcExpectedValue(bigInputData);
        assertEquals(2.16842, result, eps);
    }

    @Test
    public void isVarianceValueCorrectOnTinyData() {
        float result;
        result =   mathStatistic.calcVariance(tinyInputData);
        assertEquals(0, result, eps);
    }

    @Test
    public void isVarianceValueCorrectOnMediumData() {
        float result;
        result =   mathStatistic.calcVariance(mediumInputData);
        assertEquals(0.76793, result, eps);
    }

    @Test
    public void isVarianceValueCorrectOnBigData() {
        float result;
        result =   mathStatistic.calcVariance(bigInputData);
        assertEquals(2.53269, result, eps);
    }

    @Test
    public void isThirdCentralMomentValueCorrectOnTinyData() {
        float result;
        result =   mathStatistic.calcThirdCentralMoment(tinyInputData);
        assertEquals(0, result, eps);
    }

    @Test
    public void isThirdCentralMomentValueCorrectOnMediumData() {
        float result;
        result =   mathStatistic.calcThirdCentralMoment(mediumInputData);
        assertEquals(-0.23043, result, eps);
    }

    @Test
    public void isThirdCentralMomentValueCorrectOnBigData() {
        float result;
        result =   mathStatistic.calcThirdCentralMoment(bigInputData);
        assertEquals(1.75955, result, eps);
    }

    @Test
    public void isFourthCentralMomentValueCorrectOnTinyData() {
        float result;
        result =   mathStatistic.calcFourthCentralMoment(tinyInputData);
        assertEquals( 0, result, eps);
    }

    @Test
    public void isFourthCentralMomentValueCorrectOnMediumData() {
        float result;
        result =   mathStatistic.calcFourthCentralMoment(mediumInputData);
        assertEquals(0.97977, result, eps);
    }

    @Test
    public void isFourthCentralMomentValueCorrectOnBigData() {
        float result;
        result =   mathStatistic.calcFourthCentralMoment(bigInputData);
        assertEquals(14.43848, result, eps);
    }
}

