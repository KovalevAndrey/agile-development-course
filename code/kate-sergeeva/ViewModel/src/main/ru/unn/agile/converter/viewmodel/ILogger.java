package ru.unn.agile.converter.viewmodel;

import java.util.List;

public interface ILogger {
    void Add(String inputLog, LogStatus statusLog);
    List<String> getLog(LogStatus logStatus);
}
