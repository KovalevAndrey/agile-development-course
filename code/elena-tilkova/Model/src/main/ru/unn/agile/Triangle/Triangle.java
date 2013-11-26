package ru.unn.agile.Triangle;

import ru.unn.agile.Triangle.Point;

public class Triangle
{
    private Point A;
    private Point B;
    private Point C;

    public Triangle(Point pointA, Point pointB, Point pointC)
    {
        if (pointA.equals(pointB) || pointA.equals(pointC) || pointB.equals(pointC))
            throw new IllegalArgumentException("Points must be different.");

        if (pointA.collinear(pointB, pointC))
            throw new IllegalArgumentException("Points can't be collinear.");

        A = new Point(pointA.getFirstCoordinate(), pointA.getSecondCoordinate());
        B = new Point(pointB.getFirstCoordinate(), pointB.getSecondCoordinate());
        C = new Point(pointC.getFirstCoordinate(), pointC.getSecondCoordinate());
    }

    public double perimeter()
    {
        double perimeter = 0;
        perimeter = A.getDistance(B) + A.getDistance(C) + B.getDistance(C);
        return perimeter;
    }

    public double square()
    {
        double square = 0;
        double AB = A.getDistance(B);
        double AC = A.getDistance(C);
        double BC = B.getDistance(C);
        double halfPerimeter = (AB + BC + AC) / 2;

        square = Math.sqrt(halfPerimeter * (halfPerimeter - AB) * (halfPerimeter - AC) * (halfPerimeter - BC));

        return square;
    }

    public double circumradius()
    {
        double circumradius = 0;
        double square = square();
        double a = A.getDistance(B) * A.getDistance(C) * B.getDistance(C) / 4;

        circumradius = a / square;

        return circumradius;
    }

    public double inRadius()
    {
        double inRadius = 2 * square() / perimeter();

        return inRadius;
    }

    public double sideLength(String nameSide)
    {
        double sideLength = 0;
        if (nameSide.length() == 2)
        {
            boolean isWrongSideName = true;
            Point point1 = new Point(0, 0);
            Point point2 = new Point(0, 0);

            if (nameSide.substring(0, 1).equals("A"))
            {
                point1.setCoordinates(A);
                isWrongSideName = false;
            }
            if (nameSide.substring(0, 1).equals("B"))
            {
                point1.setCoordinates(B);
                isWrongSideName = false;
            }
            if (nameSide.substring(0, 1).equals("C"))
            {
                point1.setCoordinates(C);
                isWrongSideName = false;
            }

            if (isWrongSideName) throw new IllegalArgumentException("The name of side is wrong.");

            isWrongSideName = true;
            if (nameSide.substring(1, 2).equals("A"))
            {
                point2.setCoordinates(A);
                isWrongSideName = false;
            }
            if (nameSide.substring(1, 2).equals("B"))
            {
                point2.setCoordinates(B);
                isWrongSideName = false;
            }
            if (nameSide.substring(1, 2).equals("C"))
            {
                point2.setCoordinates(C);
                isWrongSideName = false;
            }
            if (isWrongSideName) throw new IllegalArgumentException("The name of side is wrong.");

            sideLength = point1.getDistance(point2);
        }
        else
            throw new IllegalArgumentException("The name of side is wrong.");

        return sideLength;
    }

    public double angle(String nameCorner)
    {
        double angle = 0;
        if (nameCorner.length() == 1)
        {
            Point point1 = new Point(0, 0);
            Point point2 = new Point(0, 0);
            Point point3 = new Point(0, 0);

            boolean isWrongSideName = true;
            if (nameCorner.equals("A"))
            {
                point1.setCoordinates(B);
                point2.setCoordinates(C);
                point3.setCoordinates(A);
                isWrongSideName = false;
            }
            if (nameCorner.equals("B"))
            {
                point1.setCoordinates(A);
                point2.setCoordinates(C);
                point3.setCoordinates(B);
                isWrongSideName = false;
            }
            if (nameCorner.equals("C"))
            {
                point1.setCoordinates(B);
                point2.setCoordinates(A);
                point3.setCoordinates(C);
                isWrongSideName = false;
            }
            if (isWrongSideName) throw new IllegalArgumentException("The name of angle is wrong.");

            double cosAngle = (Math.pow(point3.getDistance(point1), 2) +
                    Math.pow(point3.getDistance(point2), 2) -
                    Math.pow(point1.getDistance(point2), 2)) /
                    (2 * point3.getDistance(point1) * point3.getDistance(point2));

            angle = Math.acos(cosAngle);
        }
        else
            throw new IllegalArgumentException("The name of angle is wrong.");

        return angle;
    }
}
