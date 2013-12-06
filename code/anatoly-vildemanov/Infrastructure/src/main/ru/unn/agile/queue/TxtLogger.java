package ru.unn.agile.queue;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TxtLogger implements ILogger {
    private BufferedWriter bufferedWriter = null;
    private String filename;
    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";

    public TxtLogger(String filename) {
        if(filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        if(filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }

        this.filename = filename;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeLog(String message) {
        writeBuffer(time() + "> " + message);
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<String>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }

    public void writeBuffer(String message){
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private String time()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        return sdf.format(cal.getTime());
    }
}
