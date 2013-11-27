package ru.unn.agile.colorConverter.model;

public class LABColor extends Color {
    public int L;
    public int A;
    public int B;

    public LABColor(int l, int a, int b) {
        L = l;
        A = a;
        B = b;
    }

    @Override
    public void checkValues() throws ColorException {
        if (!isValueInRange(L, 0, 100))
            throw new ColorException("L value does not fall in expected range");
        if (!isValueInRange(A, -128, 128))
            throw new ColorException("A* value does not fall in expected range");
        if (!isValueInRange(B, -128, 128))
            throw new ColorException("B value does not fall in expected range");
    }

    private XYZSpace getXYZ(){
        double y = (L + 16) / 116.0;
        double x = A / 500.0 + y;
        double z = y - B / 200.0;

        if (Math.pow(y, 3) > 0.008856)
            y = Math.pow(y, 3);
        else
            y = (y - 16.0 / 116) / 7.787;

        if (Math.pow(x, 3) > 0.008856)
            x = Math.pow(x, 3);
        else
            x = (x - 16.0 / 116) / 7.787;

        if (Math.pow(z, 3) > 0.008856)
            z = Math.pow(z, 3);
        else
            z = (z - 16.0 / 116) / 7.787;

        // Observer= 2°, Illuminant= D65
        final double refX = 95.047;
        final double refY = 100.000;
        final double refZ = 108.883;

        XYZSpace xyz = new XYZSpace(
                refX * x,
                refY * y,
                refZ * z);

        return xyz;
    }

    @Override
    public RGBColor getRGB() throws ColorException {
        checkValues();

        XYZSpace xyz = getXYZ();
        return xyz.getRgb();
    }

    @Override
    public String toString() {
        return "LAB(" + L + ", " + A + ", " + B + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (!(other instanceof LABColor)) return false;

        LABColor otherColor = (LABColor)other;
        return otherColor.L == L &&
                otherColor.A == A &&
                otherColor.B == B;
    }
}
