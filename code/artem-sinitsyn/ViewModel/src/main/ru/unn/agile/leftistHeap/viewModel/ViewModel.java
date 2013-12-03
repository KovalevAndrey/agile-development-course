package ru.unn.agile.leftistHeap.viewModel;

import java.util.List;
import ru.unn.agile.leftistHeap.model.Pair;
import ru.unn.agile.leftistHeap.model.LeftistHeap;

public class ViewModel {

    public class Status {
        public static final String BAD_INPUT = "Invalid input";
        public static final String ADD_OK = "New element is successfully added";
        public static final String ADD_BAD = "Cant add new element";
        public static final String GET_OK = "Min is successfully got";
        public static final String GET_BAD = "Cant get min from empty heap";
        public static final String DEL_OK = "Min is successfully deleted";
        public static final String DEL_BAD = "Cant delete min from empty heap";
    }

    public class LogMessages {
        public static final String ADD = "Trying to add element: ";
        public static final String GET = "Trying to get minimum";
        public static final String DELETE = "Trying to delete minimum";
        public static final String SUCCESS = "Action has been successful";
        public static final String FAIL = "Action has failed";
    }

    private LeftistHeap leftistHeap;
    private ILogger logger;

    public String keyAdd;
    public String valueAdd;
    public String keyGetDel;
    public String valueGetDel;
    public String status;

    public ViewModel(ILogger logger) {
        if (logger == null)
            throw new IllegalArgumentException("Logger can't be null");

        keyAdd = "0";
        valueAdd = "Zero";
        keyGetDel = "";
        valueGetDel = "";
        status = "";
        leftistHeap = new LeftistHeap();
        this.logger = logger;
    }

    public void addToLeftistHeap() {
        logger.log(MakeAddMessage());

        keyGetDel = "";
        valueGetDel = "";

        try {
            leftistHeap.add(new Pair(Integer.parseInt(keyAdd), valueAdd));
            status = Status.ADD_OK;
            logger.log(LogMessages.SUCCESS);
        }
        catch (IllegalArgumentException ex) {
            status = Status.BAD_INPUT;
            logger.log(LogMessages.FAIL);
        }
        catch (Exception ex) {
            status = Status.ADD_BAD;
            logger.log(LogMessages.FAIL);
        }
    }

    public void getMinFromLeftistHeap() {
        logger.log(MakeGetMessage());

        keyAdd = "";
        valueAdd = "";

        try {
            Pair min = leftistHeap.getMin();
            keyGetDel = String.valueOf(min.getKey());
            valueGetDel = min.getValue();
            status = Status.GET_OK;
            logger.log(LogMessages.SUCCESS);
        }
        catch (Exception ex) {
            keyGetDel = "";
            valueGetDel = "";
            status = Status.GET_BAD;
            logger.log(LogMessages.FAIL);
        }
    }

    public void deleteMinFromLeftistHeap() {
        logger.log(MakeDeleteMessage());

        keyAdd = "";
        valueAdd = "";

        try {
            Pair min = leftistHeap.getMin();
            leftistHeap.deleteMin();
            keyGetDel = String.valueOf(min.getKey());
            valueGetDel = min.getValue();
            status = Status.DEL_OK;
            logger.log(LogMessages.SUCCESS);
        }
        catch (Exception ex)  {
            keyGetDel = "";
            valueGetDel = "";
            status = Status.DEL_BAD;
            logger.log(LogMessages.FAIL);
        }
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private String MakeAddMessage() {
        String message = LogMessages.ADD +
                "( " + keyAdd + ", " +
                valueAdd + " )";
        return message;
    }

    private String MakeGetMessage() {
        return LogMessages.GET;
    }

    private String MakeDeleteMessage() {
        return LogMessages.DELETE;
    }
}