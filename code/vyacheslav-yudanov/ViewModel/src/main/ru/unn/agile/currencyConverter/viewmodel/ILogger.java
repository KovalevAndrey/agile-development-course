package ru.unn.agile.currencyConverter.viewmodel;

import java.util.ArrayList;

public interface ILogger {
    public void logMessage(String message);
    public void logError(String message);

    public void setLogLevel(LoggingLevel level);
    public LoggingLevel getLogLevel();

    public ArrayList<String> getLogFull();
    public String getLastLogMessage();
}

