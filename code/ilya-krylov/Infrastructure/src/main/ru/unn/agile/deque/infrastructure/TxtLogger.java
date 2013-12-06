package ru.unn.agile.deque.infrastructure;

import ru.unn.agile.deque.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger{
    private BufferedWriter writer;
    private String filename;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TxtLogger(String filename){
        try{
            writer = new BufferedWriter(new FileWriter(filename));
            this.filename = filename;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void log(String message){
        try{
            writer.write(getDateAndTime() + ": " + message + '\n');
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getLog(){
        ArrayList<String> log = new ArrayList<String>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line = bufferedReader.readLine();
            while (line != null){
                log.add(line);
                line = bufferedReader.readLine();
            }
            return log;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return log;
    }

    private String getDateAndTime(){
            Calendar cal = Calendar.getInstance();
            return new SimpleDateFormat(DATE_FORMAT).format(cal.getTime());
    }
}
