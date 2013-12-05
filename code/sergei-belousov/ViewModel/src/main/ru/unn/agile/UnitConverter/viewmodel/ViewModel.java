package ru.unn.agile.UnitConverter.viewmodel;

import ru.unn.agile.UnitConverter.*;

import java.util.*;

public class ViewModel {
    public String fromValueText = "";
    public String fromUnitText = "";
    public String toValueText = "";
    public String toUnitText = "";
    public HashSet<String> unitList = new HashSet<String>();
    public boolean addMode = false;
    public String actionButtonText = "convert";
    public boolean fromUnitEditable = false;
    public boolean toUnitEditable = false;
    public boolean toValueEnabled = false;
    public String errorMsg = "";
    private WeightConverter weightConverter = new WeightConverter();
    private ILogger logger;
    String tag;
    String logMsg;

    public ViewModel(ILogger logger) {
        if(logger == null) {
            throw new RuntimeException("Logger must not be null");
        }
        this.logger = logger;
        HashMap<UnitKey, Double> table = weightConverter.getTable();
        for(Map.Entry<UnitKey, Double> key: table.entrySet()) {
            unitList.add(key.getKey().getFrom());
            unitList.add(key.getKey().getTo());
        }
        tag = "ViewModel";
        logMsg = "start program";
        logger.Log(tag, logMsg);
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private boolean parseInput() {
        tag = "parseInput";
        try {
            if(!fromValueText.isEmpty()) {
                Double.parseDouble(fromValueText);
            }
            if(!toValueText.isEmpty() && addMode) {
                Double.parseDouble(toValueText);
            }
        } catch (Exception e) {
            logger.Log(tag, e.getMessage());
            return false;
        }
        if(fromUnitText.isEmpty() || toUnitText.isEmpty() || fromValueText.isEmpty() || (toValueText.isEmpty() && addMode) ) {
            logger.Log(tag, "incorrect input");
            return false;
        }
        logger.Log(tag, "correct input");
        return true;
    }

    private void addPair() {
        tag = "addPair";
        double factor = Double.parseDouble(toValueText) / Double.parseDouble(fromValueText);
        weightConverter.addPair(fromUnitText, toUnitText, factor);
        logMsg = "add pair: " + fromUnitText + "-" + toUnitText + ", factor = " + factor + "\n";
        weightConverter.addPair(fromUnitText, fromUnitText, 1.);
        logMsg += "add pair: " + fromUnitText + "-" + fromUnitText + ", factor = 1.0\n";
        weightConverter.addPair(toUnitText, toUnitText, 1);
        logMsg += "add pair: " + toUnitText + "-" + toUnitText + ", factor = 1.0\n";
        unitList.add(fromUnitText);
        unitList.add(toUnitText);
        logger.Log(tag, logMsg);
    }

    public void setAddMode(boolean status) {
        tag = "setAddMode";
        addMode = status;
        if(addMode) {
            actionButtonText = logMsg = "add";
            fromUnitEditable = true;
            toUnitEditable = true;
            toValueEnabled = true;
        } else {
            actionButtonText = logMsg = "convert";
            fromUnitEditable = false;
            toUnitEditable = false;
            toValueEnabled = false;
            toValueText = "";
        }
        errorMsg = "";
        logger.Log(tag, logMsg);
    }

    private void convert() throws UnitConvertTableException {
        tag = "convert";
        try {
            logMsg = "fromUnit: " + fromUnitText + ", toUnit: " + toUnitText + "\n";

            Unit from = new Unit(fromUnitText, Double.parseDouble(fromValueText));
            toValueText = weightConverter.convert(from, toUnitText).getValue().toString();
            logMsg += "fromValue: " + fromValueText + ", toValue: " + toValueText;
            logger.Log(tag, logMsg);
        } catch(UnitConvertTableException e) {
            logger.Log(tag, e.getMessage());
            throw e;
        }
    }

    public void processKey(){
        if(!parseInput()) {
            errorMsg = "incorrect input data.";
            return;
        }
        if(addMode) {
            addPair();
            errorMsg = "key added.";
        } else {
            try {
                convert();
            } catch (UnitConvertTableException e) {
                errorMsg = e.getMessage();
                return;
            }
            errorMsg = "";
        }
    }
}
