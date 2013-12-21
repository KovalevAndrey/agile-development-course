package ru.unn.agile.CreditCalculator.infrastructure;

import ru.unn.agile.CreditCalculator.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RealLogger implements ILogger {
    private BufferedWriter writer;
    private String logsFilePath;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(calendar.getTime());
    }

    public RealLogger(String logsFilePath) {
        this.logsFilePath = logsFilePath;

        try {
            FileWriter logsFileWriter = new FileWriter(logsFilePath);
            writer = new BufferedWriter(logsFileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Log(String s) {
        try {
            writer.write("(" + getCurrentTime() + "): " + s);
            writer.newLine();
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            FileReader logsFileReader = new FileReader(logsFilePath);
            BufferedReader bufferedReader = new BufferedReader(logsFileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                result.add(line);
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
