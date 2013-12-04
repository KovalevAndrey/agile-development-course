package ru.unn.agile.UnitConverter.infrastructure;

import ru.unn.agile.UnitConverter.viewmodel.ILogger;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TxtFileLogger implements ILogger {
    private BufferedWriter writer = null;
    private String filename = "";
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat sdf;

    public TxtFileLogger(String filename) {
        sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        this.filename = filename;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Log(String tag, String msg) {
        try {
            writeLogMessage(tag, msg, new Date());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
            return null;
        }

        return log;
    }

    private String getFormattedLogString(String tag, String msg, Date date) {
        return String.format("%s%s%s%s%s%s", "[", sdf.format(date), "]", tag, ": ", msg);
    }

    private void writeLogMessage(String tag, String msg, Date date) {
        try {
            writer.write(getFormattedLogString(tag, msg, date));
            writer.newLine();
            writer.flush();
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
