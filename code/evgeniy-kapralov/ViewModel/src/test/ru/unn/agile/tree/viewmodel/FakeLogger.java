package ru.unn.agile.tree.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> logContent = new ArrayList<String>();

    @Override
    public void log(String log)
    {
        logContent.add(log);
    }

    @Override
    public void logWithDate(String log)
    {
        logContent.add(log);
    }

    @Override
    public List<String> readLog()
    {
        return logContent;
    }
}
