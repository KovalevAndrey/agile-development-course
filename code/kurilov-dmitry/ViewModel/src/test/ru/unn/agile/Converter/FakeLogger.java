package ru.unn.agile.Converter;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger
{
    private ArrayList<String> fakeLog = new ArrayList<String>();

    @Override
    public void log(String message)
    {
        fakeLog.add(message);
    }

    @Override
    public List<String> getLog()
    {
        return  fakeLog;
    }
}
