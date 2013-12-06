package ru.unn.agile.BitArray.Insfrastructure;

import ru.unn.agile.BitArray.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TextLogger implements ILogger{
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String STRUCT_DELIMITER = "::";


    private BufferedWriter writer;
    private int sequence;
    private String logFileName;


    public TextLogger(String logOutFileName) {
        try {
            logFileName = logOutFileName;
            sequence = 0;
            FileWriter file = new FileWriter(logOutFileName);
            writer = new BufferedWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(calendar.getTime());
    }

    private String generateLogEntry(String msg) {
        sequence++;
        StringBuffer buf = new StringBuffer();
        buf.append(sequence);
        buf.append(STRUCT_DELIMITER);
        buf.append(now());
        buf.append(STRUCT_DELIMITER);
        buf.append(msg);
        return buf.toString();
    }

    @Override
    public void log(String log) {
        try {
            writer.write(generateLogEntry(log));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLogs() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(logFileName));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
