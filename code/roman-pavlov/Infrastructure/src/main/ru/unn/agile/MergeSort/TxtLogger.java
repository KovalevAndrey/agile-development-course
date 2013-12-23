package ru.unn.agile.MergeSort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtLogger implements ILogger{
    private List<String> log;
    private String file= "";

    private BufferedWriter fileWriter;

    TxtLogger(String file) {
        this.file=file;
        log = new ArrayList<String>();
        try {
            fileWriter = new BufferedWriter(new FileWriter(file));
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void log(String ms) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.add(date + " " + ms);
        try {
            fileWriter.write(date + " " + ms);
            fileWriter.newLine();
            fileWriter.flush();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public void clear() {
        log.clear();
        try {
            FileWriter logFile = new FileWriter(file);
            fileWriter = new BufferedWriter(logFile);
        }
        catch (Exception e) {
        e.printStackTrace();
        }
    }

}
