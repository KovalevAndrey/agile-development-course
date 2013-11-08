package ru.unn.agile.Triangle;

import ru.unn.agile.Triangle.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest
{
    public final double delta = 0.001;

    @Test
    public void canCreateTriangle()
    {
        Point pointA = new Point(5, 5);
        Point pointB = new Point(4, 5);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertNotNull(triangle);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotCreateTriangleWhihtTwoEqualPoints()
    {
        Point pointA = new Point(5, 5);
        Point pointB = new Point(5, 5);
        Point pointC = new Point(0, 0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotCreateTriangleWhihtThreeEqualPoints()
    {
        Point pointA = new Point(5, 5);
        Point pointB = new Point(5, 5);
        Point pointC = new Point(5, 0);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotCreateTriangleWhihtCollinearPoints()
    {
        Point pointA = new Point(1, 5);
        Point pointB = new Point(2, 5);
        Point pointC = new Point(5, 5);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
    }

    @Test
    public void canGetPerimeter()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(12.0, triangle.perimeter(), delta);
    }

    @Test
    public void canGetPerimeterWithPointNegativeCoordinate()
    {
        Point pointA = new Point(0, -3);
        Point pointB = new Point(-4, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(12.0, triangle.perimeter(), delta);
    }

    @Test
    public void canGetSquare()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(6.0, triangle.square(), delta);
    }

    @Test
    public void canGetSquareWithPointNegativeCoordinate()
    {
        Point pointA = new Point(0, -3);
        Point pointB = new Point(-4, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(6.0, triangle.square(), delta);
    }

    @Test
    public void canGetCircumradius()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(2.5, triangle.circumradius(), delta);
    }

    @Test
    public void canGetCircumradiusWithPointNegativeCoordinate()
    {
        Point pointA = new Point(0, -3);
        Point pointB = new Point(-4, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(2.5, triangle.circumradius(), delta);
    }

    @Test
    public void canGetInRadius()
    {
        Point pointA = new Point(0.5, Math.sqrt(3.0) / 2);
        Point pointB = new Point(1, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(Math.sqrt(3.0) / 6, triangle.inRadius(), delta);
    }

    @Test
    public void canGetInRadiusWithPointNegativeCoordinate()
    {
        Point pointA = new Point(-0.5, -Math.sqrt(3.0) / 2);
        Point pointB = new Point(-1, 0);
        Point pointC = new Point(0, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(Math.sqrt(3.0) / 6, triangle.inRadius(), delta);
    }

    @Test
    public void canGetSideLength()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(5, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(10.0, triangle.sideLength("AB"), delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetSideLengthForWrongSideName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(9, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        triangle.sideLength("DB");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetSideLengthForWrongSideNameSecondLetter()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(9, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        triangle.sideLength("AF");
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetSideLengthForWrongLengthSideName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(9, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        triangle.sideLength("ABC");
    }

    @Test
    public void canGetAngle()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 1);
        Point pointC = new Point(1, 0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);
        assertEquals(1.57, triangle.angle("A"), delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void canNotGetAngleForWrongAngleName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(10, 9);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        triangle.angle("D");
    }
}

