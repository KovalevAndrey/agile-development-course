package ru.unn.agile.TCViewModelTests;

import ru.unn.agile.TC.viewmodel.ILogger;

import java.util.ArrayList;

public class FakeLogger implements ILogger {
    ArrayList<String> log = new ArrayList<String>();

    @Override
    public void putMessage(String message) {
        log.add(message);
    }

    @Override
    public void putError(Errors message) {
        log.add(message.toString());
    }

    @Override
    public ArrayList<String> getLog() {
        return log;
    }
    
    @Override
    public String getLastMessage() {
        int size = log.size();

        return size > 0 ? log.get(size - 1) : "";
    }
}
