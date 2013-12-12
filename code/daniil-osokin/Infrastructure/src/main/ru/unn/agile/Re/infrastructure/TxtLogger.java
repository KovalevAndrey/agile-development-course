package ru.unn.agile.Re.infrastructure;

import ru.unn.agile.Re.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtLogger implements ILogger
{
    private BufferedWriter writer;
    private String filename;
    private String delimiter;
    private static final String DATE_FORMAT_NOW = "HH:mm:ss yyyy-MM-dd";
    SimpleDateFormat sdf;

    public TxtLogger(String filename)
    {
        this.filename = filename;
        delimiter = "\t";
        sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

        try
        {
            writer = new BufferedWriter(new FileWriter(filename));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void i(String tag, String text)
    {
        writeLogMessage(ILogger.INFO, tag, text, new Date());
    }

    @Override
    public void w(String tag, String text)
    {
        writeLogMessage(ILogger.WARN, tag, text, new Date());
    }

    @Override
    public void e(String tag, String text)
    {
        writeLogMessage(ILogger.ERROR, tag, text, new Date());
    }

    @Override
    public List<String[]> getLog()
    {
        List<String[]> log = new ArrayList<String[]>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null)
            {
                log.add(line.split(delimiter));
                line = reader.readLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return log;
    }

    private String getFormattedLogMessage(String type, String tag, String text, Date date)
    {
        return String.format("%s%s%s%s%s%s%s", type, delimiter,
                tag, delimiter,
                text, delimiter,
                sdf.format(date));
    }

    private void writeLogMessage(String type, String tag, String text, Date date)
    {
        try {
            writer.write(getFormattedLogMessage(type, tag, text, date));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
