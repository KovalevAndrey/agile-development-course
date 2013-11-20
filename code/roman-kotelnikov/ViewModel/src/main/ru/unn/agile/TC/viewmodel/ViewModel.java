package ru.unn.agile.TC.viewmodel;

import ru.unn.agile.TC.AvailableScales;
import ru.unn.agile.TC.Temperature;

import static ru.unn.agile.TC.viewmodel.ILogger.Errors.*;
import static ru.unn.agile.TC.viewmodel.ILogger.Messages.*;

public class ViewModel {
    private final ILogger logger;

    public String input = "0.0";
    public AvailableScales inputScale = AvailableScales.Celsius;
    public String result = "";
    public AvailableScales resultScale = AvailableScales.Fahrenheit;
    public String status = "";

    public ViewModel(ILogger logger) {
        this.logger = logger;
        input = "0.0";
        inputScale = AvailableScales.Celsius;
        result = "";
        resultScale = AvailableScales.Fahrenheit;
        status = "";
    }

    public void convert() {
        if (inputScale == null) {
            status = Status.STATUS_INPUT_SCALE_NULL;
            logger.putError(LOG_ERROR_INPUT_SCALE_IS_NULL);
            return;
        }

        if (resultScale == null) {
            status = Status.STATUS_RESULT_SCALE_NULL;
            logger.putError(LOG_ERROR_RESULT_SCALE_IS_NULL);
            return;
        }

        try {
            _convert();
        }
        catch (NumberFormatException e) {
            status = Status.STATUS_WRONG_INPUT_STRING;
            logger.putError(LOG_ERROR_WRONG_INPUT_STRING);
            return;
        }

        status = Status.STATUS_VIEW_MODEL_OK;
        logger.putMessage(LOG_VIEW_MODEL_OK);
    }

    public void inputFocusLost() {
        String logMessage = String.format(LOG_INPUT_MESSAGE, input, inputScale, resultScale);
        status = String.format(Status.STATUS_INPUTS, input, inputScale, resultScale);
        logger.putMessage(logMessage);
    }

    public ILogger getLogger() {
        return logger;
    }

    private void _convert() {
        double _input = Double.parseDouble(input);
        Temperature t = new Temperature(_input, inputScale);
        result = t.scaleTo(resultScale).toString();

        String logMessage = String.format(LOG_CONVERT_MESSAGE, t, result);
        logger.putMessage(logMessage);
    }

    public class Status {
        public static final String STATUS_INPUTS = "You are going to convert %s from %s to %s. Let's have some fun.";

        public static final String STATUS_INPUT_SCALE_NULL = "Please select input temperature scale.";
        public static final String STATUS_RESULT_SCALE_NULL = "Please select result temperature scale.";
        public static final String STATUS_WRONG_INPUT_STRING = "Please specify only numbers separated by one dot.";
        public static final String STATUS_VIEW_MODEL_OK = "Success.";
    }
}
