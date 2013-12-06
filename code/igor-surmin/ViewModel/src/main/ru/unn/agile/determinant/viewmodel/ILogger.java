package ru.unn.agile.determinant.viewmodel;

import java.util.List;

public interface ILogger {
    void LogMessage(String s);
    List<String> getLog();
}
