package ru.unn.agile.converter.model;


public enum Unit {
    meter("meter", 1),
    decimeter("decimeter", 0.1),
    centimeter("centimeter", 0.01),
    kilometer("kilometer", 1000),
    millimeter("millimeter", 0.001),
    inch("inch", 0.0254),
    league("league", 4828.032),
    mile("mile", 1609.344),
    furhlong("furhlong", 201.16),
    chain("chain", 20.1168),
    rod("rod", 5.029),
    yard("yard", 0.9144);

    Unit(String name, double factor) {
        this.name = name;
        this.factor = factor;
    }

    public String toString() {
        return name;
    }
    public double factor;
    public String name;
}
