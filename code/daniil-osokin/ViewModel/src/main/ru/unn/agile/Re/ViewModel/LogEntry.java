package ru.unn.agile.Re.viewmodel;

import java.util.Date;

public class LogEntry
{
    private final String type;
    private final Date date;
    private final String tag;
    private final String text;

    public LogEntry(String type, Date date, String tag, String text)
    {
        this.type = type;
        this.date = date;
        this.tag = tag;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
