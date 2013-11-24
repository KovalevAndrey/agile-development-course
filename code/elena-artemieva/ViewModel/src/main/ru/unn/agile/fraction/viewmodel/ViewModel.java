package ru.unn.agile.fraction.viewmodel;

import ru.unn.agile.fraction.model.Fraction;

public class ViewModel {

    public String input1 = "";
    public String input2 = "";
    public String result = "";

    public boolean isAddButtonEnabled = false;
    public boolean isSubtractButtonEnabled = false;
    public boolean isMultiplyButtonEnabled = false;
    public boolean isDivideButtonEnabled = false;

    public void processKeyInTextField() {
        parseInput();
    }

    private boolean parseInput() {
        if (Fraction.tryParse(input1) && Fraction.tryParse(input2)) {
            isAddButtonEnabled = true;
            isSubtractButtonEnabled = true;
            isMultiplyButtonEnabled = true;

            if (Fraction.parse(input2).getNumerator() == 0) {
                isDivideButtonEnabled = false;
            }
            else {
                isDivideButtonEnabled = true;
            }

            return true;
        }
        else {
            isAddButtonEnabled = false;
            isSubtractButtonEnabled = false;
            isMultiplyButtonEnabled = false;
            isDivideButtonEnabled = false;

            return false;
        }
    }

    public void add() {
        calculate(Operation.ADD);
    }

    public void subtract() {
        calculate(Operation.SUBTRACT);
    }

    public void multiply() {
        calculate(Operation.MULTIPLY);
    }

    public void divide() {
        calculate(Operation.DIVIDE);
    }

    private void calculate(Operation operation) {
        if (!parseInput()) return;

        Fraction firstFraction = Fraction.parse(input1);
        Fraction secondFraction = Fraction.parse(input2);

        Fraction resultFraction = new Fraction(0, 1);

        switch (operation) {
            case ADD:
                resultFraction = firstFraction.add(secondFraction);
                break;
            case SUBTRACT:
                resultFraction = firstFraction.subtract(secondFraction);
                break;
            case MULTIPLY:
                resultFraction = firstFraction.multiply(secondFraction);
                break;
            case DIVIDE:
                resultFraction = firstFraction.divide(secondFraction);
                break;
        }

        result = resultFraction.toString();
    }

    public enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE;
    }
}
