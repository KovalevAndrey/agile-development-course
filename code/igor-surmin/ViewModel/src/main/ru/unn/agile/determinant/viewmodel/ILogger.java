package ru.unn.agile.determinant.viewmodel;

import java.util.List;

public interface ILogger {
    void logMessage(String s);
    List<String> getLog();
    void clearLog();
}
