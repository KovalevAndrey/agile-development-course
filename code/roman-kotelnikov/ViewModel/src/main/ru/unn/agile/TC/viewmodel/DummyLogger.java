package ru.unn.agile.TC.viewmodel;

import java.io.IOException;
import java.util.List;

public class DummyLogger implements ILogger {
    @Override
    public void putMessage(LogMessage message) {

    }

    @Override
    public List<String> getLog() throws IOException {
        throw new UnsupportedOperationException("DummyLogger cant return log");
    }

    @Override
    public String getLastMessage() {
        throw new UnsupportedOperationException("Dummy logger cant get log last message");
    }
}
