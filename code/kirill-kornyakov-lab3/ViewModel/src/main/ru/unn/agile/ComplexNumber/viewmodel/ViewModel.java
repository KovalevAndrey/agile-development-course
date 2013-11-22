package ru.unn.agile.ComplexNumber.viewmodel;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import java.util.List;

public class ViewModel {
    private String re1;
    private String im1;
    private String re2;
    private String im2;
    private Operation operation;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;
    private boolean isInputChanged;
    private ILogger logger;

    public ViewModel(ILogger logger) {
        if(logger == null)
            throw new IllegalArgumentException("Logger parameter can't be null");

        this.logger = logger;
        re1 = "";
        im1 = "";
        re2 = "";
        im2 = "";
        operation = Operation.ADD;
        result = "";
        status = Status.WAITING;

        isCalculateButtonEnabled = false;
        isInputChanged = true;
    }

    public void processKeyInTextField(int keyCode) {
        parseInput();

        if (keyCode == KeyboardKeys.ENTER) enterPressed();
    }

    private void enterPressed() {
        logInputParams();

        if (isCalculateButtonEnabled())
            calculate();
    }

    private void logInputParams() {
        if (!isInputChanged) return;

        logger.Log(editingFinishedLogMessage());
        isInputChanged = false;
    }

    public void focusLost() {
        logInputParams();
    }

    private String editingFinishedLogMessage() {
        String message = LogMessages.EDITING_FINISHED
                + "Input arguments are: ["
                + re1 + "; "
                + im1 + "; "
                + re2 + "; "
                + im2 + "]";

        return message;
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
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
        logger.Log(calculateLogMessage());

        if (!parseInput()) return;

        ComplexNumber z1 = new ComplexNumber(re1, im1);
        ComplexNumber z2 = new ComplexNumber(re2, im2);
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

    public List<String> getLog() {
        List<String> log = logger.getLog();
        return log;
    }

    private String calculateLogMessage() {
        String message =
                LogMessages.CALCULATE_WAS_PRESSED + "Arguments"
                + ": Re1 = " + re1
                + "; Im1 = " + im1
                + "; Re2 = " + re2
                + "; Im2 = " + im2 + "."
                + " Operation: " + operation.toString() + ".";

        return message;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        if (this.operation != operation)
        {
            logger.Log(LogMessages.OPERATION_WAS_CHANGED + operation.toString());
            this.operation = operation;
        }
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getRe1() {
        return re1;
    }

    public void setRe1(String re1) {
        if (re1.equals(this.re1)) return;

        this.re1 = re1;
        isInputChanged = true;
    }

    public String getIm1() {
        return im1;
    }

    public void setIm1(String im1) {
        if (im1.equals(this.im1)) return;

        this.im1 = im1;
        isInputChanged = true;
    }

    public String getRe2() {
        return re2;
    }

    public void setRe2(String re2) {
        if (re2.equals(this.re2)) return;

        this.re2 = re2;
        isInputChanged = true;
    }

    public String getIm2() {
        return im2;
    }

    public void setIm2(String im2) {
        if (im2.equals(this.im2)) return;

        this.im2 = im2;
        isInputChanged = true;
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
        public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
        public static final String OPERATION_WAS_CHANGED = "Operation was changed to ";
        public static final String EDITING_FINISHED = "Updated input. ";
    }
}
