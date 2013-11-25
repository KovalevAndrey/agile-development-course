package ru.unn.agile.leftistHeap.viewModel;

import java.util.List;

public interface ILogger {
    void log(String message);
    List<String> getLog();
}
