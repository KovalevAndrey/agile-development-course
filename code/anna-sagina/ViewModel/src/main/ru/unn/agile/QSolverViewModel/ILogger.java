package ru.unn.agile.QSolverViewModel;

import java.util.List;

public interface ILogger {
    List<String> getAllLog();
    void addInfo(String s);
    void addWarning(String s);
    void addError(String s);
}
