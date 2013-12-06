package ru.unn.agile.interpolationSearch.viewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sasha
 * Date: 12/5/13
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleLogger implements ILogger {
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
