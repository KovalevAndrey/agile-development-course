package ru.unn.agile.Converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TxtLogger implements ILogger
{
    private String filename = "";
    private BufferedWriter writer = null;

    public TxtLogger(String filename)
    {
        this.filename = filename;

        try
        {
            writer = new BufferedWriter(new FileWriter(this.filename));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /*
    protected void finalize()
    {
        try
        {
            writer.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    */

    @Override
    public void log(String message)
    {
        try
        {
            writer.write(message);
            writer.newLine();
            writer.flush();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog()
    {
        BufferedReader bufferedReaderreader;
        ArrayList<String> logList = new ArrayList<String>();

        try
        {
            bufferedReaderreader = new BufferedReader(new FileReader(filename));
            String line = bufferedReaderreader.readLine();

            while (line != null)
            {
                logList.add(line);
                line = bufferedReaderreader.readLine();
            }

            bufferedReaderreader.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return logList;
    }
}