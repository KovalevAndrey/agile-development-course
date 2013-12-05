package ru.unn.agile.calculating;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileLogger implements ILogger {

    List<String> strings = new ArrayList<String>();
    OutputStreamWriter out;

    FileLogger(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists())
                file.createNewFile();

            out = new OutputStreamWriter(new FileOutputStream(file, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putMessage(String mes) {
        String date = new Date().toString();
        strings.add(date + ": " + mes);
        try {
            out.write(date + ": ");
            out.write(mes + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        }
    }

    @Override
    public String getLastMessage() {
        String res = strings.size() == 0 ? "" : strings.get(strings.size() - 1);
        return res;
    }

    @Override
    public List<String> getAllMessages() {
        return strings;
    }
}
