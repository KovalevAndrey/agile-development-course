package ru.unn.agile.queue;

public class ViewModel {
    public String Element = "";
    public String topElement = "";
    public String message = "";
    public String size;
    public Queue queue;

    public ViewModel() {
        queue = new Queue();
        size = "0";
    }

    public void bind() {}
    public void unbind() {}

    public void pushProcessAction()
    {
        int el;
        try {
            el = Integer.parseInt(Element);
        }
        catch (Exception e) {
            message = "Bad Format";
            return;
        }
        if (queue.isFull())
        {
            message = "Queue is full";
            return;
        }
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
            message = "Queue is empty";
            return;
        }
        setStatusField();
    }

    public void cleanProcessAction()
    {
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
}
