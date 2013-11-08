package ru.unn.agile.areaConverter;

import ru.unn.agile.areaConverter.ScaleTable;

public class Area {

    private double value;
    private String scale;

    private void checkString(String scale) {
        try {
            ScaleTable.valueOf(scale);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Wrong or empty scale");
        }
    }

    public Area(double value, String scale) {
        checkString(scale);
        this.scale = scale;
        if (value < 0.0)
            throw new IllegalArgumentException("Negative measure");
        this.value = value;
    }

    public double toSI() {
        value *= ScaleTable.valueOf(scale).getMultiplier();
        scale = "Metre";
        return value;
    }

    public double fromSI(String newScale) {
        checkString(newScale);
        toSI();
        value /= ScaleTable.valueOf(newScale).getMultiplier();
        scale = ScaleTable.valueOf(newScale).toString();
        return value;
    }

}
