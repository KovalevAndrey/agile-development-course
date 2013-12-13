package ru.unn.agile.Converter;

import java.util.List;

public interface ILogger
{
    void log(String message);
    List<String> getLog();
}
