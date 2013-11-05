package ru.unn.agile.MathStatistic.viewModel;

import ru.unn.agile.MathStatistic.model.MathStatistic;

import java.util.Arrays;
import java.util.Iterator;

public class ViewModel {
    public String inputData = "";
    public Statistic operation = Statistic.EXPECTEDVALUE;
    public String outputData = "";

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

    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Calc it!' or Enter";
        public static final String BAD_INPUT = "Bad input data";
        public static final String SUCCESS = "Success";
    }

    private boolean isInputDataOK(){
        return false;
    }

    public void calcIt() {
        if(isInputDataOK()) {

        }
    }

    public float[] convertToArrayOfDoubles(String inputData) {
        float[] inputArray = null;
        int indexInputArray = 0;
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
          }
        return inputArray;
    }
}
