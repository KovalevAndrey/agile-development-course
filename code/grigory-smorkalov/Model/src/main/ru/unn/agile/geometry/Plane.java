package ru.unn.agile.geometry;

public class Plane {
    private Point point;
    private Point normal;

    public Plane(Point point, Point normal) {
        this.point = point;
        this.normal = normal;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getNormal() {
        return normal;
    }

    public void setNormal(Point normal) {
        this.normal = normal;
    }
}
