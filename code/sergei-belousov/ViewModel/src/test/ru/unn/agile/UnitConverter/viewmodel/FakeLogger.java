package ru.unn.agile.UnitConverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void Log(String tag, String msg) {
        log.add(tag + ": " + msg);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
