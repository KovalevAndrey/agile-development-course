package ru.unn.agile.geometry;

public class Line {
    private Point point;
    private Point direction;

    public Line(Point point, Point direction) {
        this.point = point;
        this.direction = direction;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
