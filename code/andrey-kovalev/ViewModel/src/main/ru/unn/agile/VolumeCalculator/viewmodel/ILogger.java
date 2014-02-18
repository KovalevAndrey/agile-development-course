package ru.unn.agile.VolumeCalculator.viewmodel;

import java.util.List;

public interface ILogger {
    void logError(String message);
    void logInfo(String message);
    List<String> getAllMessage();
    String getLastMessage();
    void clearLog();
}
