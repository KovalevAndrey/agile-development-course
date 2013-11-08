package ru.unn.agile.TriangleABC;

public class TriangleABC
{
    private Point A;
    private Point B;
    private Point C;

    public TriangleABC(Point point1, Point point2, Point point3)
    {
        A = new Point(point1.getFirstCoordinate(), point1.getSecondCoordinate());
        B = new Point(point2.getFirstCoordinate(), point2.getSecondCoordinate());
        C = new Point(point3.getFirstCoordinate(), point3.getSecondCoordinate());
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

        if (!A.equals(B) && !A.equals(C) && !B.equals(C))
            square = Math.sqrt(halfPerimeter * (halfPerimeter - AB) * (halfPerimeter - AC) * (halfPerimeter - BC));

        return square;
    }

    public double circumradius()
    {
        double circumradius = 0;
        double square = square();
        double a = A.getDistance(B) * A.getDistance(C) * B.getDistance(C) / 4;

        if (!A.equals(B) && !A.equals(C) && !B.equals(C))
            circumradius = a / square;
        else
            circumradius = perimeter() / 4;

        return circumradius;
    }

    public double inRadius()
    {
        double inRadius = 0;

        if (!A.equals(B) && !A.equals(C) && !B.equals(C))
            inRadius = 2 * square() / perimeter();

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

            if (!isWrongSideName)
            {
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
            }

            if (!isWrongSideName)
                sideLength = point1.getDistance(point2);
        }
        return sideLength;
    }

    public double cornerMesure(String nameCorner)
    {
        double cornerMesure = 0;
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
            if (!isWrongSideName && !A.equals(B) && !A.equals(C) && !B.equals(C))
            {
                double cosCorner = (Math.pow(point3.getDistance(point1), 2) +
                                    Math.pow(point3.getDistance(point2), 2) -
                                    Math.pow(point1.getDistance(point2), 2)) /
                                   (2 * point3.getDistance(point1) * point3.getDistance(point2));

                cornerMesure = Math.acos(cosCorner);
            }
        }
        return cornerMesure;
    }
}
