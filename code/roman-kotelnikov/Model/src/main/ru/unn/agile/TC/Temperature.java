package ru.unn.agile.TC;

public class Temperature {
    private double current;
    private AvailableScales scale;

    public Temperature(double current, AvailableScales scale) {
        if (scale == null)
            throw new IllegalArgumentException("Scale cannot be null");
        this.scale = scale;
        this.current = current;
    }

    public double getCurrent() {
        return current;
    }

    public AvailableScales getScale() {
        return scale;
    }

    public Temperature scaleTo(AvailableScales newScale) {
        if (newScale == null)
            throw new IllegalArgumentException("Scale cannot be null");

        double currentInCelsius = (current - scale.Shift) / scale.Multiplier;
        double newCurrent = currentInCelsius * newScale.Multiplier + newScale.Shift;
        return new Temperature(newCurrent, newScale);
    }

    @Override
    public String toString() {
        return scale.toString(current);
    }
}
