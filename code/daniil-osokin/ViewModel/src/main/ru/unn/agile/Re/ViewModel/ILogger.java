package ru.unn.agile.Re.viewmodel;

import java.util.List;

public interface ILogger
{
    static final String INFO  = "INFO";
    static final String WARN  = "WARNING";
    static final String ERROR = "ERROR";
    static final int TYPE_ID = 0;
    static final int TAG_ID  = 1;
    static final int TEXT_ID = 2;
    static final int DATE_ID = 3;


    void i(String tag, String text);
    void w(String tag, String text);
    void e(String tag, String text);
    List<String[]> getLog();
}
