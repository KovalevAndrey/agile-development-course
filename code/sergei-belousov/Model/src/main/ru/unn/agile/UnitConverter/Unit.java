package ru.unn.agile.UnitConverter;

public class Unit {
    private String type;
    private Double value;

    public Unit(String type, Double value){
        setType(type);
        setValue(value);
    }

    public Unit(Unit obj){
        setType(obj.getType());
        setValue(obj.getValue());
    }

    public String getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isEqualType(Unit obj) {
        return type.equals(obj.getType());
    }

    public  boolean isEqualValue(Unit obj) {
        return value.equals(obj.getValue());
    }

    public boolean isEqual(Unit obj) {
        return isEqualType(obj) && isEqualValue(obj);
    }
}
