package ru.unn.agile.converter.viewmodel;


import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger{
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void Add(String inputLog, LogStatus statusLog) {
        log.add(inputLog + " Status: " + statusLog.toString());
    }

    /*@Override
    public List<String> getLog() {
        return log;
    }  */

    @Override
    public List<String> getLog(LogStatus logStatus) {
        return log;
    }
}
