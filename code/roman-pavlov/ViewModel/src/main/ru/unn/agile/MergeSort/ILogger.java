package ru.unn.agile.MergeSort;


import java.util.List;

public interface ILogger {
    void log(String ms);
    List<String> getLog();
    void clear();
}

