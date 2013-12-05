package ru.unn.agile.converter.viewmodel;

import ru.unn.agile.converter.model.*;

import java.util.List;

public class ViewModel {
    private static final int ENTER_CODE = 10;
    private String inputValue = "";
    private String outputValue = "";
    private Unit inputUnit = Unit.meter;
    private Unit outputUnit = Unit.meter;

    private String status = Status.WAITING.toString();
    private boolean isConvertButtonEnabled = false;
    private ConverterOfLength converterOfLength = new ConverterOfLength();

    private ILogger logger;

    public ViewModel(ILogger logger){
        if (logger == null)
            throw new IllegalArgumentException();
        this.logger = logger;
    }

    public void setInputValue(String inputValue) {
        if (this.inputValue.equals(inputValue))
            return;
        this.inputValue = inputValue;
        if (parseInput())
            logger.Add(createMessageForAdding(), LogStatus.success);
        else
            logger.Add(createMessageForAdding(), LogStatus.error);
    }

    public void setOutputValue(String outputValue){
        this.outputValue = outputValue;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setInputUnit(Unit inputUnit) {
        if (!this.inputUnit.toString().equals(inputUnit.toString()))
        {
            this.inputUnit = inputUnit;
            logger.Add(createMessageForChangeInputUnit(), LogStatus.success);
        }
    }

    public void setOutputUnit(Unit outputUnit) {
        if (this.outputUnit != outputUnit)
        {
            this.outputUnit = outputUnit;
            logger.Add(createMessageForChangeOutputUnit(), LogStatus.success);
        }
    }

    public void setIsConvertButtonEnabled(boolean isConvertButtonEnabled){
        this.isConvertButtonEnabled = isConvertButtonEnabled;
    }

    public String getInputValue() {
        return this.inputValue;
    }

    public String getOutputValue() {
        return this.outputValue;
    }

    public Unit getInputUnit() {
        return this.inputUnit;
    }

    public Unit getOutputUnit() {
        return this.outputUnit;
    }

    public String getStatus() {
        return this.status;
    }

    public boolean getIsConvertButtonEnabled(){
        return this.isConvertButtonEnabled;
    }

    public void processKeyInTextField(int keyCode) {
        if (keyCode == ENTER_CODE) {
            parseInput();
            convert();
        } else {
            parseInput();
        }
    }

    private boolean parseInput() {
        try {
            if (!inputValue.isEmpty()) {
                Double.parseDouble(inputValue);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT.toString();
            isConvertButtonEnabled = false;
            //logger.Add(createMessageForAdding(), LogStatus.error);
            return false;
        }

        isConvertButtonEnabled = !inputValue.isEmpty();
        if (isConvertButtonEnabled) {
            status = Status.READY.toString();
            //logger.Add(createMessageForAdding(), LogStatus.success);
        } else {
            status = Status.WAITING.toString();
        }

        return isConvertButtonEnabled;
    }

    public String createMessageForConvert()
    {
        String message = "";
        message = "Doing success convert "
                + inputValue + " " + inputUnit
                + " to " + outputUnit
                + ". Result is " + outputValue
                + " " + outputUnit + ".";
        return message;
    }

    public String createMessageForAdding() {
        String message = "";
        message = "Adding input value: "
                + inputValue + ".";
        return message;
    }

    public String createMessageForChangeInputUnit() {
        String message = "";
        message = "Change input unit: "
                + inputUnit + ".";
        return message;
    }

    public String createMessageForChangeOutputUnit() {
        String message = "";
        message = "Change output unit: "
                + outputUnit + ".";
        return message;
    }

    public void convert()
    {
        if (!parseInput()) return;

        outputValue = Double.toString(converterOfLength.convertLength(inputUnit, outputUnit, Double.parseDouble(inputValue)));
        status = Status.SUCCESS.toString();
        logger.Add(createMessageForConvert(), LogStatus.success);
    }

    public List<String> getLog(LogStatus logStatus) {
        List<String> log = logger.getLog(logStatus);
        return log;
    }

}
