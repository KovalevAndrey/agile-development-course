package ru.unn.agile.interpolationSearch.viewmodel;

import java.util.ArrayList;
import java.util.List;


public class FakeLogger implements ILogger {
    private ArrayList<String> notes = new ArrayList<String>();

    @Override
    public void AddNote(String s) {
        notes.add(s);
    }

    @Override
    public String[] ReadLog() {
        return notes.toArray(new String[notes.size()]);
    }
}
