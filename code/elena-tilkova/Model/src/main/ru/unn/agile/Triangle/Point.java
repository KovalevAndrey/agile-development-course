package ru.unn.agile.Triangle;

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

    public boolean equals(Point point)
    {
        if (point.getFirstCoordinate() == getFirstCoordinate() && point.getSecondCoordinate() == getSecondCoordinate())
            return true;
        else
            return false;
    }

    public double getDistance(Point point)
    {
        double distance = Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
        return distance;
    }

    public void setCoordinates(Point point)
    {
        this.setFirstCoordinate(point.getFirstCoordinate());
        this.setSecondCoordinate(point.getSecondCoordinate());
    }

    public boolean collinear(Point point1, Point point2)
    {
        boolean isCollinear = false;
        if (point1.getFirstCoordinate() == point2.getFirstCoordinate())
            if (point1.getFirstCoordinate() == getFirstCoordinate())
                isCollinear = true;
            else
                isCollinear = false;
        else
        if (getSecondCoordinate() == point1.getSecondCoordinate() + (point2.getSecondCoordinate() - point1.getSecondCoordinate()) *
                (getFirstCoordinate() - point1.getFirstCoordinate()) / (point2.getFirstCoordinate() - point1.getFirstCoordinate()))
            isCollinear = true;
        else
            isCollinear = false;

        return isCollinear;
    }
}

