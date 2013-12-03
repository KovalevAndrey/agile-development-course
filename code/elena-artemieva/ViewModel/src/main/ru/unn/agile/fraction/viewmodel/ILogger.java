package ru.unn.agile.fraction.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String message);

    List<String> getLog();
}
