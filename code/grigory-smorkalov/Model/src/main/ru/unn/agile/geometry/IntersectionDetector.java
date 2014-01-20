package ru.unn.agile.geometry;

public class IntersectionDetector {
    public IntersectionDetector() {

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

        Point linePlanePointsDiff = plane.getPoint().minus(line.getPoint());
        double linePointOrtToPlane = plane.getNormal().scalarMultiply(linePlanePointsDiff);

        if (areLineAndPlaneParallel(plane, line)) {
            if (Math.abs(linePointOrtToPlane) < Point.ACCURACY) {
                return line.getPoint();
            } else {
                return null;
            }
        } else {
            double linePointToIntersectionDistance = linePointOrtToPlane / (plane.getNormal().scalarMultiply(line.getDirection()));
            return line.getPoint().plus(line.getDirection().multiply(linePointToIntersectionDistance));
        }
    }

    public boolean areLineAndPlaneParallel(Plane plane, Line line) {
        return Math.abs(plane.getNormal().scalarMultiply(line.getDirection())) < Point.ACCURACY;
    }
}
