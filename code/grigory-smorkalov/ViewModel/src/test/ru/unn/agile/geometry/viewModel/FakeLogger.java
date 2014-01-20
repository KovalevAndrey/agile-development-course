package ru.unn.agile.geometry.viewModel;

import ru.unn.agile.geometry.viewModel.ILogger;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void message(String msg) {
        log.add(createFormatLogString(msg, ILogger.MESSAGE_PREFIX));
    }

    @Override
    public void debug(String msg) {
        log.add(createFormatLogString(msg, ILogger.DEBUG_PREFIX));
    }

    @Override
    public List<String> getLog() {
        return  log;
    }

    private String createFormatLogString(String msg, String prefix) {
        return prefix + ": " + msg;
    }

}
