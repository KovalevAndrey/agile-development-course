package ru.unn.agile.determinant;

import org.junit.Rule;
import org.junit.Test;

public class TestLUDecomposition {

    @Test
    public void TestLUDecomposition3x3() {
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
        LUDecomposition lu = new LUDecomposition(A);
        Matrix L = lu.getL();
        Matrix U = lu.getU();
        assert (L.isLowerTriangular() && U.isUpperTriangular() && A.equals(L.multiply(U)));
    }

    @Test(expected = NullPointerException.class)
    public void TestLUDecompositionNullMatrix() {
        Matrix A = null;
        LUDecomposition lu = new LUDecomposition(A);
    }
}
