package ru.unn.agile.Re.infrastructure;

import ru.unn.agile.Re.viewmodel.ILogger;
import ru.unn.agile.Re.viewmodel.LogEntry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtLogger implements ILogger
{
    private BufferedWriter writer;
    private String filename;
    private List<LogEntry> logList;

    public TxtLogger(String filename)
    {
        this.filename = filename;
        logList = new ArrayList<LogEntry>();

        try
        {
            writer = new BufferedWriter(new FileWriter(filename));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void i(String tag, String text)
    {
        logList.add(new LogEntry(ILogger.INFO, new Date(), tag, text));
    }

    @Override
    public void w(String tag, String text)
    {
        logList.add(new LogEntry(ILogger.WARN, new Date(), tag, text));
    }

    @Override
    public void e(String tag, String text)
    {
        logList.add(new LogEntry(ILogger.ERROR, new Date(), tag, text));
    }

    @Override
    public List<LogEntry> getLog()
    {
        return logList;
    }
}
