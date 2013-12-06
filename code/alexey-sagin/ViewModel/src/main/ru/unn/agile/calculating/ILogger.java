package ru.unn.agile.calculating;

import java.util.List;

public interface ILogger {
    public void putMessage(String mes);

    public String getLastMessage();

    List<String> getAllMessages();
}
