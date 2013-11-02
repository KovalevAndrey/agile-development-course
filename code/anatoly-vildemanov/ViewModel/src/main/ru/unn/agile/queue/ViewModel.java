package ru.unn.agile.queue;

public class ViewModel {
    public String Element;
    public String topElement;
    public String result;
    public String message;
    public String size;
    public Queue queue;

    public ClickHandler pushActionHandler;
    public ClickHandler popActionHandler;
    public ClickHandler cleanActionHandler;

    public ViewModel() {
        queue = new Queue();
        size = "0";

        pushActionHandler = new ClickHandler() {
            public void onClick() {
                ViewModel.this.bind();
                ViewModel.this.pushProcessAction();
                ViewModel.this.unbind();
            }
        };

        popActionHandler = new ClickHandler() {
            public void onClick() {
                ViewModel.this.bind();
                ViewModel.this.popProcessAction();
                ViewModel.this.unbind();
            }
        };

        cleanActionHandler = new ClickHandler() {
            public void onClick() {
                ViewModel.this.bind();
                ViewModel.this.cleanProcessAction();
                ViewModel.this.unbind();
            }
        };
    }

    public void bind() {}
    public void unbind() {}

    private void pushProcessAction()
    {
        int el;
        try {
            el = Integer.parseInt(Element);
        }
        catch (Exception e) {
            result = "NA";
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

    private void popProcessAction()
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

    private void cleanProcessAction()
    {
        queue.clean();
        setStatusField();
    }

    private void setStatusField()
    {
        size = Integer.toString(queue.getCount());
        message = "Success";
    }
}
