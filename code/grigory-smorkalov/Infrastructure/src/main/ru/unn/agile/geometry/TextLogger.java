package ru.unn.agile.geometry;

import ru.unn.agile.geometry.viewModel.ILogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class TextLogger implements ILogger{
    private String fileName;
    private BufferedWriter writer = null;

    public TextLogger(String filename) {
        if(filename == null || filename.length() == 0) {
            throw new IllegalArgumentException("Empty filename");
        }
        this.fileName = filename;

        try {
            FileWriter logFile = new FileWriter(filename);
            writer = new BufferedWriter(logFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void message(String msg) {
        write(ILogger.MESSAGE_PREFIX + ":\t" + msg);
    }

    public void debug(String msg) {
        write(ILogger.DEBUG_PREFIX + ":\t" + msg);
    }

    public List<String> getLog() {
        return null;
    }

    private void write(String string) {
        try {
            writer.write(string);
            writer.newLine();
            writer.flush();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
