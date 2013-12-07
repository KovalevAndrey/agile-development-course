package ru.unn.agile.Triangle.infrastucture;

import ru.unn.agile.Triangle.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger
{
    private String fileLog = "";
    private BufferedWriter writer = null;
    private static final String DATE = "yyyy-MM-dd HH:mm:ss";

    private static String date()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE);
        return sdf.format(cal.getTime());
    }

    public TxtLogger (String fileName)
    {
        fileLog = fileName;
        try
        {
            FileWriter logFile = new FileWriter(fileLog);
            writer = new BufferedWriter(logFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void Log(String s) {
        try
        {
            writer.write(date() + " " + s);
            writer.newLine();
            writer.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try
        {
            reader = new BufferedReader(new FileReader(fileLog));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return log;
    }
}

