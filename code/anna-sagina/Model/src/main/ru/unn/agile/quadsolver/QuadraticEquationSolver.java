package ru.unn.agile.quadsolver;

import java.security.InvalidParameterException;

public class QuadraticEquationSolver {
    protected static final double delta = 1e-10;

    public static double[] solve(double a, double b, double c) {
        if (a == 0 && b == 0 && c == 0)
            throw new InvalidParameterException("All arguments cannot be zero!");
        if (a == 0 && b == 0 && c != 0)
            throw new InvalidParameterException("Equation cannot be solve!");

        double[] X;

        if (a == 0) {
            X = new double[1];
            X[0] = -c / b;
            return X;
        }

        double D = b * b - 4 * a * c;

        if (Math.abs(D) <= delta) {
            X = new double[1];
            X[0] = -b / (2 * a);
            return X;
        }

        if (D < 0){
            X = new double[]{};
            return X;
        }

        X = new double[2];
        X[0] = (-b - Math.sqrt(D)) / (2 * a);
        X[1] = (-b + Math.sqrt(D)) / (2 * a);
        return X;
    }

}
