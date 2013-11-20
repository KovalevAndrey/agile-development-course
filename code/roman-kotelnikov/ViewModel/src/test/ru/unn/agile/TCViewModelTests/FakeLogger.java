package ru.unn.agile.TCViewModelTests;

import ru.unn.agile.TC.viewmodel.ILogger;

import java.util.ArrayList;

public class FakeLogger implements ILogger {
    ArrayList<String> log = new ArrayList<String>();

    @Override
    public void puts(LoggerConstant message) {
        log.add(message.toString());
    }

    @Override
    public void putsString(String message) {
        log.add(message);
    }

    @Override
    public ArrayList<String> getLog() {
        return log;
    }
    
    @Override
    public String getLastMessage() {
        return log.get(log.size() - 1);
    }
}
