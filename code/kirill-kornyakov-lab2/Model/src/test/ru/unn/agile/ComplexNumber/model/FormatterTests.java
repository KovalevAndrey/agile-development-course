package ru.unn.agile.ComplexNumber.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatterTests {

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnNegativeDouble() {
        double x = -1.0;
        Formatter.formatPositiveDouble(x);
    }

    @Test
    public void canFormatToIntegerWith6Digits() {
        double x = 123456.123;
        assertEquals("123456.12", Formatter.formatPositiveDouble(x));
    }

    @Test
    public void canConvertToString() {
        ComplexNumber z = new ComplexNumber(1, 2);
        assertEquals("1.0 + 2.0i", z.toString());
    }

    @Test
    public void canConvertFloatingComplexNumberToString() {
        ComplexNumber z = new ComplexNumber(3.14, 2);
        assertEquals("3.14 + 2.0i", z.toString());
    }

    @Test
    public void canConvertScientificFormatToString() {
        ComplexNumber z = new ComplexNumber(1, 1.2456e-2);
        assertEquals("1.0 + 0.01i", z.toString());
    }

    @Test
    public void canConvertNegativeRealPartToString() {
        ComplexNumber z = new ComplexNumber(-1, 1);
        assertEquals("-1.0 + 1.0i", z.toString());
    }

    @Test
    public void canConvertNegativeImaginaryPartToString() {
        ComplexNumber z = new ComplexNumber(1, -1);
        assertEquals("1.0 - 1.0i", z.toString());
    }
}
