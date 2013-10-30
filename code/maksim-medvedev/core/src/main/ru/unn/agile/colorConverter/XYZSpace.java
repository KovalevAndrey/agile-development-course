package ru.unn.agile.colorConverter;

/**
 * Created with IntelliJ IDEA.
 * User: maxmedvedev
 * Date: 10/19/13
 * Time: 3:48 AM
 */
public class XYZSpace {
    public double X;
    public double Y;
    public double Z;

    public XYZSpace(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }

    public RGBColor getRgb() {
        // (Observer = 2°, Illuminant = D65)
        double x = X / 100;
        double y = Y / 100;
        double z = Z / 100;

        double r = x *  3.2406 + y * (-1.5372) + z * (-0.4986);
        double g = x * (-0.9689) + y *  1.8758 + z *  0.0415;
        double b = x *  0.0557 + y * (-0.2040) + z *  1.0570;

        if (r > 0.0031308)
            r = 1.055 * ( Math.pow(r, (1 / 2.4)) ) - 0.055;
        else
            r = 12.92 * r;
        if (g > 0.0031308)
            g = 1.055 * ( Math.pow(g, (1 / 2.4)) ) - 0.055;
        else
            g = 12.92 * g;
        if (b > 0.0031308)
            b = 1.055 * (Math.pow(b, ( 1 / 2.4 )) ) - 0.055;
        else
            b = 12.92 * b;

        return new RGBColor(
                (int)Math.round(r * 255),
                (int)Math.round(g * 255),
                (int)Math.round(b * 255));
    }
}