package ru.unn.agile.converter.viewmodel;


public enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Convert'"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success convert");

    Status(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public String text;
}
