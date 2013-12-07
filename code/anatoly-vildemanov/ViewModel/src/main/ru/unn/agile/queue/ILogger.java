package ru.unn.agile.queue;

import java.util.List;

public interface ILogger {
    void writeLog(String s);

    List<String> getLog();
}

