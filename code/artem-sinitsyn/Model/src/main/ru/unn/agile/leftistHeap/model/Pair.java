package ru.unn.agile.leftistHeap.model;

public class Pair {
    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        if (key >= 0)
            this.key = key;
        else
            throw new IllegalArgumentException("Key should be non-negative");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value != null)
            this.value = value;
        else
            throw new IllegalArgumentException("Value is undefined");
    }

    public Pair() {
        setKey(0);
        setValue("");
    }

    public Pair(int key, String value) {
        setKey(key);
        setValue(value);
    }
}
