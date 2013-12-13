package ru.unn.agile.Huffman;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger{
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void logInfo(String message) {
        log.add("Info: "+message);
    }

    @Override
    public void logError(String message) {
        log.add("Error: "+message);
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void clearLog(){
        log.clear();
    }
}
