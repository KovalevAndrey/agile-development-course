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
       fakeLog.add(LogTitle.INFO_STRING + s);
    }

    @Override
    public void addWarning(String s) {
        fakeLog.add(LogTitle.WARNING_STRING + s);
     }

    @Override
    public void addError(String s) {
        fakeLog.add(LogTitle.ERROR_STRING + s);
    }

    public class LogTitle {
        public static final String ERROR_STRING = "ERROR:";
        public static final String WARNING_STRING = "WARNING:";
        public static final String INFO_STRING = "Info:";
    }
}
