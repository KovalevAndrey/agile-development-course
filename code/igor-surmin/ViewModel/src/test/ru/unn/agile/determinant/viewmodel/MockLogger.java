package ru.unn.agile.determinant.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MockLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void logMessage(String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void clearLog() {
        log.clear();
    }
}
