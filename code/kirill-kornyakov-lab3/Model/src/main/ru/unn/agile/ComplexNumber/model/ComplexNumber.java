package ru.unn.agile.ComplexNumber.model;

public class ComplexNumber {
    private double re;
    private double im;

    public ComplexNumber(double real, double imaginary) {
        this.re = real;
        this.im = imaginary;
    }

    public ComplexNumber() {
        this.re = 0;
        this.im = 0;
    }

    public boolean equals(Object object) {
        ComplexNumber number;
        number = (ComplexNumber) object;
        return number.getReal() == getReal()
                && number.getImaginary() == getImaginary();
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(other.getReal() + getReal(),
                other.getImaginary() + getImaginary());
    }

    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(
                other.getReal() * getReal() - other.getImaginary() * getImaginary(),
                other.getReal() * getImaginary() + other.getImaginary() * getReal());
    }

    public double getReal() {
        return re;
    }

    public double getImaginary() {
        return im;
    }

    public String toString() {
        return Formatter.getFormatted(this);
    }
}
