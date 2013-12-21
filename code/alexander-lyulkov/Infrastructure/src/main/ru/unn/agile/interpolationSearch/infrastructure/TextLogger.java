package ru.unn.agile.interpolationSearch.infrastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import ru.unn.agile.interpolationSearch.viewmodel.ILogger;


public class TextLogger implements ILogger {
    private BufferedWriter bufferedWriter;
    private String filename;


    public TextLogger(String filename) {
        this.filename = filename;

        try {
            FileWriter fileWriter = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void AddNote(String note) {
        try {
            bufferedWriter.write(CurrentTime() + ": " + note + "\n");
            bufferedWriter.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String[] ReadLog() {
        int numberOfNotes = 0;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while (line != null) {
                numberOfNotes++;
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] notes = new String[numberOfNotes];
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            for (int i = 0; i < numberOfNotes; i++) {
                notes[i] = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return notes;
    }

    private static String CurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}