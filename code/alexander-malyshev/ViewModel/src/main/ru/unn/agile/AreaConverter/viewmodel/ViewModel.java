package ru.unn.agile.AreaConverter.viewmodel;

import ru.unn.agile.areaConverter.ScaleTable;
import ru.unn.agile.areaConverter.AreaConverter;

public class ViewModel {
    public String input = "0.0";
    public ScaleTable inputScale = ScaleTable.Metre;
    public String result = "";
    public ScaleTable resultScale = ScaleTable.Hectare;
    public String status = "";

    public void convert() {
        if (inputScale == null) {
            status = Status.NULL_INPUT_SCALE;
            return;
        }

        if (resultScale == null) {
            status = Status.NULL_RESULT_SCALE;
            return;
        }

        try {
            double inputArea = Double.parseDouble(input);
            double outputArea = AreaConverter.convert(inputArea, inputScale.name(), resultScale.name());
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
        public static final String NULL_INPUT_SCALE = "Input scale is null";
        public static final String NULL_RESULT_SCALE = "Result scale is null";
        public static final String MODEL_OK = "Success";
    }
}
