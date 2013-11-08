package ru.unn.agile.geometry;

public class IntersectionComputer {
    public IntersectionComputer() {

    }

    public Point compute(Plain plain, Line line) {
        if (plain == null && line != null) {
            throw new RuntimeException("Null plain passed");
        }
        if (plain != null && line == null) {
            throw new RuntimeException("Null line passed");
        }
        if (plain == null && line == null) {
            throw new RuntimeException("Null plain and line passed");
        }

        Point linePlainPointsDiff = plain.getPoint().minus(line.getPoint());
        double linePointOrtToPlain = plain.getNormal().scalarMultiply(linePlainPointsDiff);

        if (areLineAndPlainParallel(plain, line)) {
            if (Math.abs(linePointOrtToPlain) < Point.ACCURACY) {
                return line.getPoint();
            } else {
                return null;
            }
        } else {
            double linePointToIntersectionDistance = linePointOrtToPlain / (plain.getNormal().scalarMultiply(line.getDirection()));
            return line.getPoint().plus(line.getDirection().multiply(linePointToIntersectionDistance));
        }
    }

    public boolean areLineAndPlainParallel(Plain plain, Line line) {
        return Math.abs(plain.getNormal().scalarMultiply(line.getDirection())) < Point.ACCURACY;
    }
}
