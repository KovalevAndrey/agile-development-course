package ru.unn.agile.CreditCalculator.viewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestLogger implements ILogger
{
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private ArrayList<String> log = new ArrayList<String>();

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public void Log(String s)
    {
        log.add("(" + getCurrentTime() + "): " + s);
    }

    @Override
    public List<String> getLog()
    {
        return log;
    }
}
