package ru.unn.agile.TriangleABC;

import ru.unn.agile.TriangleABC.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest
{
    public final double delta = 0.001;
    @Test
    public void canCreatePoint()
    {
        Point point = new Point(1, 1);
        assertNotNull(point);
    }
    @Test
    public void canCreatePointWithoutInitial()
    {
        Point point = new Point();
        assertNotNull(point);
    }
    @Test
    public void canSetInitialFirstCoordinate()
    {
        Point point = new Point(1,1);
        assertEquals(1.0, point.getFirstCoordinate(), delta);
    }
    @Test
    public void canSetInitialSecondCoordinate()
    {
        Point point = new Point(1,1);
        assertEquals(1.0, point.getSecondCoordinate(), delta);
    }
    @Test
    public void areEqualPointsEqual ()
    {
        Point point1 = new Point(1,1);
        Point point2 = new Point(1,1);
        assertTrue(point1.equals(point2));
    }
    @Test
    public void arePointsWithDifferentFirstCoordinateNotEqual()
    {
        Point point1 = new Point(1.5, 10);
        Point point2 = new Point(6.23, 10);
        assertFalse(point1.equals(point2));
    }

    @Test
    public void arePointsWithDifferentSecondCoordinateNotEqual()
    {
        Point point1 = new Point(1.5, 5.22);
        Point point2 = new Point(1.5, 10);
        assertFalse(point1.equals(point2));
    }
    @Test
    public void isDistanceBetweenPointsRight()
    {
        Point point1 = new Point(10,0);
        Point point2 = new Point(0,0);
        assertEquals(10.0, point1.getDistance(point2), delta);
    }

    @Test
    public void isDistanceBetweenPointsNegativeCoordinateRight()
    {
        Point point1 = new Point(-10,0);
        Point point2 = new Point(-2,0);
        assertEquals(8.0, point1.getDistance(point2), delta);
    }

    @Test
    public void isDistanceBetweenEqualPointsRight()
    {
        Point point1 = new Point(5,0);
        Point point2 = new Point(5,0);
        assertEquals(0.0, point1.getDistance(point2), delta);
    }

}
