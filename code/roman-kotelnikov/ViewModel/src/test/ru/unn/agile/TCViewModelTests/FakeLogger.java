package ru.unn.agile.TCViewModelTests;

import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.LogFormat;
import ru.unn.agile.TC.viewmodel.LogMessage;

import java.util.ArrayList;

public class FakeLogger implements ILogger {
    ArrayList<String> log = new ArrayList<String>();

    @Override
    public void putMessage(LogMessage message) {
        if(message == null)
            throw new IllegalArgumentException("LogMessage cannot be null");

        log.add(LogFormat.formatEntry(message));
    }

    @Override
    public ArrayList<String> getLog() {
        return log;
    }
    
    @Override
    public String getLastMessage() {
        int size = log.size();
        if(size > 0)
            return log.get(size - 1);

        throw new UnsupportedOperationException("Cannot get last message of empty log");
    }
}
