package ru.unn.agile.qsolver.infrastructure;

import ru.unn.agile.QSolverViewModel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileLogger implements ILogger {

    private BufferedWriter logWriter = null;
    private String fileName = "";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public FileLogger(String filename){
        this.fileName = filename;

        try {
            FileWriter logFile = new FileWriter(filename);
            logWriter = new BufferedWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllLog() {
        List<String> allLog = new ArrayList<String>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String str = bfr.readLine();
            while (null != str) {
                allLog.add(str);
                str = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLog;
    }

    @Override
    public void addInfo(String s) {
        try {
            logWriter.write(getTimeNow()  + LogType.INFO + s);
            logWriter.newLine();
            logWriter.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addWarning(String s) {
        try {
            logWriter.write(getTimeNow()  + LogType.WARNING+ s);
            logWriter.newLine();
            logWriter.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addError(String s) {
        try {
            logWriter.write(getTimeNow()  + LogType.ERROR + s);
            logWriter.newLine();
            logWriter.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getTimeNow(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(cal.getTime());
    }

    public class LogType {
        public static final String INFO = "[INFO]: ";
        public static final String WARNING = "[WARNING]: ";
        public static final String ERROR = "[ERROR]: ";
    }

}
