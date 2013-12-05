package ru.unn.agile.Huffman.Infrastructure;

import ru.unn.agile.Huffman.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class HuffmanTxtLogger implements ILogger
{
    private String filename = "";
    private BufferedWriter writer = null;
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public HuffmanTxtLogger(String filename) {
        this.filename = filename;
        try {
            writer = new BufferedWriter(new FileWriter(this.filename));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    @Override
    public void log(String message) {
        try {
            writer.write(now()+" "+message);
            writer.newLine();
            writer.flush();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> logList = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                logList.add(line);
                line = reader.readLine();
            }

            reader.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return logList;
    }

    protected void finalize() {
        try {
            writer.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}