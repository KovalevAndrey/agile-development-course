package ru.unn.agile.Polynomial.viewmodel;

import java.util.List;

public interface ILogger {

    void log(LoggingLevel level, String message);

    List<String> getLog();

    String getLastMessage();

    void setLevel(LoggingLevel level);

    LoggingLevel getLevel();
}
