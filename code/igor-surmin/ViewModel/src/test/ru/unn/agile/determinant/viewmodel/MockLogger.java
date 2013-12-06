package ru.unn.agile.determinant.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MockLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void LogMessage(String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
