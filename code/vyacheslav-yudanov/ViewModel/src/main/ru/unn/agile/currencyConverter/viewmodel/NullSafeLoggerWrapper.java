package ru.unn.agile.currencyConverter.viewmodel;

import java.util.ArrayList;

public class NullSafeLoggerWrapper implements ILogger {
    ILogger logger;

    public NullSafeLoggerWrapper(ILogger logger)
    {
        this.logger = logger;
    }

    @Override
    public void logMessage(String message) {
        if(logger != null)
            logger.logMessage(message);
    }

    @Override
    public void logError(String message) {
        if(logger != null)
            logger.logError(message);
    }

    @Override
    public void setLogLevel(LoggingLevel level) {
        if(logger != null)
            logger.setLogLevel(level);
    }

    @Override
    public LoggingLevel getLogLevel() {
        if(logger != null)
            return logger.getLogLevel();
        return null;
    }

    @Override
    public ArrayList<String> getLogFull() {
        if(logger != null)
            return logger.getLogFull();
        return null;
    }

    @Override
    public String getLastLogMessage() {
        if(logger != null)
            return logger.getLastLogMessage();
        return null;
    }
}
