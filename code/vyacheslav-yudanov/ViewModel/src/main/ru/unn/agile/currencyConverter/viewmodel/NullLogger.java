package ru.unn.agile.currencyConverter.viewmodel;

import java.util.ArrayList;

public class NullLogger implements ILogger {

    public NullLogger()
    {
    }

    @Override
    public void logMessage(String message) {
    }

    @Override
    public void logError(String message) {
    }

    @Override
    public void setLogLevel(LoggingLevel level) {
    }

    @Override
    public LoggingLevel getLogLevel() {
        return null;
    }

    @Override
    public ArrayList<String> getLogFull() {
        return null;
    }

    @Override
    public String getLastLogMessage() {
        return null;
    }
}
