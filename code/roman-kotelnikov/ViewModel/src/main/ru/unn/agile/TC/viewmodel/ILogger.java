package ru.unn.agile.TC.viewmodel;

import java.io.IOException;
import java.util.List;

public interface ILogger {
    void putMessage(LogMessage message);

    List<String> getLog() throws IOException;
    String getLastMessage();
}

