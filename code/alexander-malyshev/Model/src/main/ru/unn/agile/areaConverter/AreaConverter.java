package ru.unn.agile.areaConverter;

public class AreaConverter {

    static public double convert(double value, String inputScale, String outputScale) {
        Area area = new Area(value, inputScale);
        area.toSI();
        return area.fromSI(outputScale);
    }
}