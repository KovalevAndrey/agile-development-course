package ru.unn.agile.tree.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String log);
    List<String> readLog();
}
