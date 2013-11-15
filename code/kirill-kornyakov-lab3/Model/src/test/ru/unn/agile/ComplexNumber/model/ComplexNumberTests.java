package ru.unn.agile.ComplexNumber.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexNumberTests {
    public final double delta = 0.001;

    @Test
    public void canCreateComplexNumberWithInitialValues() {
        ComplexNumber number = new ComplexNumber(1, 1);
        assertNotNull(number);
    }

    @Test
    public void defaultNumberIsZero() {
        ComplexNumber z = new ComplexNumber();
        assertEquals(new ComplexNumber(0, 0), z);
    }

    @Test
    public void canSetInitialRealValue() {
        ComplexNumber number = new ComplexNumber(1, 2);
        assertEquals(1.0, number.getReal(), delta);
    }

    @Test
    public void canSetInitialImaginaryValue() {
        ComplexNumber number = new ComplexNumber(1, 2);
        assertEquals(2.0, number.getImaginary(), delta);
    }

    @Test
    public void areEqualNumbersEqual() {
        ComplexNumber z1 = new ComplexNumber(3.14, 2.73);
        ComplexNumber z2 = new ComplexNumber(3.14, 2.73);
        assertTrue(z1.equals(z2));
    }

    @Test
    public void areNumbersWithDifferentRealPartNotEqual() {
        ComplexNumber z1 = new ComplexNumber(1.00, 2.73);
        ComplexNumber z2 = new ComplexNumber(3.14, 2.73);
        assertFalse(z1.equals(z2));
    }

    @Test
    public void areNumbersWithDifferentImaginaryPartNotEqual() {
        ComplexNumber z1 = new ComplexNumber(3.14, 1.00);
        ComplexNumber z2 = new ComplexNumber(3.14, 2.73);
        assertFalse(z1.equals(z2));
    }

    @Test
    public void canAddNumbers() {
        ComplexNumber z1 = new ComplexNumber(1, 2);
        ComplexNumber z2 = new ComplexNumber(3, 4);
        ComplexNumber sum = z1.add(z2);
        assertEquals(new ComplexNumber(4, 6), sum);
    }

    @Test
    public void canAddNumbersWithNegativePart() {
        ComplexNumber z1 = new ComplexNumber(-1, 2);
        ComplexNumber z2 = new ComplexNumber(3, -4);
        ComplexNumber sum = z1.add(z2);
        assertTrue(sum.equals(new ComplexNumber(2, -2)));
    }

    @Test
    public void canFormatSumOfNumbersWithNegativePart() {
        ComplexNumber z1 = new ComplexNumber(1.2, 2.3);
        ComplexNumber z2 = new ComplexNumber(-10.4, -20.5);
        ComplexNumber sum = z1.add(z2);
        assertEquals("-9.2 - 18.2i", sum.toString());
    }

    @Test
    public void canMultiplyNumbers() {
        ComplexNumber z1 = new ComplexNumber(1, 2);
        ComplexNumber z2 = new ComplexNumber(3, 4);
        ComplexNumber sum = z1.multiply(z2);
        assertEquals(new ComplexNumber(-5, 10), sum);
    }
}
