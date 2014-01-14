package ru.unn.agile.geometry;

public class IntersectionComputer {
    public IntersectionComputer() {

    }

    public Point compute(Plane plane, Line line) {
        if (plane == null && line != null) {
            throw new RuntimeException("Null plane passed");
        }
        if (plane != null && line == null) {
            throw new RuntimeException("Null line passed");
        }
        if (plane == null && line == null) {
            throw new RuntimeException("Null plane and line passed");
        }

        Point linePlainPointsDiff = plane.getPoint().minus(line.getPoint());
        double linePointOrtToPlain = plane.getNormal().scalarMultiply(linePlainPointsDiff);

        if (areLineAndPlainParallel(plane, line)) {
            if (Math.abs(linePointOrtToPlain) < Point.ACCURACY) {
                return line.getPoint();
            } else {
                return null;
            }
        } else {
            double linePointToIntersectionDistance = linePointOrtToPlain / (plane.getNormal().scalarMultiply(line.getDirection()));
            return line.getPoint().plus(line.getDirection().multiply(linePointToIntersectionDistance));
        }
    }

    public boolean areLineAndPlainParallel(Plane plane, Line line) {
        return Math.abs(plane.getNormal().scalarMultiply(line.getDirection())) < Point.ACCURACY;
    }
}
