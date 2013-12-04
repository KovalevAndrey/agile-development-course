package ru.unn.agile.UnitConverter.viewmodel;

import java.util.List;

public interface ILogger {
    void Log(String tag, String msg);
    List<String> getLog();
}
