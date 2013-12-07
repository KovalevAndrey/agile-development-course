package ru.unn.agile.deque.viewmodel;

import java.util.List;

public interface ILogger {
    void log(String message);
    List<String> getLog();
}

