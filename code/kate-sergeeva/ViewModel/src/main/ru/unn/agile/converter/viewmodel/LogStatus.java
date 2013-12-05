package ru.unn.agile.converter.viewmodel;


public enum LogStatus {
    all("all"),
    success("success"),
    error("error");

    LogStatus(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public String text;
}
