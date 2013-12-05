package ru.unn.agile.MathStatistic.infrastructure;

import ru.unn.agile.MathStatistic.viewModel.ILogger;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class TextFileLogger implements ILogger {

    private String fileName;
    private BufferedWriter bufferedWriter;

    public TextFileLogger(String actFileName) throws IOException {
        fileName = actFileName;
        FileWriter fileWriter;
        if (actFileName.isEmpty()) {
            throw new IllegalArgumentException("empty file name");
        } else {
            try {
                fileWriter = new FileWriter(fileName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new IOException("cannot create file: " + fileName);
            }
            bufferedWriter = new BufferedWriter(fileWriter);
        }
    }

    @Override
    public void saveToLog(String message, MessageType type) {
        String header;
        switch (type) {
            case INFO:
                header = "[info] ";
                break;
            case WARNING:
                header = "[warning] ";
                break;
            case ERROR:
                header = "[error] ";
                break;
            case UNKNOWN:
                throw new IllegalArgumentException("you can specify only: info, warning or error");
            default:
                throw new IllegalArgumentException("unknown message type");
        }

        Date now = new Date();
        String data = now.toString() + ":";

        try {
            bufferedWriter.write(header + data + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<String> getEntireLog() {
        ArrayList<String> entireLog = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                entireLog.add(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return entireLog;
    }

    @Override
    public ArrayList<String> getParticularType(MessageType requiredType) {
        ArrayList<String> entireLog = getEntireLog();
        ArrayList<String> filteredStorage = new ArrayList<String>();
        MessageType actType;
        for (String item : entireLog) {

            if (item.indexOf("[info]") == 0) {
                actType = MessageType.INFO;
            } else if (item.indexOf("[warning]") == 0) {
                actType = MessageType.WARNING;
            } else if (item.indexOf("[error]") == 0) {
                actType = MessageType.ERROR;
            } else {
                actType = MessageType.UNKNOWN;
            }

            if (actType == requiredType) {
                filteredStorage.add(item);
            }
        }
        return filteredStorage;
    }
}
