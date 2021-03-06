package ru.unn.agile.tree.infrastructure;

import ru.unn.agile.tree.viewmodel.IDateLogger;
import ru.unn.agile.tree.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileLogger implements IDateLogger {
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private String fileName = "";
    private BufferedWriter bufWriter = null;

    public FileLogger(String filename) {
        if(filename == null || filename.length() == 0) {
            throw new IllegalArgumentException("File name cannot be empty");
        }
        this.fileName = filename;

        try {
            FileWriter logFile = new FileWriter(filename);
            bufWriter = new BufferedWriter(logFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void log(String s) {
        writeLog(s, false);
    }

    @Override
    public void logWithDate(String s) {
        writeLog(s, true);
    }

    @Override
    public List<String> readLog() {
        BufferedReader bufReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufReader = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                log.add(line);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    private void writeLog(String s, Boolean needDate) {
        try {
            String text = null;
            if (needDate) {
                text = now() + " " + s;
            } else {
                text = s;
            }

            bufWriter.write(text);
            bufWriter.newLine();
            bufWriter.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(Calendar.getInstance().getTime());
    }
}
