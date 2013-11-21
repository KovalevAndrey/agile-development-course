package ru.unn.agile.TC.viewmodel;

import ru.unn.agile.TC.AvailableScales;
import ru.unn.agile.TC.Temperature;

import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.*;

public class ViewModel {
    private final ILogger logger;

    public String input = "0.0";
    public AvailableScales inputScale = AvailableScales.Celsius;
    public String result = "";
    public AvailableScales resultScale = AvailableScales.Fahrenheit;
    public String status = "";

    public ViewModel(ILogger logger) {
        if(logger == null)
            throw new IllegalArgumentException();

        this.logger = logger;
    }

    public ViewModel() {
        this.logger = null;
    }

    public void convert() {
        if (inputScale == null) {
            status = Status.STATUS_INPUT_SCALE_NULL;
            if(logger != null)
                logger.putMessage(new LogMessage(LOG_ERROR_INPUT_SCALE_IS_NULL));
            return;
        }

        if (resultScale == null) {
            status = Status.STATUS_RESULT_SCALE_NULL;
            if(logger != null)
                logger.putMessage(new LogMessage(LOG_ERROR_RESULT_SCALE_IS_NULL));
            return;
        }

        try {
            _convert();
        }
        catch (NumberFormatException e) {
            status = Status.STATUS_WRONG_INPUT_STRING;
            if(logger != null)
                logger.putMessage(new LogMessage(LOG_ERROR_WRONG_INPUT_STRING));
            return;
        }

        status = Status.STATUS_VIEW_MODEL_OK;
        if(logger != null)
            logger.putMessage(new LogMessage(LOG_INFO_VM_OK));
    }

    public void inputParametersChanged() {
        status = String.format(Status.STATUS_INPUTS, input, inputScale, resultScale);
        if(logger != null)
            logger.putMessage(new LogMessage(LOG_INFO_INPUT, input, inputScale, resultScale));
    }

    private void _convert() {
        double _input = Double.parseDouble(input);
        Temperature t = new Temperature(_input, inputScale);
        result = t.scaleTo(resultScale).toString();

        if(logger != null)
            logger.putMessage(new LogMessage(LOG_INFO_CONVERT, t, result));
    }

    public class Status {
        public static final String STATUS_INPUTS = "You are going to convert %s from %s to %s. Let's have some fun.";

        public static final String STATUS_INPUT_SCALE_NULL = "Please select input temperature scale.";
        public static final String STATUS_RESULT_SCALE_NULL = "Please select result temperature scale.";
        public static final String STATUS_WRONG_INPUT_STRING = "Please specify only numbers separated by one dot.";
        public static final String STATUS_VIEW_MODEL_OK = "Success.";
    }
}
