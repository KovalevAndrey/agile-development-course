package ru.unn.agile.stack.viewmodel;

import ru.unn.agile.stack.Stack;

public class ViewModelStack {

    public String status = Status.STARTING;
    public String input = "";
    public String topElement = "";

    private Stack stack;

    public ViewModelStack()
    {
        stack=new Stack();
    }

    public void pushPushAction()
    {
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
    }

    public void popPushAction()
    {
        topElement = stack.Pop();
        if (topElement.equals("stack is empty"))
        {
            topElement = "" ;
            status = Status.EMPTY;
        }
        else
            status = Status.POPPED;
    }

    public void topPushAction()
    {
        topElement = stack.Top();
        if (topElement.equals("stack is empty"))
        {
            topElement = "" ;
            status = Status.EMPTY;
        }
        else
            status = Status.TOPPED;
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
