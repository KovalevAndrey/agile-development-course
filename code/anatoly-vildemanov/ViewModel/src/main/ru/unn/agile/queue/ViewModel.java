package ru.unn.agile.queue;

import java.util.List;

public class ViewModel {
    public String Element = "";
    public String topElement = "";
    public String message = "";
    public String size;
    public Queue queue;
    public ILogger logger;

    public ViewModel(ILogger logger) {
        if(logger == null)
            throw new IllegalArgumentException("Error! Logger is NULL!");
        this.logger = logger;

        queue = new Queue();
        size = "0";
    }

    public void bind() {}

    public void pushProcessAction()
    {
        int el;
        try {
            el = Integer.parseInt(Element);
        }
        catch (Exception e) {
            logger.writeLog("Error! Bad Format. Push: " + Element);
            message = "Bad Format";
            return;
        }
        if (queue.isFull())
        {
            logger.writeLog("Queue is full");
            message = "Queue is full";
            return;
        }
        logger.writeLog("Push Element: " + Element);
        queue.push(el);
        setStatusField();
    }

    public void popProcessAction()
    {
        try {
            topElement = Integer.toString(queue.pop());
        }
        catch (Exception e)
        {
            logger.writeLog("Queue is empty");
            message = "Queue is empty";
            return;
        }
        logger.writeLog("Pop Element: " + topElement);
        setStatusField();
    }

    public void cleanProcessAction()
    {
        logger.writeLog("Cleaned");
        queue.clean();
        Element = "";
        topElement = "";
        setStatusField();
    }

    private void setStatusField()
    {
        size = Integer.toString(queue.getCount());
        message = "Success";
    }

    public List<String> getLog() {
        List<String> log = logger.getLog();
        return log;
    }
}
