package ru.unn.agile.converter.viewmodel;

import ru.unn.agile.converter.model.*;

public class ViewModel {
    public static final int ENTER_CODE = 10;
    public String inputValue = "";
    public String outputValue = "";
    public Unit inputUnit = Unit.meter;
    public Unit outputUnit = Unit.meter;

    public String status = Status.WAITING;
    public boolean isConvertButtonEnabled = false;
    public ConverterOfLength converterOfLength = new ConverterOfLength();

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            convert();
        } else {
            parseInput();
        }
    }

    private boolean parseInput() {
        try {
            if (!inputValue.isEmpty()) Double.parseDouble(inputValue);
            if (!outputValue.isEmpty()) Double.parseDouble(outputValue);
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isConvertButtonEnabled = false;
            return false;
        }

        isConvertButtonEnabled = !inputValue.isEmpty();
        if (isConvertButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }

        return isConvertButtonEnabled;
    }

    public void convert()
    {
        if (!parseInput()) return;

        outputValue = Double.toString(converterOfLength.convertLength(inputUnit, outputUnit, Double.parseDouble(inputValue)));
        status = Status.SUCCESS;
    }


    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Convert'";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }
}
