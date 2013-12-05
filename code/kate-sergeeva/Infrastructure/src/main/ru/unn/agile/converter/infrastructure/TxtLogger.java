package ru.unn.agile.converter.infrastructure;

import ru.unn.agile.converter.viewmodel.ILogger;
import ru.unn.agile.converter.viewmodel.LogMessage;
import ru.unn.agile.converter.viewmodel.LogStatus;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class TxtLogger implements ILogger{
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    private String file = "";
    private BufferedWriter writer = null;
    private String separator = "/";

    public TxtLogger(String file){
        this.file = file;
        try {
            FileWriter logFile = new FileWriter(file);
            writer = new BufferedWriter(logFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Add(String textLog, LogStatus statusLog) {
        try {
            LogMessage logMessage = new LogMessage(time(), textLog, statusLog);
            writer.write(logMessage.toStringForFile(separator));
            writer.newLine();
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*@Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                LogMessage logMessage = new LogMessage(line, separator);
                log.add(logMessage.toString());
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }*/

    @Override
    public List<String> getLog(LogStatus logStatus) {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                LogMessage logMessage = new LogMessage(line, separator);
                if ((logMessage.getStatusLog() == logStatus)||(logStatus == LogStatus.all))
                    log.add(logMessage.toString());
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    public static String time(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(calendar.getTime());
    }
}
