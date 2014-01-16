package ru.unn.agile.Polynomial.viewmodel;

import ru.unn.agile.Polynomial.PolynomialCalculator;
import ru.unn.agile.Polynomial.Term;

import java.util.List;

public class ViewModel {
    private final ILogger logger;
    private String polynomial1 = "";
    private String polynomial2 = "";
    private String result = "";
    private String status = Status.WAITING;
    private boolean isCalculateButtonEnabled = false;
    private Operation operation = Operation.ADD;
    private boolean isInputChanged = true;

    public ViewModel(ILogger logger) {
        if (logger == null)
            throw new IllegalArgumentException("Logger parameter cannot be null");
        parser = new PolynomialParser();
        writer = new PolynomialWriter();
        this.logger = logger;
        calculator = new PolynomialCalculator();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean getIsCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public void calculate() {
        logger.log(LoggingLevel.RELEASE, calculateLogMessage());

        if (!parseInput())
            return;

        Term[] resultTerms = null;
        resultTerms = tryCalculate();

        if (resultTerms == null)
            return;

        result = writer.writePolynomial(resultTerms);
        logger.log(LoggingLevel.RELEASE, "Result: " + result);
        status = Status.SUCCESS;
    }

    public void processKeyInTextField(int keyCode) {
        parseInput();
        if (keyCode == KeyboardKeys.ENTER && isCalculateButtonEnabled)
            calculate();
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    public void setOperation(Operation op) {
        if (operation == op)
            return;
        logger.log(LoggingLevel.DEBUG, "Operation " + op.toString() + " was selected");
        operation = op;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setPolynomial1(String polynomial1) {
        if (polynomial1.equals(this.polynomial1))
            return;

        this.polynomial1 = polynomial1;

        isInputChanged = true;
    }

    public String getPolynomial1() {
        return polynomial1;
    }

    public void setPolynomial2(String polynomial2) {
        if (polynomial2.equals(this.polynomial2))
            return;

        this.polynomial2 = polynomial2;

        isInputChanged = true;
    }

    public String getPolynomial2() {
        return polynomial2;
    }

    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calculate' or Enter";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
        public static final String ERROR = "Internal error";
    }

    private String calculateLogMessage() {
        String message = "Polynomial1: " + polynomial1 +
                " Polynomial2: " + polynomial2 +
                " Operation: " + operation.toString();
        return message;
    }

    public void focusLost() {
        logInputParams();
    }

    public void setLogLevel(LoggingLevel level) {
        logger.setLevel(level);
    }

    public LoggingLevel getLogLevel() {
        return logger.getLevel();
    }

    public String getLastLogMessage() {
        return logger.getLastMessage();
    }

    private void logInputParams() {
        if (!isInputChanged) return;

        logger.log(LoggingLevel.DEBUG, editingFinishedLogMessage());

        isInputChanged = false;
    }

    private String editingFinishedLogMessage() {
        String p1 = polynomial1.isEmpty() ? "None" : polynomial1;
        String p2 = polynomial2.isEmpty() ? "None" : polynomial2;
        String message = "Input arguments changed: p1 = " + p1 + ", p2 = " + p2;

        return message;
    }


    private boolean isInputAvailable() {
        return !polynomial1.isEmpty() && !polynomial2.isEmpty();
    }

    private boolean parseInput() {
        try {
            if (!polynomial1.isEmpty()) terms1 = parser.parsePolynomial(polynomial1);
            if (!polynomial2.isEmpty()) terms2 = parser.parsePolynomial(polynomial2);
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

    private Term[] tryCalculate() {
        Term[] result = null;
        try
        {
            switch (operation) {
                case ADD:
                result = calculator.add(terms1, terms2);
                break;
                case SUB:
                result = calculator.sub(terms1, terms2);
                break;
                case MUL:
                result = calculator.mul(terms1, terms2);
                break;
            }
        }
        catch (Exception e) {
            status = Status.ERROR;
            return null;
        }
        return result;
    }

    private PolynomialParser parser = null;
    private PolynomialWriter writer = null;
    private PolynomialCalculator calculator = null;
    private Term[] terms1 = null;
    private Term[] terms2 = null;
}
