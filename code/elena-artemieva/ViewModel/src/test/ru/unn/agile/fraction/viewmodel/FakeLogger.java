package ru.unn.agile.fraction.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> fakelog = new ArrayList<String>();

    @Override
    public void log(String s) {
        fakelog.add(s);
    }

    @Override
    public List<String> getLog() {
        return fakelog;
    }
}
