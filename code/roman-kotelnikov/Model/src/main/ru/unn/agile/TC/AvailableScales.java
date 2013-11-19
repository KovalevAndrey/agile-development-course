package ru.unn.agile.TC;

import java.util.Locale;

public enum AvailableScales {
    Celsius("Celsius", "C", 1, 0),
    Fahrenheit("Fahrenheit", "F", 9.0 / 5.0, 32),
    Kelvin("Kelvin", "K", 1, 273.15),
    Newton("Newton", "N", 33.0 / 100.0, 0),
    Rankine("Rankine", "Ra", 9.0 / 5.0, 273.15 * 9.0 / 5.0),
    Delisle("Delisle", "De", -3.0 / 2.0, -100 * 3.0 / 2.0),
    Reaumur("Reaumur", "Re", 4.0 / 5.0, 0),
    Romer("Romer", "Ro", 21.0 / 40.0, 7.5);

    AvailableScales(String name, String suffix, double multiplier, double shift) {
        this.name = name;
        this.suffix = suffix;
        this.Multiplier = multiplier;
        this.Shift = shift;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toString(double value) {
        return String.format(Locale.ENGLISH, "%.1f %s", value, suffix);
    }

    private String name;
    private String suffix;

    public final double Multiplier;
    public final double Shift;
}
