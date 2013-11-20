package ru.unn.agile.Re.viewmodel;

import java.util.List;

public interface ILogger
{
    static final int INFO  = 0;
    static final int WARN  = 1;
    static final int ERROR = 2;

    void i(String tag, String text);
    void w(String tag, String text);
    void e(String tag, String text);
    List<LogEntry> getLog();
}
