package ru.unn.agile.converter.viewmodel;

public class LogMessage {
    private String timeLog = "";
    private String textLog = "";
    private LogStatus statusLog;

    public LogMessage(String time, String text, LogStatus status) {
        this.timeLog = time;
        this.textLog = text;
        this.statusLog = status;
    }

    public LogMessage(String input, String separator){
        String[] stringForReading = new String[3];
        stringForReading = input.split(separator);
        this.timeLog = stringForReading[0];
        this.textLog = stringForReading[1];
        this.statusLog = LogStatus.valueOf(stringForReading[2]);
    }

    public String getTimeLog(){
        return timeLog;
    }

    public String getTextLog(){
        return textLog;
    }

    public LogStatus getStatusLog(){
        return statusLog;
    }

    public String toString() {
        String result = "";
        result = timeLog + " > " + textLog + " Status: " + statusLog;
        return result;
    }

    public String toStringForFile(String separator){
        String result = "";
        result = timeLog + separator + textLog + separator + statusLog;
        return result;
    }
}