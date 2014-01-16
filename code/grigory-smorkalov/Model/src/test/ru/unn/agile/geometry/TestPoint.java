package ru.unn.agile.geometry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestPoint {
    @Test
    public void scalarMultiplyZeroPoint() {
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 2, 3);

        double result = p1.scalarMultiply(p2);

        assertEquals(result, 0.0, Point.ACCURACY);
    }

    @Test
    public void scalarMultiplyNormal() {
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);

        double result = p1.scalarMultiply(p2);

        assertEquals(result, 32.0, Point.ACCURACY);
    }

    @Test
    public void minusNormal() {
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);

        Point result = p1.minus(p2);

        assertEquals(result, new Point(3, 3, 3));
    }

    @Test
    public void plusNormal() {
        Point p1 = new Point(4, 5, 6);
        Point p2 = new Point(1, 2, 3);

        Point result = p1.plus(p2);

        assertEquals(result, new Point(5, 7, 9));
    }

    @Test
    public void multiplyDouble() {
        Point p1 = new Point(4, 5, 6);
        double val = 3.0;

        Point result = p1.multiply(val);

        assertEquals(result, new Point(12, 15, 18));
    }
}
