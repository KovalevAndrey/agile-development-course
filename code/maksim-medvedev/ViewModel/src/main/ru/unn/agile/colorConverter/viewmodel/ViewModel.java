package ru.unn.agile.colorConverter.viewmodel;

import ru.unn.agile.colorConverter.model.*;

import java.util.List;

public class ViewModel {
    public static final int ENTER_CODE = 10;

    private ILogger logger;

    private String firstColorFirstValue = "255";
    private String firstColorSecondValue = "0";
    private String firstColorThirdValue = "0";
    private ColorSpace firstColorSpace = ColorSpace.RGB;

    private String secondColorSecondValue = "";
    private String secondColorFirstValue = "";
    private String secondColorThirdValue = "";
    private ColorSpace secondColorSpace = ColorSpace.RGB;

    private String status = Status.WAITING;
    private boolean isConvertButtonEnabled = true;

    public ViewModel(ILogger logger) {
        this.logger = logger;
    }

    public void convert() {
        if (!parseInput()) return;

        logger.log(LogMessage.ConvertClicked + getValuesForLog());

        Color colorToConvert = getColorByValues();
        Converter converter = new Converter();

        try {
            switch (secondColorSpace) {
                case RGB:
                    RGBColor rgb = converter.toRGB(colorToConvert);

                    secondColorFirstValue = String.valueOf(rgb.R);
                    secondColorSecondValue = String.valueOf(rgb.G);
                    secondColorThirdValue = String.valueOf(rgb.B);
                    break;
                case HSV:
                    HSVColor hsv = converter.toHSV(colorToConvert);

                    secondColorFirstValue = String.valueOf(hsv.H);
                    secondColorSecondValue = String.valueOf(hsv.S);
                    secondColorThirdValue = String.valueOf(hsv.V);
                    break;
                case LAB:
                    LABColor lab = converter.toLAB(colorToConvert);

                    secondColorFirstValue = String.valueOf(lab.L);
                    secondColorSecondValue = String.valueOf(lab.A);
                    secondColorThirdValue = String.valueOf(lab.B);
                    break;
            }

            status = Status.SUCCESS;
        }
        catch (ColorException e) {
            status = Status.ERROR_CONVERTING;
        }
    }

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            convert();
        } else {
            parseInput();
        }
    }

    private boolean isInputAvailable() {
        return !firstColorFirstValue.isEmpty() &&
                !firstColorSecondValue.isEmpty() &&
                !firstColorThirdValue.isEmpty();
    }

    private boolean parseInput() {
        try {
            if (!firstColorFirstValue.isEmpty()) Integer.parseInt(firstColorFirstValue);
            if (!firstColorSecondValue.isEmpty()) Integer.parseInt(firstColorSecondValue);
            if (!firstColorThirdValue.isEmpty()) Integer.parseInt(firstColorThirdValue);
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isConvertButtonEnabled = false;
            return false;
        }

        isConvertButtonEnabled = isInputAvailable();
        if (isConvertButtonEnabled) {
            status = Status.READY;
        } else {
            status = Status.WAITING;
        }

        return isConvertButtonEnabled;
    }

    private Color getColorByValues() {
        int value_1 = Integer.parseInt(firstColorFirstValue);
        int value_2 = Integer.parseInt(firstColorSecondValue);
        int value_3 = Integer.parseInt(firstColorThirdValue);

        switch (firstColorSpace) {
            case RGB:
                return new RGBColor(value_1, value_2, value_3);
            case HSV:
                return new HSVColor(value_1, value_2, value_3);
            case LAB:
                return new LABColor(value_1, value_2, value_3);
        }

        return null;
    }

    public String getFirstColorFirstValue() {
        return firstColorFirstValue;
    }

    public void setFirstColorFirstValue(String firstColorFirstValue) {
        if (this.firstColorFirstValue.equals(firstColorFirstValue))
            return;

        this.firstColorFirstValue = firstColorFirstValue;
        logger.log(LogMessage.ValueChanged + getValuesForLog());
    }

    public String getFirstColorSecondValue() {
        return firstColorSecondValue;
    }

    public void setFirstColorSecondValue(String firstColorSecondValue) {
        if (this.firstColorSecondValue.equals(firstColorSecondValue))
            return;

        this.firstColorSecondValue = firstColorSecondValue;
        logger.log(LogMessage.ValueChanged + getValuesForLog());
    }

    public String getFirstColorThirdValue() {
        return firstColorThirdValue;
    }

    public void setFirstColorThirdValue(String firstColorThirdValue) {
        if (this.firstColorThirdValue.equals(firstColorThirdValue))
            return;

        this.firstColorThirdValue = firstColorThirdValue;
        logger.log(LogMessage.ValueChanged + getValuesForLog());
    }

    public ColorSpace getFirstColorSpace() {
        return firstColorSpace;
    }

    public void setFirstColorSpace(ColorSpace firstColorSpace) {
        if (this.firstColorSpace == firstColorSpace)
            return;

        this.firstColorSpace = firstColorSpace;
        logger.log(LogMessage.ColorSpaceChanged + getValuesForLog());
    }

    public String getSecondColorSecondValue() {
        return secondColorSecondValue;
    }

    public String getSecondColorFirstValue() {
        return secondColorFirstValue;
    }

    public String getSecondColorThirdValue() {
        return secondColorThirdValue;
    }

    public ColorSpace getSecondColorSpace() {
        return secondColorSpace;
    }

    public void setSecondColorSpace(ColorSpace secondColorSpace) {
        if (this.secondColorSpace == secondColorSpace)
            return;

        this.secondColorSpace = secondColorSpace;
        logger.log(LogMessage.ColorSpaceChanged + getValuesForLog());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnabled;
    }

    public List<String> getLog() {
        return logger.getContent();
    }

    private String getValuesForLog() {
        return String.format(
                " Values are: [%s, %s, %s]; %s -> %s",

                firstColorFirstValue,
                firstColorSecondValue,
                firstColorThirdValue,

                firstColorSpace.toString(),
                secondColorSpace.toString());
    }

    public enum ColorSpace {
        RGB("RGB"), HSV("HSV"), LAB("LAB");
        private final String name;

        private ColorSpace(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public class Status {
        public static final String WAITING = "Please provide input data";
        public static final String READY = "Press 'Convert' or Enter";
        public static final String ERROR_CONVERTING = "Couldn't convert specified color";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";
    }

    public class LogMessage {
        public static final String ValueChanged = "Color value changed.";
        public static final String ColorSpaceChanged = "Color space changed.";
        public static final String ConvertClicked = "Convert button clicked.";
    }
}