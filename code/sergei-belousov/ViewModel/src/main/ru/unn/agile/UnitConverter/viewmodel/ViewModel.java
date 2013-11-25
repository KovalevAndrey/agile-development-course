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

    public ViewModel() {
        HashMap<UnitKey, Double> table = weightConverter.getTable();
        for(Map.Entry<UnitKey, Double> key: table.entrySet()) {
            unitList.add(key.getKey().getFrom());
            unitList.add(key.getKey().getTo());
        }
    }

    private boolean parseInput() {
        try {
            if(!fromValueText.isEmpty()) Double.parseDouble(fromValueText);
            if(!toValueText.isEmpty() && addMode) Double.parseDouble(toValueText);
        } catch (Exception e) {
            return false;
        }
        if(fromUnitText.isEmpty() || toUnitText.isEmpty() || fromValueText.isEmpty() || (toValueText.isEmpty() && addMode) ) return false;

        return true;
    }

    private void addPair() {
        double factor = Double.parseDouble(toValueText) / Double.parseDouble(fromValueText);
        weightConverter.addPair(fromUnitText, toUnitText, factor);
        weightConverter.addPair(fromUnitText, fromUnitText, 1.);
        weightConverter.addPair(toUnitText, toUnitText, 1);
        unitList.add(fromUnitText);
        unitList.add(toUnitText);
    }

    public void setAddMode(boolean status) {
        addMode = status;
        if(addMode) {
            actionButtonText = "add";
            fromUnitEditable = true;
            toUnitEditable = true;
            toValueEnabled = true;
        } else {
            actionButtonText = "convert";
            fromUnitEditable = false;
            toUnitEditable = false;
            toValueEnabled = false;
            toValueText = "";
        }
        errorMsg = "";
    }

    private void convert() throws UnitConvertTableException {
        try {
            Unit from = new Unit(fromUnitText, Double.parseDouble(fromValueText));
            toValueText = weightConverter.convert(from, toUnitText).getValue().toString();
        } catch(UnitConvertTableException e) {
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
        }
        else {
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
