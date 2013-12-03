package ru.unn.agile.fraction.viewmodel;

import ru.unn.agile.fraction.model.Fraction;

import java.util.List;

public class ViewModel {

    public String input1;
    public String input2;
    public String result;

    public boolean isAddButtonEnabled;
    public boolean isSubtractButtonEnabled;
    public boolean isMultiplyButtonEnabled;
    public boolean isDivideButtonEnabled;

    private ILogger logger;

    public ViewModel(ILogger logger) {
        if (logger == null)
            throw new IllegalArgumentException("Logger can't be null");

        this.logger = logger;

        input1 = "";
        input2 = "";
        result = "";

        isAddButtonEnabled = false;
        isSubtractButtonEnabled = false;
        isMultiplyButtonEnabled = false;
        isDivideButtonEnabled = false;
    }

    public void processKeyInTextField() {
        parseInput();
    }

    private boolean parseInput() {
        boolean canParseFirstFraction = Fraction.tryParse(input1);
        boolean canParseSecondFraction = Fraction.tryParse(input2);

        if (canParseFirstFraction && canParseSecondFraction) {
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
        logger.log(editingOperationsLogMessage(LogMessages.ADD_WAS_PRESSED));
        calculate(Operation.ADD);
    }

    public void subtract() {
        logger.log(editingOperationsLogMessage(LogMessages.SUBTRACT_WAS_PRESSED));
        calculate(Operation.SUBTRACT);
    }

    public void multiply() {
        logger.log(editingOperationsLogMessage(LogMessages.MULTIPLY_WAS_PRESSED));
        calculate(Operation.MULTIPLY);
    }

    public void divide() {
        logger.log(editingOperationsLogMessage(LogMessages.DIVIDE_WAS_PRESSED));
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
        logger.log(editingCalculateLogMessage());
    }

    private String editingCalculateLogMessage() {
        return LogMessages.CALCULATE + result;
    }

    private String editingOperationsLogMessage(String message) {
        return message + "( " + input1 + ", " + input2 + " )";
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE;
    }

    public class LogMessages {
        public static final String ADD_WAS_PRESSED = "Trying to add fractions: ";
        public static final String SUBTRACT_WAS_PRESSED = "Trying to subtract fractions: ";
        public static final String MULTIPLY_WAS_PRESSED = "Trying to multiply fractions: ";
        public static final String DIVIDE_WAS_PRESSED = "Trying to divide fractions: ";
        public static final String CALCULATE = "Calculation successful. Result: ";
    }
}
