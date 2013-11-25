package ru.unn.agile.UnitConverter;

import java.util.HashMap;

public class UnitConvertTable {
    private HashMap<UnitKey, Double> converterTable;

    public UnitConvertTable() {
        converterTable = new HashMap<UnitKey, Double>();
    }

    public boolean isEmpty() {
        return converterTable.isEmpty();
    }

    public void addPair(UnitKey pair, double factor) {
        converterTable.put(pair, new Double(factor));
        converterTable.put(pair.reverse(), new Double(1. / factor));
    }

    public double getFactor(UnitKey pair) throws UnitConvertTableException {
        if(!converterTable.containsKey(pair)) {
            throw new UnitConvertTableException("key not found.");
        }
        else {
            return converterTable.get(pair);
        }
    }

    public void clear() {
        converterTable.clear();
    }

    public HashMap<UnitKey, Double> getTable() {
        return converterTable;
    }
}
