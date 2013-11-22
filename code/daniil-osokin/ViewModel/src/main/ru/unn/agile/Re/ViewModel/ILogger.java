package ru.unn.agile.Re.viewmodel;

import java.util.List;

public interface ILogger
{
    static final String INFO  = "INFO";
    static final String WARN  = "WARNING";
    static final String ERROR = "ERROR";

    void i(String tag, String text);
    void w(String tag, String text);
    void e(String tag, String text);
    List<LogEntry> getLog();
}
