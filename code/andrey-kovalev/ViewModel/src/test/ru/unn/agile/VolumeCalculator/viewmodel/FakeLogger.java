package ru.unn.agile.VolumeCalculator.viewmodel;

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
    public List<String> getAllMessages() {
        return log;
    }

    @Override
    public String getLastMessage() {
        String lastMassage="";
        if (log.size()>0) lastMassage=log.get(log.size()-1);
        return  lastMassage;
    }

    @Override
    public void clearLog(){
        log.clear();
    }
}