package ru.unn.agile.stack;

import ru.unn.agile.stack.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtStackLogger implements ILogger {

    private BufferedWriter writer = null;
    private String outputFile = "";
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public TxtStackLogger(String fileName)
    {
        this.outputFile = fileName;

        try
        {
            FileWriter fileWriter = new FileWriter(outputFile);
            writer = new BufferedWriter(fileWriter);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Log(String s)
    {
        LogString(getDate() + " " + " " + s);
    }

    public void LogString(String s)
    {
        try
        {
            writer.write(s+"\n");
            writer.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {

        ArrayList<String> logCollection=new ArrayList<String>();
        try
        {
            FileReader fileReader = new FileReader(outputFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null)
            {
                logCollection.add(line);
                line=reader.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return logCollection;
    }

    private String getDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

}
