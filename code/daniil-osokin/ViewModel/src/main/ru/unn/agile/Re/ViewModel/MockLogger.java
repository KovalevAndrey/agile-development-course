package ru.unn.agile.Re.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockLogger implements ILogger
{
    private List<String[]> logList = new ArrayList<String[]>();

    @Override
    public void i(String tag, String text)
    {
        logList.add(getLogEntry(ILogger.INFO, (new Date()).toString(), tag, text));
    }

    @Override
    public void w(String tag, String text)
    {
        logList.add(getLogEntry(ILogger.WARN, (new Date()).toString(), tag, text));
    }

    @Override
    public void e(String tag, String text)
    {
        logList.add(getLogEntry(ILogger.ERROR, (new Date()).toString(), tag, text));
    }

    @Override
    public List<String[]> getLog()
    {
        return logList;
    }

    private String[] getLogEntry(String type, String date, String tag, String text)
    {
        String[] logEntry = new String[4];
        logEntry[ILogger.TYPE_ID] = type;
        logEntry[ILogger.DATE_ID] = date;
        logEntry[ILogger.TAG_ID]  = tag;
        logEntry[ILogger.TEXT_ID] = text;

        return logEntry;
    }
}
