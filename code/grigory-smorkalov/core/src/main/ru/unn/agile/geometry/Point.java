package ru.unn.agile.geometry;

public class Point {
    private double x;
    private double y;
    private double z;

    public static double ACCURACY = 1E-10;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Point))
            return false;

        Point rhs = (Point) obj;
        return (Math.abs(rhs.getX() - x) < ACCURACY) && (Math.abs(rhs.getY() - y) < ACCURACY) && (Math.abs(rhs.getZ() - z) < ACCURACY);
    }

    public double scalarMultiply(Point rhs) {
        return x*rhs.x + y*rhs.y + z*rhs.z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Point minus(Point rhs) {
        Point result = new Point(0, 0, 0);
        result.x = x - rhs.x;
        result.y = y - rhs.y;
        result.z = z - rhs.z;

        return result;
    }

    public Point plus(Point rhs) {
        Point result = new Point(0, 0, 0);
        result.x = x + rhs.x;
        result.y = y + rhs.y;
        result.z = z + rhs.z;

        return result;
    }

    public Point multiply(double val) {
        return new Point(x * val, y * val, z * val);
    }
}
