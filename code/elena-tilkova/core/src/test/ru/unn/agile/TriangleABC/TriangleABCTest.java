package ru.unn.agile.TriangleABC;

import ru.unn.agile.TriangleABC.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleABCTest
{
    public final double delta = 0.001;

    @Test
    public void canCreateTriangleABC()
    {
        Point pointA = new Point(5, 5);
        Point pointB = new Point(4, 5);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertNotNull(triangle);
    }

    @Test
    public void canGetPerimeter()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(12.0, triangle.perimeter(), delta);
    }

    @Test
    public void canGetPerimeterWithPointNegativeCoordinate()
    {
        Point pointA = new Point(0, -3);
        Point pointB = new Point(-4, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(12.0, triangle.perimeter(), delta);
    }

    @Test
    public void canGetPerimeterWithTwoEqualPoint()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(6.0, triangle.perimeter(), delta);
    }

    @Test
    public void canGetPerimeterWithThreeEqualPoint()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.perimeter(), delta);
    }

    @Test
    public void canGetSquare()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(6.0, triangle.square(), delta);
    }

    @Test
    public void canGetSquareWithPointNegativeCoordinate()
    {
        Point pointA = new Point(0, -3);
        Point pointB = new Point(-4, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(6.0, triangle.square(), delta);
    }


    @Test
    public void canGetSquareWithTwoEqualPoint()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.square(), delta);
    }

    @Test
    public void canGetSquareWithThreeEqualPoint()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.square(), delta);
    }

    @Test
    public void canGetCircumradius()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(4, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(2.5, triangle.circumradius(), delta);
    }

    @Test
    public void canGetCircumradiusWithPointNegativeCoordinate()
    {
        Point pointA = new Point(0, -3);
        Point pointB = new Point(-4, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(2.5, triangle.circumradius(), delta);
    }

    @Test
    public void canGetCircumradiusWithTwoEqualPoint()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(1.5, triangle.circumradius(), delta);
    }

    @Test
    public void canGetCircumradiusWithThreeEqualPoint()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.circumradius(), delta);
    }

    @Test
    public void canGetInRadius()
    {
        Point pointA = new Point(0.5, Math.sqrt(3.0) / 2);
        Point pointB = new Point(1, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(Math.sqrt(3.0) / 6, triangle.inRadius(), delta);
    }

    @Test
    public void canGetInRadiusWithPointNegativeCoordinate()
    {
        Point pointA = new Point(-0.5, -Math.sqrt(3.0) / 2);
        Point pointB = new Point(-1, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(Math.sqrt(3.0) / 6, triangle.inRadius(), delta);
    }

    @Test
    public void canGetInRadiusWithTwoEqualPoint()
    {
        Point pointA = new Point(0, 3);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.inRadius(), delta);
    }

    @Test
    public void canGetInRadiusWithThreeEqualPoint()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 0);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.inRadius(), delta);
    }

    @Test
    public void canGetSideLength()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(5, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(10.0, triangle.sideLength("AB"), delta);
    }

    @Test
    public void canGetSideLengthWithTwoEqualPoint()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.sideLength("AC"), delta);
    }

    @Test
    public void canNotGetSideLengthForWrongSideName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.sideLength("BD"), delta);
    }

    @Test
    public void canNotGetSideLengthForWrongLengthSideName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.sideLength("ABC"), delta);
    }

    @Test
    public void canGetCornerMesure()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 1);
        Point pointC = new Point(1, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(1.57, triangle.cornerMesure("A"), delta);
    }

    @Test
    public void canGetCornerMesureWithTwoEqualPoint()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.cornerMesure("A"), delta);
    }

    @Test
    public void canNotGetSCornerMesureForWrongSideName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.cornerMesure("D"), delta);
    }

    @Test
    public void canNotGetCornerMesureForWrongLengthSideName()
    {
        Point pointA = new Point(0, 0);
        Point pointB = new Point(0, 10);
        Point pointC = new Point(0, 0);
        TriangleABC triangle = new TriangleABC(pointA, pointB, pointC);
        assertEquals(0.0, triangle.cornerMesure("ABC"), delta);
    }
}

