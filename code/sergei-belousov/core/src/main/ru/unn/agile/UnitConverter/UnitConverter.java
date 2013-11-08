package ru.unn.agile.UnitConverter;

public class UnitConverter {
    private UnitConvertTable unitConvertTable;

    public UnitConverter() {
        unitConvertTable = new UnitConvertTable();
    }

    public boolean isEmpty() {
        return unitConvertTable.isEmpty();
    }

    public void addPair(String from, String to, double factor) {
        unitConvertTable.addPair(new UnitKey(from, to), factor);
    }

    private UnitKey getKeyFromString(String fromToFormatString) {
        String[] tokens = fromToFormatString.split("-");
        if(tokens.length == 1) {
            return null;
        }
        return new UnitKey(tokens[0], tokens[1]);
    }

    private double getFactor(String fromToFormatString) throws UnitConvertTableException {
        if(fromToFormatString.equals("")) {
            throw new UnitConvertTableException("format string empty.");
        }
        try {
            return unitConvertTable.getFactor(getKeyFromString(fromToFormatString));
        }catch(UnitConvertTableException e)
        {
            throw e;
        }
    }

    public Unit convert(String fromToFormatString, double value) throws UnitConvertTableException {
        try {
            return getConvertedUnit(fromToFormatString, value);
        } catch (UnitConvertTableException e){
            throw e;
        }
    }

    public Unit convert(Unit from, String to) throws UnitConvertTableException {
        try{
            String fromToFormatString = from.getType() + "-" + to;
            return getConvertedUnit(fromToFormatString, from.getValue());
        }catch(UnitConvertTableException e){
            throw e;
        }
    }

    private Unit getConvertedUnit(String fromToFormatString, double value) throws UnitConvertTableException{
        try {
            String targetType = getKeyFromString(fromToFormatString).getTo();
            double targetValue = getFactor(fromToFormatString) * value;
            return new Unit(targetType, targetValue);
        } catch (UnitConvertTableException e){
            throw e;
        }
    }
}
