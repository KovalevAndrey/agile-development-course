package ru.unn.agile.ComplexNumber.model;

public class Formatter {

    public static String formatPositiveDouble(double value) {
        if (value < 0)
            throw new IllegalArgumentException();

        Integer i = (int) value;
        String buffer = i.toString();

        value *= 100; // We want only 2 digits
        i = (int) value % 100;
        buffer += ".";
        if (i == 0) {
            buffer += "0";
        } else if (i < 10) {
            buffer += "0";
            buffer += i.toString();
        } else {
            if (i % 10 == 0)
                i /= 10;
            buffer += i.toString();
        }

        return buffer;
    }

    public static String getFormatted(ComplexNumber z) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(z.getReal() < 0 ? "-" : "");
        String re = formatPositiveDouble(Math.abs(z.getReal()));
        String im = formatPositiveDouble(Math.abs(z.getImaginary()));
        buffer.append(re);
        buffer.append(z.getImaginary() < 0 ? " - " : " + ");
        buffer.append(im + "i");
        return buffer.toString();
    }

}
