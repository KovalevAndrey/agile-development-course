package ru.unn.agile.determinant;


public class LUDecomposition {

    private Matrix A, L, U;

    public LUDecomposition(Matrix A) {
        int n = A.getSize();
        L = new Matrix(n);
        U = new Matrix(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                U.setItem(i, j, A.getItem(i, j));

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                L.setItem(j, i, U.getItem(j, i) / U.getItem(i, i));

        for(int k = 1; k < n; k++) {
            for (int i = k - 1; i < n; i++)
                for (int j = i; j < n; j++)
                    L.setItem(j, i, U.getItem(j, i) / U.getItem(i, i));

            for(int i = k; i < n; i++)
                for(int j = k-1; j < n; j++)
                    U.setItem(i, j, U.getItem(i, j) - L.getItem(i, k - 1) * U.getItem(k - 1, j));
        }
    }

    public Matrix getL() {
        return L;
    }

    public Matrix getU() {
        return U;
    }
}
