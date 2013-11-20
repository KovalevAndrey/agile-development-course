package ru.unn.agile.TC.infrastructure;

import ru.unn.agile.TC.viewmodel.ILogger;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtLogger implements ILogger {
    private String filename;
    private BufferedWriter bufferedWriter;
    private String lastMessageBuffer;

    private final static String MESSAGE_FORMAT = "%s > %s";


    public TxtLogger(String filename) {
        this.filename = filename;
        lastMessageBuffer = "";

        try {
            FileWriter fileWriter = new FileWriter(filename);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putMessage(String message) {
        lastMessageBuffer = String.format(MESSAGE_FORMAT, now(), message);
        tryWriteBuffer();
    }

    @Override
    public void putError(Errors message) {
        lastMessageBuffer = String.format(MESSAGE_FORMAT, now(), message.toString());
        tryWriteBuffer();
    }

    @Override
    public List<String> getLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }

    @Override
    public String getLastMessage() {
        return lastMessageBuffer;
    }

    private void tryWriteBuffer() {
        try {
            bufferedWriter.write(lastMessageBuffer);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String now() {
        Date now = new Date();
        return now.toString();
    }
}