package ru.unn.agile.MathStatistic.viewModel;

import ru.unn.agile.MathStatistic.model.MathStatistic;

public class ViewModel {
    public final int ENTER_CODE = 10;
    public String inputData = "";
    public Statistic operation = Statistic.EXPECTEDVALUE;
    public String outputData = "";
    public String status = Status.WAITING;
    public boolean isCalculateButtonEnabled = false;
    private float[] inputArray;

    public enum Statistic {
        EXPECTEDVALUE("Expected value"),
        VARIANCE("Variance"),
        THIRDCENTRALMOMENT("Third central moment"),
        FOURTHCENTRALMOMENT("Fourth central moment");
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
            calcIt();
        } else {
            convertToArrayOfDoubles();
        }
    }

    public class Status {
        public static final String WAITING = "Waiting for input data";
        public static final String READY = "Press 'Calc it!' or Enter";
        public static final String BAD_INPUT = "Bad input data";
        public static final String SUCCESS = "Success";
        public static final String ERROR = "Internal error";
    }

    public void convertToArrayOfDoubles() {
        int indexInputArray = 0;
        isCalculateButtonEnabled = true;
        status = Status.READY;
          try {
              inputData = inputData.replaceAll("\\s+"," ");
              inputData = inputData.replaceAll("^ ","");
              inputData = inputData.replaceAll(" $","");
              String[] splittedData = inputData.split(" ");
              inputArray = new float[splittedData.length];
              for(String item:  splittedData) {
                  inputArray[indexInputArray] = Float.parseFloat(item);
                  indexInputArray++;
              }

          } catch (Exception e) {
              inputArray = null;
              isCalculateButtonEnabled = false;
              status = Status.BAD_INPUT;
          }
    }

    public float[] getInputArray(){
        return inputArray;
    }

    public void calcIt(){
        convertToArrayOfDoubles();
        if(status.equals(Status.READY)) {
            float result = 0;
            status = Status.SUCCESS;
            switch (operation) {
                case EXPECTEDVALUE:
                    result = MathStatistic.calcExpectedValue(inputArray);
                    break;
                case VARIANCE:
                    result = MathStatistic.calcVariance(inputArray);
                    break;
                case THIRDCENTRALMOMENT:
                    result = MathStatistic.calcThirdCentralMoment(inputArray);
                    break;
                case FOURTHCENTRALMOMENT:
                    result = MathStatistic.calcFourthCentralMoment(inputArray);
                    break;
                default:
                    status = Status.ERROR;
                    isCalculateButtonEnabled = false;
            }

            if(!status.equals(Status.ERROR)) {
                try{
                    outputData = Float.toString(result);
                }
                catch (Exception e) {
                    status = Status.ERROR;
                    isCalculateButtonEnabled = false;
                }
            }
        }
    }
}
