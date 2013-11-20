package ru.unn.agile.currencyConverter.viewmodel;

import java.util.ArrayList;

public class FakeLogger implements ILogger {
    private LoggingLevel loggingLevel;
    private ArrayList<String> log;

    public FakeLogger()
    {
        loggingLevel = LoggingLevel.Debug;
        log = new ArrayList<String>();
    }

    @Override
    public void logMessage(String message) {
        if(loggingLevel.ordinal() <= LoggingLevel.Debug.ordinal())
        {
            log.add(message);
        }
    }

    @Override
    public void logError(String message) {
        log.add("ERROR: " + message);
    }

    @Override
    public void setLogLevel(LoggingLevel level) {
        loggingLevel = level;
    }

    @Override
    public LoggingLevel getLogLevel() {
        return loggingLevel;
    }

    @Override
    public ArrayList<String> getLogFull() {
        return log;
    }

    @Override
    public String getLastLogMessage() {
        int logSize = log.size();
        return logSize == 0 ? null : log.get(logSize - 1);
    }
}
