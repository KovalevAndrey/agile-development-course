package ru.unn.agile.TemperatureConverter.viewmodel;

import ru.unn.agile.TemperatureConverter.AvailableScales;
import ru.unn.agile.TemperatureConverter.Temperature;

public class ViewModel {
    public String input = "0.0";
    public AvailableScales inputScale = AvailableScales.Celsius;
    public String result = "";
    public AvailableScales resultScale = AvailableScales.Fahrenheit;
    public String status = "";

    public void convert() {
        if (inputScale == null) {
            status = Status.INPUT_SCALE_NULL;
            return;
        }

        if (resultScale == null) {
            status = Status.RESULT_SCALE_NULL;
            return;
        }

        try {
            _convert();
        }
        catch (NumberFormatException e) {
            status = Status.WRONG_INPUT_STRING;
            return;
        }

        status = Status.MODEL_OK;
    }

    private void _convert() {
        double _input = Double.parseDouble(input);
        Temperature t = new Temperature(_input, inputScale);
        result = t.scaleTo(resultScale).toString();
    }

    public class Status {
        public static final String INPUT_SCALE_NULL = "Please select input temperature scale.";
        public static final String RESULT_SCALE_NULL = "Please select result temperature scale.";
        public static final String WRONG_INPUT_STRING = "Please specify only numbers separated by one dot.";
        public static final String MODEL_OK = "Success.";
    }
}
