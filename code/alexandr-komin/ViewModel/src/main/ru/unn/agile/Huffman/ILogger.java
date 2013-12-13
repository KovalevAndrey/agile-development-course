package ru.unn.agile.Huffman;

import java.util.List;

public interface ILogger {
    void logInfo(String message);
    void logError(String message);
    List<String> getLog();
    void clearLog();
}
