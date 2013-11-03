package ru.unn.agile.stack.viewmodel;

import ru.unn.agile.stack.Stack;

public class ViewModelStack {

    public ClickHandler pushActionHandler;
    public ClickHandler popActionHandler;
    public ClickHandler topActionHandler;

    public String status = "You can add element to stack";
    public String input = "";
    public String topElement = "";

    private Stack stack;

    public ViewModelStack()
    {
        stack=new Stack();
        pushActionHandler = new ClickHandler() {
            public void onClick() {
                ViewModelStack.this.processPushAction();
            }
        };
        popActionHandler = new ClickHandler() {
            public void onClick() {
                ViewModelStack.this.popPushAction();
            }
        };
        topActionHandler = new ClickHandler() {
            public void onClick() {
                ViewModelStack.this.topPushAction();
            }
        };
    }

    private void processPushAction()
    {
        try
        {
        stack.Push(input);
        if (stack.IsEmpty())
            status = "No elements add";
        else
            status = "Elements added";
        }
        catch (Exception e)
        {
            status = "Stack is overflow";
        }
    }

    private void popPushAction()
    {
        topElement = stack.Pop();
        if (topElement.equals("stack is empty"))
        {
            topElement = "" ;
            status = "Stack is empty";
        }
        else
            status = "Top element popped";
    }

    private void topPushAction()
    {
        topElement = stack.Top();
        if (topElement.equals("stack is empty"))
        {
            topElement = "" ;
            status = "Stack is empty";
        }
        else
            status = "Top element topped";
    }
}
