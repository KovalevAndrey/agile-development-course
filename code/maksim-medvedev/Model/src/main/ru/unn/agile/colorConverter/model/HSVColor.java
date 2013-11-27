package ru.unn.agile.colorConverter.model;

public class HSVColor extends Color {
    public int H;
    public int S;
    public int V;

    public HSVColor(int h, int s, int v) {
        H = h;
        S = s;
        V = v;
    }

    @Override
    public void checkValues() throws ColorException {
        if (!isValueInRange(H, 0, 359))
            throw new ColorException("Hue value does not fall in expected range");
        if (!isValueInRange(S, 0, 100))
            throw new ColorException("Saturation value does not fall in expected range");
        if (!isValueInRange(V, 0, 100))
            throw new ColorException("Value does not fall in expected range");
    }

    @Override
    public RGBColor getRGB() throws ColorException {
        checkValues();

        int r = 0;
        int g = 0;
        int b = 0;

        int hi = (int)Math.floor(H / 60.0);
        int vMin = ((100 - S) * V) / 100;
        int a = (V - vMin) * (H % 60) / 60;

        int vInc = vMin + a;
        int vDec = V - a;

        switch (hi)
        {
            case 0: r = V; g = vInc; b = vMin;
                break;

            case 1: r = vDec; g = V; b = vMin;
                break;

            case 2: r = vMin; g = V; b = vInc;
                break;

            case 3: r = vMin; g = vDec; b = V;
                break;

            case 4: r = vInc; g = vMin; b = V;
                break;

            case 5: r = V; g = vMin; b = vDec;
                break;
        }
        return new RGBColor(
                (int)Math.round(r * 2.55),
                (int)Math.round(g * 2.55),
                (int)Math.round(b * 2.55));
    }

    @Override
    public String toString() {
        return "HSV(" + H + ", " + S + ", " + V + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;

        if (!(other instanceof HSVColor)) return false;

        HSVColor otherColor = (HSVColor)other;
        return otherColor.H == H &&
                otherColor.S == S &&
                otherColor.V == V;
    }
}
