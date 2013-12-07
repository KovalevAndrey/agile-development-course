package ru.unn.agile.deque.viewmodel;

import java.util.ArrayList;

public class FakeLogger implements ILogger{
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void log(String message){
        log.add(message);
    }

    @Override
    public ArrayList<String> getLog(){
        return log;
    }
}
