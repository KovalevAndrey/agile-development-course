package ru.unn.agile.converter.model;


public class ConverterOfLength {

    private double calculateFactor(Unit input, Unit output){
        double factor = 1;
        if ((input == null)||(output == null))
            throw new IllegalArgumentException();
        factor = input.factor / output.factor;
        return factor;
    }

    private boolean isCorrectInputValue(double inputValue, double factor){
        boolean isCorrect = true;
        if ((inputValue * factor >= Double.MAX_VALUE)||((inputValue != 0.0)&&(inputValue * factor <= Double.MIN_NORMAL)))
            isCorrect = false;
        return isCorrect;
    }

    public double convertLength(Unit inputUnit, Unit outputUnit, double inputValue){
        double outputValue;
        double factor = calculateFactor(inputUnit, outputUnit);
        if (!isCorrectInputValue(inputValue, factor))
            throw new IllegalArgumentException();
        outputValue = inputValue * factor;
        return outputValue;
    }
}
