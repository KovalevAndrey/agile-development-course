package ru.unn.agile.MergeSort;

import java.util.*;

public class FakeLogger implements ILogger {
    List<String> log;

    FakeLogger() {
        log = new ArrayList<String>();
    }

    @Override
    public void log(String ms) {
        log.add(ms);
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void clear() {
        log.clear();
    }
}
