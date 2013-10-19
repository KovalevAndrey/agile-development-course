package ru.unn.agile.colorConverter;

/**
 * Created with IntelliJ IDEA.
 * User: maxmedvedev
 * Date: 10/19/13
 * Time: 12:57 AM
 */
public abstract class Color {

    public abstract void checkValues() throws ColorException;
    public abstract RGBColor getRGB() throws  ColorException;

    protected Boolean isValueInRange(int value, int start, int end)
    {
        return value >= start && value <= end;
    }
}
