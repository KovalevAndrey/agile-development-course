package ru.unn.agile.determinant.model;


public class Matrix {
    private int n;
    private double[] data;
    public static double EPS = 1e-6;

    public Matrix(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Wrong matrix size");
        this.n = n;
        data = new double[n * n];
        for (int i = 0; i < n * n; i++)
            data[i] = 0.0;
    }

    public int getSize() {
        return n;
    }

    public double getItem(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j > n)
            throw new IndexOutOfBoundsException("Item is out of bounds");
        return data[i * n + j];
    }

    public  void setItem(int i, int j, double val) {
        if (i < 0 || i >= n || j < 0 || j > n)
            throw new IndexOutOfBoundsException("Item is out of bounds");
        data[i * n + j] = val;
    }

    public double getNorm() {
        double norm = 0;
        for (int i = 0; i < n * n; i++)
            norm += data[i] * data[i];
        return Math.sqrt(norm);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Matrix))
            return false;
        Matrix b = (Matrix)obj;
        if (n != b.n)
            return false;
        double diff = 0.0;
        for (int i = 0; i < n * n; i++)
            diff += (data[i] - b.data[i]) * (data[i] - b.data[i]);
        return (Math.sqrt(diff) < EPS);
    }

    public Matrix multiply(Matrix b) {
        if (b == null)
            throw new NullPointerException("Matrix is not created");
        if ( n != b.n)
            throw new ArithmeticException("Cannot multiply matrices of different sizes");
        Matrix res = new Matrix(b.n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                double s = 0;
                for (int k = 0; k < n; k++)
                    s += getItem(i, k) * b.getItem(k, j);
                res.setItem(i, j, s);
            }
        return res;
    }

    public boolean isLowerTriangular() {
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (Math.abs(getItem(i, j)) > EPS)
                    return false;
        return true;
    }

    public boolean isUpperTriangular() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                if (Math.abs(getItem(i, j)) > EPS)
                    return false;
        return true;
    }
}
