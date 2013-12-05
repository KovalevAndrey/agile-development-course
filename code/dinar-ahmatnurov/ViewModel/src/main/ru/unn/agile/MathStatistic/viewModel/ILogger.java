package ru.unn.agile.MathStatistic.viewModel;

import java.util.ArrayList;

public interface ILogger {
    enum MessageType {
        INFO,
        WARNING,
        ERROR,
        UNKNOWN
    }

    void saveToLog(String message, MessageType type);

    ArrayList<String> getEntireLog();
    ArrayList<String> getParticularType(MessageType type);
}
