package ru.unn.agile.AreaConverter.viewmodel;

import ru.unn.agile.areaConverter.ScaleTable;
import ru.unn.agile.areaConverter.AreaConverter;

public class ViewModel {
    public String input = "0.0";
    public String inputScale = "Metre";
    public String result = "";
    public String resultScale = "Hectare";
    public String status = "";

    public void convert() {
        try {
            ScaleTable.valueOf(inputScale);
        }
        catch (Exception e) {
           status = Status.INCORRECT_INPUT_SCALE;
            return;
        }

        try {
            ScaleTable.valueOf(resultScale);
        }
        catch (Exception e) {
            status = Status.INCORRECT_OUTINPUT_SCALE;
            return;
        }

        try {
            double inputArea = Double.parseDouble(input);
            double outputArea = AreaConverter.convert(inputArea, inputScale, resultScale);
            result = Double.toString(outputArea);
        }
        catch (NumberFormatException e) {
            status = Status.INCORRECT_INPUT_AREA;
            return;
        }
        catch (IllegalArgumentException e) {
            status = Status.NEGATIVE_INPUT_AREA;
            return;
        }

        status = Status.MODEL_OK;
    }

    public class Status {
        public static final String INCORRECT_INPUT_AREA = "Please input non-negative value separated by dot";
        public static final String NEGATIVE_INPUT_AREA = "Please don't use negative area";
        public static final String INCORRECT_INPUT_SCALE = "Please choose correct input scale";
        public static final String INCORRECT_OUTINPUT_SCALE = "Please choose correct input scale";
        public static final String MODEL_OK = "Success";
    }
}
