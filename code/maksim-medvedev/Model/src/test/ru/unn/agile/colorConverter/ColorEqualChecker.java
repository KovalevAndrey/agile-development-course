package ru.unn.agile.colorConverter;

/**
 * Created with IntelliJ IDEA.
 * User: maxmedvedev
 * Date: 10/19/13
 * Time: 3:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class ColorEqualChecker {
    public static Boolean equalColorsByValues(RGBColor color, int r, int g, int b)
    {
        return color.R == r &&
                color.G == g &&
                color.B == b;
    }

    public static Boolean equalColorsByValues(RGBColor first, RGBColor second)
    {
        return first.R == second.R &&
                first.G == second.G &&
                first.B == second.B;
    }

    public static Boolean equalColorsByValues(HSVColor first, HSVColor second)
    {
        return first.H == second.H &&
                first.S == second.S &&
                first.V == second.V;
    }

    public static Boolean almostEqualValues(RGBColor color, int r, int g, int b)
    {
        return Math.abs(color.R - r) <= 2 &&
                Math.abs(color.G - g) <= 2 &&
                Math.abs(color.B - b) <= 2;
    }

    public static Boolean almostEqualValues(RGBColor first, RGBColor second)
    {
        return Math.abs(first.R - second.R) <= 2 &&
                Math.abs(first.G - second.G) <= 2 &&
                Math.abs(first.B - second.B) <= 2;
    }
}
