package ru.unn.agile.UnitConverter;

import java.util.HashMap;

public class UnitConvertTable {
    private HashMap<UnitKey, Double> weightConvertTable;

    public UnitConvertTable() {
        weightConvertTable = new HashMap<UnitKey, Double>();
    }

    public boolean isEmpty() {
        return weightConvertTable.isEmpty();
    }

    public void addPair(UnitKey pair, double factor) {
        weightConvertTable.put(pair, new Double(factor));
        weightConvertTable.put(pair.reverse(), new Double(1. / factor));
    }

    public double getFactor(UnitKey pair) throws UnitConvertTableException {
        if(!weightConvertTable.containsKey(pair)) {
            throw new UnitConvertTableException("key not found.");
        }
        else {
            return weightConvertTable.get(pair);
        }
    }
}
