package ru.unn.agile.UnitConverter;

public class WeightConverter extends UnitConverter {
    public WeightConverter() {
        init();
    }
    public void init() {
        addPair("kg", "g", 1000.);
        addPair("kg", "kg", 1.);
        addPair("g", "kg", 0.001);
        addPair("g", "g", 1.);
        addPair("g", "mg", 1000.);
        addPair("mg", "g", 0.001);
        addPair("mg", "mg", 1.);
        addPair("kg", "mg", 1000000.);
        addPair("mg", "kg", 1e-6);
    }
}
