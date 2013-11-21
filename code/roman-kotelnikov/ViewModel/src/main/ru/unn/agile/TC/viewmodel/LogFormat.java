package ru.unn.agile.TC.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class LogFormat {
    public final static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DELIMITER = ">";
    public final static String MESSAGE_FORMAT = "%s" + DELIMITER + "%s";

    public final static SimpleDateFormat SDF = new SimpleDateFormat(TIMESTAMP_FORMAT);

    private static String now() {
        Date now = new Date();
        return SDF.format(now);
    }

    public static String formatEntry(LogMessage message) {
        if(message == null)
            throw new IllegalArgumentException("LogMessage cannot be null");

        return String.format(
                MESSAGE_FORMAT,
                now(),
                message);
    }

    public static boolean isFormatOk(String logMessage, String pattern) {
        if(logMessage == null)
            throw new IllegalArgumentException("LogMessage cannot be null");
        if(logMessage.isEmpty())
            throw new IllegalArgumentException("LogMessage cannot be empty");
        if(pattern == null)
            throw  new IllegalArgumentException("Pattern cannot be null");
        if(pattern.isEmpty())
            throw  new IllegalArgumentException("Pattern cannot be empty");


        String[] splitMessage = logMessage.split(DELIMITER);
        if(splitMessage.length != MESSAGE_FORMAT.split(DELIMITER).length)
            return false;
        if(splitMessage[0].isEmpty() || splitMessage[1].isEmpty())
            return false;

        try{
            SDF.parse(splitMessage[0]);
        }
        catch (ParseException e) {
            return false;
        }

        return splitMessage[1].equals(pattern);
    }


}
