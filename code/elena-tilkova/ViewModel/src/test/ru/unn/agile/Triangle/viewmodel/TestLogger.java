package ru.unn.agile.Triangle.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class TestLogger implements ILogger
{
    private ArrayList<String> log = new ArrayList<String>();
    private static String data = "yyyy-MM-dd HH:mm:ss";
    @Override
    public void Log(String s)
    {
        log.add(data + " " + s);
    }

    @Override
    public List<String> getLog()
    {
        return log;
    }

    @Override
    public int dateSize()
    {
        return data.length() + 1;
    }
}
