package ru.unn.agile.QSolverViewModel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private ArrayList<String> fakeLog = new ArrayList<String>();

    @Override
    public List<String> getAllLog() {
        return fakeLog;
    }

    @Override
    public void addInfo(String s) {
       fakeLog.add(s);

    }

    @Override
    public void addWarning(String s) {
        fakeLog.add(s);
     }

    @Override
    public void addError(String s) {
        fakeLog.add(s);
    }
}
