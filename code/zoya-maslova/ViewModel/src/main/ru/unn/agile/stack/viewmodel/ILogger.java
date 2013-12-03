package ru.unn.agile.stack.viewmodel;

import java.io.FileNotFoundException;
import java.util.List;

public interface ILogger {
    void Log(String s);

    List<String> getLog();
}
