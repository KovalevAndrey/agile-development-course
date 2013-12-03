package ru.unn.agile.stack.viewmodel;

import ru.unn.agile.stack.Stack;

import java.util.List;

public class ViewModelStack {

    public String status = Status.STARTING;
    public String input = "";
    public String topElement = "";

    private Stack stack;
    private ILogger logger;

    public ViewModelStack(ILogger logger)
    {
        stack=new Stack();
        if (logger!=null)
            this.logger=logger;
        else
            throw new RuntimeException("null pointer");
    }

    public void pushPushAction()
    {
        logger.Log("tried to push");
        try
        {
        stack.Push(input);
        if (stack.IsEmpty())
            status = Status.NO_ADDED;
        else
            status = Status.ADDED;
        }
        catch (Exception e)
        {
            status = Status.OVERFLOW;
        }
        pushLogger();
    }

    private void pushLogger()
    {
        if (status==Status.ADDED)
            logger.Log("pushed");
        else logger.Log("not pushed");
    }

    public void popPushAction()
    {
        logger.Log("tried to pop");
        topElement = stack.Pop();
        if (topElement.equals("stack is empty"))
        {
            topElement = "" ;
            status = Status.EMPTY;
        }
        else
            status = Status.POPPED;
        popLogger();
    }

    private void popLogger()
    {
        if (status==Status.POPPED)
            logger.Log("popped");
        else
            logger.Log("not popped");
    }

    public void topPushAction()
    {
        logger.Log("tried to top");
        topElement = stack.Top();
        if (topElement.equals("stack is empty"))
        {
            topElement = "" ;
            status = Status.EMPTY;
        }
        else
            status = Status.TOPPED;
        topLogger();
    }

    public List<String> getLog()
    {
        List<String> log = logger.getLog();
        return log;
    }

    private void topLogger()
    {
        if (status==Status.TOPPED)
            logger.Log("topped");
        else
            logger.Log("not topped");
    }

    public class Status
    {
        public static final String STARTING = "You can add element to stack";
        public static final String NO_ADDED= "No elements added";
        public static final String ADDED = "Elements added";
        public static final String EMPTY = "Stack is empty";
        public static final String TOPPED = "Top element topped";
        public static final String POPPED = "Top element popped";
        public static final String OVERFLOW = "Stack is overflow";
    }


}
