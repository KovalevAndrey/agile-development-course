package ru.unn.agile.fraction.infrastructure;

import ru.unn.agile.fraction.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TextLogger implements ILogger {

    private BufferedWriter writer = null;
    private String filename = "";
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public TextLogger(String filename) {
        this.filename = filename;

        try {
            FileWriter logFile = new FileWriter(filename);
            writer = new BufferedWriter(logFile);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void log(String message) {
        try {
            writer.write(getDate() + "  " + message);
            writer.newLine();
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> logList = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                logList.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logList;
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_NOW);

        return format.format(calendar.getTime());
    }
}
