package ru.unn.agile.geometry.viewModel;

import java.util.List;

public interface ILogger {
    void message(String msg);
    void error(String msg);
    void debug(String msg);

    public static final String MESSAGE_PREFIX = "MESSAGE";
    public static final String ERROR_PREFIX = "ERROR";
    public static final String DEBUG_PREFIX = "DEBUG";

    List<String> getLog();
}
