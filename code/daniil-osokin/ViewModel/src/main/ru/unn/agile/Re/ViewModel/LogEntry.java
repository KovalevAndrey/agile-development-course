package ru.unn.agile.Re.viewmodel;

import java.util.Date;

public class LogEntry
{
    private final int priority;
    private final Date date;
    private final String tag;
    private final String text;

    public LogEntry(int priority, Date date, String tag, String text)
    {
        this.priority = priority;
        this.date = date;
        this.tag = tag;
        this.text = text;
    }
}
