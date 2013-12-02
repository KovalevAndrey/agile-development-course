package ru.unn.agile.MathStatistic.viewModel;

import ru.unn.agile.MathStatistic.model.MathStatistic;

public class ViewModel {
    public final int ENTER_CODE = 10;
    public String inputData = "";
    public Statistic operation = Statistic.EXPECTED_VALUE;
    public String outputData = "";
    public String status = Status.WAITING;
    public boolean isCalculateButtonEnabled = false;
    private float[] inputArray;

    public class Status {
        public static final String WAITING = "Provide input data";
        public static final String READY = "Press 'Calc it!' or Enter";
        public static final String BAD_INPUT = "Bad input data";
        public static final String SUCCESS = "Success";
        public static final String ERROR = "Internal error";
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

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            convertToArrayOfDoubles();
            if (status == Status.READY) {
                calcIt();
            }
        } else {
            convertToArrayOfDoubles();
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
    }

    public float[] getInputArray() {
        return inputArray;
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
    }
}
