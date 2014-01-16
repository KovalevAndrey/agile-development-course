package ru.unn.agile.Polynomial.viewmodel;


import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private LoggingLevel level;
    private ArrayList<String> log;

    public FakeLogger()
    {
        level = LoggingLevel.RELEASE;
        log = new ArrayList<String>(0);
    }

    @Override
    public void log(LoggingLevel level, String message) {
        if (this.level == LoggingLevel.DEBUG)  {
            log.add(message);
            return;
        }
        if (this.level == level)
            log.add(message);
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public String getLastMessage() {
        return log.get(log.size() - 1);
    }

    @Override
    public void setLevel(LoggingLevel level) {
        this.level = level;
    }

    @Override
    public LoggingLevel getLevel() {
        return level;
    }
}
