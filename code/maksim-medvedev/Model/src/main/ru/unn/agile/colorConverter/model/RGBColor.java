package ru.unn.agile.colorConverter.model;

public class RGBColor extends Color {
    public int R;
    public int G;
    public int B;

    public RGBColor(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }

    public void checkValues() throws ColorException {
        if (!isValueInRange(R, 0, 255))
            throw new ColorException("Red value does not fall in expected range");
        if (!isValueInRange(G, 0, 255))
            throw new ColorException("Green value does not fall in expected range");
        if (!isValueInRange(B, 0, 255))
            throw new ColorException("Blue value does not fall in expected range");
    }

    @java.lang.Override
    public RGBColor getRGB() throws ColorException {
        checkValues();
        return this;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "RGB(" + R + ", " + G + ", " + B + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (!(other instanceof RGBColor)) return false;

        RGBColor otherColor = (RGBColor)other;
        return otherColor.R == R &&
                otherColor.G == G &&
                otherColor.B == B;
    }
}
