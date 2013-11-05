package ru.unn.agile.Polynomial;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PolynomialCalculatorTest {

    private PolynomialCalculator calculator = null;

    @Before
    public void setUp() {
        calculator = new PolynomialCalculator();
    }

    @Test
    public void twoEmptyStringGivesEmptyString() {
        assertEquals("", calculator.add("", ""));
    }

    @Test
    public void polynomialAndEmptyStringGivesTheSamePolynomial() {
        assertEquals("2x + 1", calculator.add("2x + 1", ""));
    }

    @Test
    public void emptyStringAndPolynomialGivesTheSamePolynomial() {
        assertEquals("2x + 1", calculator.add("", "2x + 1"));
    }

    @Test
    public void twoPolynomialsGivesSum() {
        assertEquals("6x + 3", calculator.add("2x + 2", "4x + 1"));
    }

    @Test
    public void twoBigPolynomialGivesSum() {
        assertEquals("8x^2 + 4x + 1", calculator.add("4x^2 + 2x + 1", "4x^2 + 2x"));
    }

    @Test
    public void canSubtractTwoPolynomials() {
        assertEquals("5x^3 + -7x + 10", calculator.sub("10x^3 + 13", "5x^3 + 7x + 3"));
    }

    @Test
    public void canMultiplyTwoPolynomials() {
        assertEquals("x^2 + 2x + 1", calculator.mul("x + 1", "x + 1"));
    }

    @Test
    public void multiplyPolynomialAndEmptyStringGivesEmptyString() {
        assertEquals("", calculator.mul("x + 3", ""));
    }

    @Test
    public void sumIncorrectPolynomialsGivesErrorMessage() {
        assertEquals("Incorrect polynomials format", calculator.add("x^2 + qqq", "x^x+"));
    }

    @Test
    public void mulIncorrectPolynomialsGivesErrorMessage() {
        assertEquals("Incorrect polynomials format", calculator.mul("^3", "7"));
    }

    @Test
    public void subIncorrectPolynomialGivesErrorMessage() {
        assertEquals("Incorrect polynomials format", calculator.sub("++x+^5&3 34 1", "0x + 0x^0"));
    }

}
