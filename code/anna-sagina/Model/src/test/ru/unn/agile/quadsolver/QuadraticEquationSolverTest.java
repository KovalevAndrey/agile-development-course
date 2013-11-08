package ru.unn.agile.quadsolver;


import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertArrayEquals;

public class QuadraticEquationSolverTest {

    protected static final double delta = 1e-10;

    @Test(expected = InvalidParameterException.class)
    public void equationWithZeroParametersThrowsException() {
         assertQuadraticEquationSolver(null, 0, 0, 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void equationWithCNonZeroOnlyThrowsException() {
        assertQuadraticEquationSolver(null,0, 0, 5);
    }

    @Test
    public void linearEquationIsSolvedCorrectly() {
        assertQuadraticEquationSolver(new double[]{-2},0, 5, 10);
    }

    @Test
    public void quadraticEquationIsSolvedCorrectly() {
        assertQuadraticEquationSolver(new double[]{1,3},1, -4, 3);
    }

    @Test
    public void equationWithZeroDiscriminantIsSolvedCorrectly() {
        assertQuadraticEquationSolver(new double[]{2},1, -4, 4);
    }

    @Test
    public void equationWithNegativeDiscriminantGivesEmptySolution() {
        assertQuadraticEquationSolver(new double[]{},1, -4, 5);
    }

    public void assertQuadraticEquationSolver(double x[], double a, double b, double c) {
         assertArrayEquals(x, QuadraticEquationSolver.solve(a, b, c), delta);
    };
}

