package ru.unn.agile.areaConverter;

public enum ScaleTable {
    Metre(1.0),
    Hectare(10000.0),
    Yard(0.83612736),
    Centimetre(0.0001);

    ScaleTable(double multiplier) {
        this.Multiplier = multiplier;
    }

    public double getMultiplier(){
        return Multiplier;
    }

    private double Multiplier;
}