package ru.unn.agile.ComplexNumber.viewmodel;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import java.util.List;

public class ViewModel {
    public static final int ENTER_CODE = 10;
    public String re1 = "";
    public String im1 = "";
    public String re2 = "";
    public String im2 = "";
    public Operation operation = Operation.ADD;
    public String result = "";
    public String status = Status.WAITING;
    public boolean isCalculateButtonEnabled = false;

    private ILogger logger;

    public ViewModel(ILogger logger) {
        this.logger = logger;
    }

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            calculate();
        } else {
            parseInput();
        }
    }

    private boolean isInputAvailable() {
        return !re1.isEmpty() && !im1.isEmpty() && !re2.isEmpty() && !im2.isEmpty();
    }

    private boolean parseInput() {
        try {
            if (!re1.isEmpty()) Double.parseDouble(re1);
            if (!im1.isEmpty()) Double.parseDouble(im1);
            if (!re2.isEmpty()) Double.parseDouble(re2);
            if (!im2.isEmpty()) Double.parseDouble(im2);
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isCalculateButtonEnabled = false;
            return false;
        }

        isCalculateButtonEnabled = isInputAvailable();
        if (isCalculateButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }

        return isCalculateButtonEnabled;
    }

    public void calculate() {
        logger.Log(prepareLogMessage());

        if (!parseInput()) return;

        ComplexNumber z1 = convertToComplexNumber(re1, im1);
        ComplexNumber z2 = convertToComplexNumber(re2, im2);
        ComplexNumber z3 = new ComplexNumber();

        switch (operation) {
            case ADD:
                z3 = z1.add(z2);
                break;
            case MULTIPLY:
                z3 = z1.multiply(z2);
                break;
        }

        result = z3.toString();
        status = Status.SUCCESS;
    }

    public ComplexNumber convertToComplexNumber(String re, String im) {
        return new ComplexNumber(Double.parseDouble(re), Double.parseDouble(im));
    }

    public List<String> getLog() {
        List<String> log = logger.getLog();
        return log;
    }

    public String prepareLogMessage() {
        String message =
                LogMessages.CALCULATE_WAS_PRESSED + "With input arguments"
                + ": Re1 = " + re1
                + "; Im1 = " + im1
                + "; Re2 = " + re2
                + "; Im2 = " + im2 + "."
                + "And the following operation: " + operation.toString() + ".";

        return message;
    }

    public enum Operation {
        ADD("Add"),
        MULTIPLY("Mul");
        private final String name;

        private Operation(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }

    public class LogMessages {
        public static final String CALCULATE_WAS_PRESSED = "Calculate button pressed!";
    }
}
