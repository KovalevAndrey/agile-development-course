package ru.unn.agile.determinant;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestDeterminantCalculator {

    @Test
    public  void DeterminantTest1x1() {
        Matrix A = new Matrix(1);
        double val = 10;
        A.setItem(0, 0, val);
        DeterminantCalculator dCalc = new DeterminantCalculator(A);
        double determinant = dCalc.getDeterminant();
        assertEquals(determinant, 10.0, Matrix.EPS);
    }

    @Test
    public void DeterminantTest3x3Diag() {
        Matrix A = new Matrix(3);
        A.setItem(0, 0, 1);
        A.setItem(1, 1, 2);
        A.setItem(2, 2, 3);
        DeterminantCalculator dCalc = new DeterminantCalculator(A);
        double determinant = dCalc.getDeterminant();
        assertEquals(determinant, 6.0, Matrix.EPS);
    }

    @Test
    public void DeterminantTest3x3() {
        Matrix A = new Matrix(3);
        A.setItem(0, 0, 10);
        A.setItem(0, 1, -7);
        A.setItem(0, 2, 0);
        A.setItem(1, 0, -3);
        A.setItem(1, 1, 6);
        A.setItem(1, 2, 2);
        A.setItem(2, 0, 5);
        A.setItem(2, 1, -1);
        A.setItem(2, 2, 5);
        DeterminantCalculator dCalc = new DeterminantCalculator(A);
        double determinant = dCalc.getDeterminant();
        assertEquals(determinant, 145.0, Matrix.EPS);
    }

    @Test (expected = NullPointerException.class)
    public void DeterminantTestNullMatrix() {
        Matrix A = null;
        DeterminantCalculator dCalc = new DeterminantCalculator(A);
    }
}
