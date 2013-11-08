package ru.unn.agile.calculating;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    private final double calcError = 1e-10;

    @Test
    public void emptyStringGivesZero() {
        double res = StringCalculator.calculate("");
        assertEquals(0.0, res, calcError);
    }

    @Test
    public void oneNumberGivesNumber() {
        double res = StringCalculator.calculate("5");
        assertEquals(5.0, res, calcError);
    }

    @Test
    public void oneRealNumberGivesRealNumber() {
        double res = StringCalculator.calculate("5.7");
        assertEquals(5.7, res, calcError);
    }

    @Test
    public void sumGivesSum() {
        double res = StringCalculator.calculate("5+5");
        assertEquals(5.0 + 5, res, calcError);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongSymbolsGivesException() {
        StringCalculator.calculate("5+a");
    }

    @Test
    public void sumWithSpacesGivesSum() {
        double res = StringCalculator.calculate("5  +5  ");
        assertEquals(5.0 + 5, res, calcError);
    }

    @Test
    public void sumWithParensGivesSum() {
        double res = StringCalculator.calculate("2+(5+5)");
        assertEquals(2.0 + (5 + 5), res, calcError);
    }

    @Test
    public void checkOperationPriorsWithParens() {
        double res = StringCalculator.calculate(" 2 - (5+5)");
        assertEquals(2.0 - (5 + 5), res, calcError);
    }

    @Test
    public void checkMultiply() {
        double res = StringCalculator.calculate(" 2 * (5+5)");
        assertEquals(2.0 * (5 + 5), res, calcError);
    }

    @Test
    public void checkDivision() {
        double res = StringCalculator.calculate(" 2 / (5+5*7)");
        assertEquals(2.0 / (5 + 5 * 7), res, calcError);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongParensGivesException() {
        StringCalculator.calculate("5+5)");
    }

    @Test
    public void checkComplexExpression() {
        double res = StringCalculator.calculate("(1.1-2*7)/19+1+2*(2+2/2)");
        assertEquals((1.1 - 2 * 7) / 19 + 1 + 2 * (2 + 2 / 2), res, calcError);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooFewOpsGivesException() {
        StringCalculator.calculate("5 5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void opInBeginGivesException() {
        StringCalculator.calculate("+5 5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void opInEndGivesException() {
        StringCalculator.calculate("5 5 + ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongOpGivesException() {
        StringCalculator.calculate("5 6 + * 5 ");
    }

    @Test
    public void checkOpsPriorWithDivisionAndMinus() {
        double res = StringCalculator.calculate("1/1-1");
        assertEquals(1.0 / 1 - 1, res, calcError);
    }

    @Test(expected = ArithmeticException.class)
    public void checkDivisionByZeroIsNotAllowed() {
        StringCalculator.calculate("2/(2-2)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkOneOpGiveException() {
        StringCalculator.calculate("+");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkUnaryMinusIsNotAllowed() {
        StringCalculator.calculate("-1");
    }

    @Test
    public void checkOpsPriorParseCorrectly() {
        double res = StringCalculator.calculate("2/1 + 2*8 - (2.7+3)");
        assertEquals(2.0 / 1 + 2 * 8 - (2.7 + 3), res, calcError);
    }

    @Test
    public void checkDivisionIsLeftAssoc() {
        double res = StringCalculator.calculate("2/2/2/2");
        assertEquals(((2.0 / 2) / 2) / 2, res, calcError);
    }

    @Test
    public void checkMinusIsLeftAssoc() {
        double res = StringCalculator.calculate("2-2-2-2");
        assertEquals(((2.0 - 2) - 2) - 2, res, calcError);
    }
}
