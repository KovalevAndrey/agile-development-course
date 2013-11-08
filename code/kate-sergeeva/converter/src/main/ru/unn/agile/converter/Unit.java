package ru.unn.agile.converter;


public enum Unit {
    meter(1),
    decimeter(0.1),
    centimeter(0.01),
    kilometer(1000),
    millimeter(0.001),
    inch(0.0254),
    league(4828.032),
    mile(1609.344),
    furhlong(201.16),
    chain(20.1168),
    rod(5.029),
    yard(0.9144);

    Unit(double factor){
        this.factor = factor;
    }
    public double factor;
}
