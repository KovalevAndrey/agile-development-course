package ru.unn.agile.colorConverter.viewmodel;

import ru.unn.agile.colorConverter.model.*;

public class ViewModel {
    public static final int ENTER_CODE = 10;

    public String firstColorFirstValue = "255";
    public String firstColorSecondValue = "0";
    public String firstColorThirdValue = "0";
    public ColorSpace firstColorSpace = ColorSpace.RGB;

    public String secondColorSecondValue = "";
    public String secondColorFirstValue = "";
    public String secondColorThirdValue = "";
    public ColorSpace secondColorSpace = ColorSpace.RGB;

    public String status = Status.WAITING;
    public boolean isConvertButtonEnabled = true;

    public void convert() {
        if (!parseInput()) return;

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
}