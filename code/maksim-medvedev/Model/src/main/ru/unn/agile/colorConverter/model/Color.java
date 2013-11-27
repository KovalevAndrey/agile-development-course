package ru.unn.agile.colorConverter.model;

public abstract class Color {

    public abstract void checkValues() throws ColorException;
    public abstract RGBColor getRGB() throws  ColorException;

    protected Boolean isValueInRange(int value, int start, int end)
    {
        return value >= start && value <= end;
    }
}
