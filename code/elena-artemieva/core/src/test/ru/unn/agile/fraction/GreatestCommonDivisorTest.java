package ru.unn.agile.fraction;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GreatestCommonDivisorTest {

    @Test
    public void CanCalculateReduceFraction() {
        assertEquals(8, GreatestCommonDivisor.calculate(16, 24));
    }

    @Test
    public void CanCalculateNoReduceFraction() {
        assertEquals(1, GreatestCommonDivisor.calculate(11, 17));
    }

    @Test
    public void CanFirstArgumentBeZero() {
        assertEquals(32, GreatestCommonDivisor.calculate(0, 32));
    }

    @Test
    public void CanSecondArgumentBeZero() {
        assertEquals(57, GreatestCommonDivisor.calculate(57, 0));
    }

    @Test
    public void CanBothArgumentsBeZero() {
        assertEquals(1, GreatestCommonDivisor.calculate(0, 0));
    }
}
