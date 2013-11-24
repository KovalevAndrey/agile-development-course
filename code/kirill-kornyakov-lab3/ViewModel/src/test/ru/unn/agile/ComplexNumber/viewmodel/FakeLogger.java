package ru.unn.agile.ComplexNumber.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void Log(String s) {
        log.add(s);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
