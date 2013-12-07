package ru.unn.agile.colorConverter.viewmodel;

import java.util.*;

class FakeLogger implements ILogger {
    private ArrayList<String> content = new ArrayList<String>();

    @Override
    public void log(String s) {
        content.add(s);
    }

    @Override
    public List<String> getContent() {
        return content;
    }
}
