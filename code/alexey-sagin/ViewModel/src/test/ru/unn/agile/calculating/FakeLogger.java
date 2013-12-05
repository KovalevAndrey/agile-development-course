package ru.unn.agile.calculating;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {

    List<String> strings = new ArrayList<String>();

    @Override
    public void putMessage(String mes) {
        strings.add(mes);
    }

    @Override
    public String getLastMessage() {
        String res = strings.size() == 0 ? "" : strings.get(strings.size() - 1);
        return res;
    }

    @Override
    public List<String> getAllMessages() {
        return strings;
    }
}
