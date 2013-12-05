package ru.unn.agile.MathStatistic.viewModel;

import ru.unn.agile.MathStatistic.model.MathStatistic;

import java.util.ArrayList;

public class ViewModel {
    public final int ENTER_CODE = 10;
    ILogger logger;
    private String inputData = "";
    private Statistic operation = Statistic.EXPECTED_VALUE;
    private String outputData = "";
    private String status = Status.WAITING;
    private boolean isCalculateButtonEnabled = false;
    private float[] inputArray;

    public ViewModel() {
        inputData = "";
        operation = Statistic.EXPECTED_VALUE;
        outputData = "";
        status = Status.WAITING;
        isCalculateButtonEnabled = false;
        logger = null;
    }

    public ViewModel(ILogger actLogger) {
        if (actLogger == null) {
            throw new IllegalArgumentException("Logger can not be a null");
        }
        inputData = "";
        operation = Statistic.EXPECTED_VALUE;
        outputData = "";
        status = Status.WAITING;
        isCalculateButtonEnabled = false;
        logger = actLogger;

        logger.saveToLog("Program successfully started", ILogger.MessageType.INFO);
        printActState();
    }

    public ArrayList<String> getEntireLog() {
        if (logger != null) {
            return logger.getEntireLog();
        } else {
            return null;
        }

    }

    public ArrayList<String> getParticularType(ILogger.MessageType type) {
        if (logger != null) {
            return logger.getParticularType(type);
        } else {
            return null;
        }
    }

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            convertToArrayOfDoubles();
            if (status == Status.READY) {
                calcIt();
            }
        }
    }

    public void convertToArrayOfDoubles() {
        int indexInputArray = 0;
        isCalculateButtonEnabled = false;
        if (!inputData.isEmpty()) {
            try {
                inputData = inputData.replaceAll("\\s+", " ");
                inputData = inputData.replaceAll("^ ", "");
                inputData = inputData.replaceAll(" $", "");
                String[] splittedData = inputData.split(" ");
                inputArray = new float[splittedData.length];
                for (String item : splittedData) {
                    inputArray[indexInputArray] = Float.parseFloat(item);
                    indexInputArray++;
                }
                status = Status.READY;
                isCalculateButtonEnabled = true;

            } catch (Exception e) {
                inputArray = null;
                isCalculateButtonEnabled = false;
                status = Status.BAD_INPUT;
            }
        } else {
            status = Status.WAITING;
        }
        outputData = "";
        printActState();
    }

    public void calcIt() {
        float result = 0;
        status = Status.SUCCESS;
        switch (operation) {
            case EXPECTED_VALUE:
                result = MathStatistic.calcExpectedValue(inputArray);
                break;
            case VARIANCE:
                result = MathStatistic.calcVariance(inputArray);
                break;
            case THIRD_CENTRAL_MOMENT:
                result = MathStatistic.calcThirdCentralMoment(inputArray);
                break;
            case FOURTH_CENTRAL_MOMENT:
                result = MathStatistic.calcFourthCentralMoment(inputArray);
                break;
            default:
                status = Status.ERROR;
                isCalculateButtonEnabled = false;
        }

        if (!status.equals(Status.ERROR)) {
            try {
                outputData = Float.toString(result);
            } catch (Exception e) {
                status = Status.ERROR;
                isCalculateButtonEnabled = false;
            }
        }
        printActState();
    }

    private void printActState() {
        if (logger != null) {
            ILogger.MessageType type;
            String status = getStatus();
            String newLogEntry = "state:[";
            newLogEntry += getInputData() + "|";
            newLogEntry += getOperationInString() + "|";
            newLogEntry += getOutputData() + "|";
            newLogEntry += getStatus() + "|";
            if (status.equals(Status.WAITING)) {
                type = ILogger.MessageType.INFO;

            } else if (status.equals(Status.READY)) {
                type = ILogger.MessageType.INFO;

            } else if (status.equals(Status.BAD_INPUT)) {
                type = ILogger.MessageType.WARNING;

            } else if (status.equals(Status.SUCCESS)) {
                type = ILogger.MessageType.INFO;

            } else if (status.equals(Status.ERROR)) {
                type = ILogger.MessageType.ERROR;

            } else {
                throw new IllegalArgumentException("unknown type");
            }
            newLogEntry += getCalculateButtonStateInString() + "]";
            logger.saveToLog(newLogEntry, type);
        }
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String actInputData) {
        inputData = actInputData;
    }

    public Statistic getOperation() {
        return operation;
    }

    public void setOperation(Statistic actOperation) {
        if (operation != actOperation) {
            operation = actOperation;
            printActState();
        }
    }

    public String getOperationInString() {
        return operation.toString();
    }

    public String getOutputData() {
        return outputData;
    }

    public String getStatus() {
        return status;
    }

    public boolean getCalculateButtonState() {
        return isCalculateButtonEnabled;
    }

    public String getCalculateButtonStateInString() {
        if (isCalculateButtonEnabled == true) {
            return "enabled";
        } else {
            return "disabled";
        }
    }

    public float[] getInputArray() {
        return inputArray;
    }

    public enum Statistic {
        EXPECTED_VALUE("Expected value"),
        VARIANCE("Variance"),
        THIRD_CENTRAL_MOMENT("Third central moment"),
        FOURTH_CENTRAL_MOMENT("Fourth central moment");
        private final String name;

        private Statistic(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public class Status {
        public static final String WAITING = "Provide input data";
        public static final String READY = "Press 'Calc it!' or Enter";
        public static final String BAD_INPUT = "Bad input data";
        public static final String SUCCESS = "Success";
        public static final String ERROR = "Internal error";
    }

}
