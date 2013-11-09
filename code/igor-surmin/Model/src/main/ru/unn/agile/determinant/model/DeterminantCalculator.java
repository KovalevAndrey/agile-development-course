package ru.unn.agile.determinant.model;

public class DeterminantCalculator {
    private double determinant;

    public DeterminantCalculator(Matrix A) {
        if (A == null)
            throw new NullPointerException("Matrix is null");
        LUDecomposition lu = new LUDecomposition(A);
        Matrix L = lu.getL();
        Matrix U = lu.getU();
        double det1 = 1, det2 = 1;
        for (int i = 0; i < A.getSize(); i++) {
            det1 *= L.getItem(i, i);
            det2 *= U.getItem(i, i);
        }
        determinant = det1 * det2;
    }

    public double getDeterminant() {
        return determinant;
    }
}
