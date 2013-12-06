package ru.unn.agile.colorConverter.viewmodel;

import java.util.List;

public interface ILogger {
    public void log(String message);
    public List<String> getContent();
}
