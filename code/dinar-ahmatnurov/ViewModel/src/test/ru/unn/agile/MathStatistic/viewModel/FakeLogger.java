package ru.unn.agile.MathStatistic.viewModel;

import java.util.ArrayList;
import java.util.Date;

public class FakeLogger implements ILogger {
    private ArrayList<String> storage = new ArrayList<String>();

    @Override
    public ArrayList<String> getEntireLog() {
        return storage;
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

        storage.add(header + data + message);
    }


    public ArrayList<String> getParticularType(MessageType requiredType) {
        ArrayList<String> filteredStorage = new ArrayList<String>();
        MessageType actType;
        for (String item : storage) {

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
