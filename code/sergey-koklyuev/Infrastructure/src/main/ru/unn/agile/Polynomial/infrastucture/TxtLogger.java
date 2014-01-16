package ru.unn.agile.Polynomial.infrastucture;

import ru.unn.agile.Polynomial.viewmodel.ILogger;
import ru.unn.agile.Polynomial.viewmodel.LoggingLevel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger {
    private BufferedWriter writer = null;
    private String filename = "";
    private LoggingLevel level = LoggingLevel.DEBUG;
    private String lastMessage = "";

    public TxtLogger(String filename) {
        this.filename = filename;

        try {
            FileWriter logFile = new FileWriter(filename);
            writer = new BufferedWriter(logFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(LoggingLevel level, String message) {
        if (this.level == LoggingLevel.DEBUG)  {
            write(message);
            return;
        }
        if (this.level == level)
            write(message);
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    @Override
    public String getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLevel(LoggingLevel level) {
        this.level = level;
    }

    @Override
    public LoggingLevel getLevel() {
        return level;
    }

    private void write(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        lastMessage = message;
    }
}
