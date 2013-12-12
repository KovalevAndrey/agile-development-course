package ru.unn.agile.currencyConverter.infrastructure;

import ru.unn.agile.currencyConverter.viewmodel.ILogger;
import ru.unn.agile.currencyConverter.viewmodel.LoggingLevel;

import java.io.*;
import java.util.ArrayList;

public class TxtFileLogger implements ILogger {
    private String filename;
    private String lastLoggedMessage;
    private BufferedWriter logFileWriter = null;
    private LoggingLevel loggingLevel;

    public TxtFileLogger(String filename)
    {
        this.filename = filename;
        this.loggingLevel = LoggingLevel.Debug;
        lastLoggedMessage = null;

        try {
            logFileWriter = new BufferedWriter(new FileWriter(this.filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void logMessage(String message) {
        if(loggingLevel.ordinal() <= LoggingLevel.Debug.ordinal())
        {
            try {
                lastLoggedMessage = message;
                logFileWriter.write(lastLoggedMessage);
                logFileWriter.flush();
            } catch (IOException e) {
                lastLoggedMessage = null;
                e.printStackTrace();
            }
        }
    }

    @Override
    public void logError(String message) {
        try {
            lastLoggedMessage = "ERROR: " + message;
            logFileWriter.write(lastLoggedMessage);
            logFileWriter.flush();
        } catch (IOException e) {
            lastLoggedMessage = null;
            e.printStackTrace();
        }
    }

    @Override
    public void setLogLevel(LoggingLevel level) {
        this.loggingLevel = level;
    }

    @Override
    public LoggingLevel getLogLevel() {
        return loggingLevel;
    }

    @Override
    public ArrayList<String> getLogFull() {
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
            return null;
        }

        return log;
    }

    @Override
    public String getLastLogMessage() {
        return lastLoggedMessage;
    }
}
