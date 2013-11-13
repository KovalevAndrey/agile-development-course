package ru.unn.agile.determinant.model;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestMatrix {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    public void createZeroMatrixTest() {
        Matrix a = new Matrix(3);
        assertEquals (a.getNorm(), 0, Matrix.EPS);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createZeroSizeMatrixTest() {
        Matrix a = new Matrix(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createNegativeSizeMatrixTest() {
        Matrix a = new Matrix(-3);
    }

    @Test
    public void SetItemTest() {
        Matrix a = new Matrix(3);
        double val = 10.0;
        a.setItem(2, 1, val);
        assertEquals(a.getItem(2, 1), val, Matrix.EPS);
    }

    @Test
    public void SetBoundaryValueTest() {
        Matrix a = new Matrix(3);
        double val = 10.0;
        a.setItem(2, 2, val);
        assertEquals(a.getItem(2, 2), val, Matrix.EPS);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void SetOutOfRangeItemTest() {
        Matrix a = new Matrix(3);
        double val = 10.0;
        a.setItem(3, 2, val);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void GetOutOfRangeItemTest() {
        Matrix a = new Matrix(3);
        a.getItem(3, 2);
    }

    @Test
    public void GetNormOfIdentityTest() {
        Matrix a = new Matrix(3);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a.setItem(i, j, 1.0);
        assertEquals(a.getNorm(), (double)3, Matrix.EPS);
    }

    @Test
    public void GetNormTest() {
        Matrix a = new Matrix(2);
        a.setItem(0, 0, 2.0);
        a.setItem(0, 1, 1.0);
        a.setItem(1, 0, -5.0);
        a.setItem(1, 1, 3.0);
        assertEquals (a.getNorm(), Math.sqrt(39.0), Matrix.EPS);
    }

    @Test
    public void MatrixEqualsNullMatrixTest() {
        Matrix A = new Matrix(3);
        Matrix B = null;
        assert (!A.equals(B));
    }

    @Test
    public void MatrixEqualsSelfTest() {
        Matrix A = new Matrix(3);
        assert (A.equals(A));
    }

    @Test
    public void MatrixEqualsToNonMatrixTest() {
        Matrix A = new Matrix(3);
        Object B = new Object();
        assert (!A.equals(B));
    }

    @Test
    public void DifferentMatricesAreEqual() {
        Matrix A = new Matrix(2);
        Matrix B = new Matrix(3);
        assert (!A.equals(B));
    }

    @Test
    public void MatrixEqualsToSimilar() {
        Matrix A = new Matrix(2);
        A.setItem(0, 0, 1.0);
        A.setItem(1, 1, 2.0);
        Matrix B = new Matrix(2);
        B.setItem(0, 0, 1.0 + 1e-7);
        B.setItem(1, 1, 2.0 + 1e-7);
        assert (A.equals(B));
    }

    @Test
    public void MatrixMultiplicationTest() {
        Matrix A = new Matrix(2);
        A.setItem(0, 0, 1.0);
        A.setItem(0, 1, 4.0);
        A.setItem(1, 0, 2.0);
        A.setItem(1, 1, -1.0);

        Matrix B = new Matrix(2);
        B.setItem(0, 0, 2.0);
        B.setItem(0, 1, 3.0);
        B.setItem(1, 0, -2.0);
        B.setItem(1, 1, 5.0);
        Matrix C = A.multiply(B);

        Matrix res = new Matrix(2);
        res.setItem(0, 0, -6.0);
        res.setItem(0, 1, 23);
        res.setItem(1, 0, 6);
        res.setItem(1, 1, 1);

        assert (C.equals(res));
    }

    @Test
    public void DifferentSizeMatrixMultiplicationTest() {
        Matrix A = new Matrix(2);
        Matrix B = new Matrix(3);
        assert (!A.equals(B));
    }

    @Test
    public void IsMatrixUpperTriangularPositive() {
        Matrix A = new Matrix(3);
        A.setItem(0, 0, 1.0);
        A.setItem(1, 1, 3.0);
        A.setItem(1, 2, 5.0);
        assert (A.isUpperTriangular());
    }

    @Test
    public void IsMatrixUpperTriangularNegative() {
        Matrix A = new Matrix(3);
        A.setItem(0, 0, 1.0);
        A.setItem(1, 1, 3.0);
        A.setItem(2, 1, 5.0);
        assert (!A.isUpperTriangular());
    }

    @Test
    public void IsMatrixLowerTriangularPositive() {
        Matrix A = new Matrix(3);
        A.setItem(0, 0, 1.0);
        A.setItem(1, 1, 3.0);
        A.setItem(2, 1, 5.0);
        assert (A.isLowerTriangular());
    }

    @Test
    public void IsMatrixLowerTriangularNegative() {
        Matrix A = new Matrix(3);
        A.setItem(0, 0, 1.0);
        A.setItem(1, 1, 3.0);
        A.setItem(1, 2, 5.0);
        assert (!A.isLowerTriangular());
    }
}
