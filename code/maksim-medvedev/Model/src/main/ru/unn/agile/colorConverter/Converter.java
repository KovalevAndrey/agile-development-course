package ru.unn.agile.colorConverter;

import javax.swing.*;

public class Converter {
    private JButton button1;
    private JPanel panel1;

    public RGBColor toRGB(Color color) throws ColorException {
        return color.getRGB();
    }

    public HSVColor toHSV(Color color) throws ColorException {
        RGBColor rgbColor = color.getRGB();
        return rgbToHSV(rgbColor);
    }

    public LABColor toLAB(Color color) throws ColorException {
        RGBColor rgbColor = color.getRGB();
        XYZSpace xyzColor = rgbToXyz(rgbColor);
        return xyzToLab(xyzColor);
    }

    private HSVColor rgbToHSV(RGBColor color) throws ColorException {
        color.checkValues();

        double h = 0;
        double s = 0;
        double v = 0;

        double r = color.R / 255.0;
        double g = color.G / 255.0;
        double b = color.B / 255.0;

        double minRGB = Math.min(r, Math.min(g,b));
        double maxRGB = Math.max(r, Math.max(g,b));

        if (minRGB == maxRGB) {
            v = minRGB;
            return new HSVColor(0, 0, (int)Math.round(v * 100.0));
        }

        double subD = (r == minRGB) ? g - b : ((b == minRGB) ? r - g : b - r);
        double subH = (r == minRGB) ? 3 : ((b == minRGB) ? 1 : 5);

        h = 60 * (subH - subD / (maxRGB - minRGB));
        s = (maxRGB - minRGB) / maxRGB;
        v = maxRGB;

        return new HSVColor(
                (int)Math.round(h),
                (int)Math.round(s * 100.0),
                (int)Math.round(v * 100.0));
    }

    private XYZSpace rgbToXyz(RGBColor color) throws ColorException {
        color.checkValues();

        double r = color.R / 255.0;
        double g = color.G / 255.0;
        double b = color.B / 255.0;

        if (r > 0.04045)
            r = Math.pow(((r + 0.055) / 1.055), 2.4);
        else
            r = r / 12.92;
        if (g > 0.04045)
            g = Math.pow(((g + 0.055) / 1.055), 2.4);
        else
            g = g / 12.92;
        if (b > 0.04045)
            b = Math.pow(((b + 0.055) / 1.055), 2.4);
        else
            b = b / 12.92;

        r = r * 100;
        g = g * 100;
        b = b * 100;

        //Observer. = 2°, Illuminant = D65
        double x = r * 0.4124 + g * 0.3576 + b * 0.1805;
        double y = r * 0.2126 + g * 0.7152 + b * 0.0722;
        double z = r * 0.0193 + g * 0.1192 + b * 0.9505;

        return new XYZSpace(x, y, z);
    }

    private LABColor xyzToLab(XYZSpace xyz) throws ColorException {
        // Observer= 2°, Illuminant= D65
        final double refX = 95.047;
        final double refY = 100.000;
        final double refZ = 108.883;

        double x = xyz.X / refX;
        double y = xyz.Y / refY;
        double z = xyz.Z / refZ;

        if (x > 0.008856)
            x = Math.pow(x, (1.0 / 3.0));
        else
            x = (7.787 * x) + (16.0 / 116);

        if (y > 0.008856)
            y = Math.pow(y, (1.0 / 3.0));
        else
            y = (7.787 * y) + (16.0 / 116);

        if (z > 0.008856)
            z = Math.pow(z, (1.0 / 3.0));
        else
            z = (7.787 * z) + (16.0 / 116);

        double L = 116 * y - 16;
        double A = 500 * (x - y);
        double B = 200 * (y - z);

        return new LABColor(
                (int)Math.round(L),
                (int)Math.round(A),
                (int)Math.round(B));
    }
}
