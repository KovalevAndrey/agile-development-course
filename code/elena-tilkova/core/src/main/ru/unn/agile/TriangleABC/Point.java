package ru.unn.agile.TriangleABC;

public class Point
{
    private double x;
    private double y;

    public Point()
    {
        this.x = 0;
        this.y = 0;
    }
    public Point(double a, double b)
    {
        this.x = a;
        this.y = b;
    }

    public double getFirstCoordinate()
    {
        return this.x;
    }

    public void setFirstCoordinate(double coord)
    {
        this.x = coord;
    }

    public double getSecondCoordinate()
    {
        return this.y;
    }

    public void setSecondCoordinate(double coord)
    {
        this.y = coord;
    }

    public boolean equals(Object object)
    {
        Point point = (Point) object;
        if (point.getFirstCoordinate() == getFirstCoordinate() && point.getSecondCoordinate() == getSecondCoordinate())
            return true;
        else
            return false;
    }

    public double getDistance(Object object)
    {
        Point point = (Point) object;
        double distance = Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
        return distance;
    }

    public void setCoordinates(Object object)
    {
        Point point = (Point) object;
        this.setFirstCoordinate(point.getFirstCoordinate());
        this.setSecondCoordinate(point.getSecondCoordinate());
    }
}
