package ru.unn.agile.TC.infrastructure;

import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.LogMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ru.unn.agile.TC.viewmodel.LogFormat.*;

public class TxtLogger implements ILogger {
    private String filename;
    private BufferedWriter bufferedWriter;
    private String lastMessageBuffer;

    public TxtLogger(String filename) throws IOException{
        if(filename == null)
            throw new IllegalArgumentException("File name cannot be null");

        if(filename.isEmpty())
            throw new IllegalArgumentException("File name cannot be empty");

        this.filename = filename;
        lastMessageBuffer = "";

        bufferedWriter = new BufferedWriter(new FileWriter(filename));
    }

    @Override
    public void putMessage(LogMessage message) {
        if(message == null)
            throw new IllegalArgumentException("LogMessage cannot be null");

        String formattedMessage = formatEntry(message);

        boolean writeOk = tryWriteBuffer(formattedMessage);
        if(writeOk)
            lastMessageBuffer = formattedMessage;
        else
            throw new UnsupportedOperationException("Something wrong with file. Message wasn't logged.");
    }

    @Override
    public List<String> getLog() throws IOException{
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }

        return log;
    }

    @Override
    public String getLastMessage() {
        if(lastMessageBuffer.isEmpty())
            throw new UnsupportedOperationException("Cannot get last message of empty log");

        return lastMessageBuffer;
    }

    private boolean tryWriteBuffer(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}