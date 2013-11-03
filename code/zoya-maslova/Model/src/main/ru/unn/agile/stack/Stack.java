package ru.unn.agile.stack;

public class Stack
{
    private int[] stack;
    private int topPointer;
    private final int SIZE_OF_STACK = 10;

    public Stack()
    {
        stack = new int[SIZE_OF_STACK];
        topPointer = -1;
    }

    public boolean IsEmpty()
    {
        return (topPointer == -1);
    }

    public void Push(String elements)
    {
        if (elements == "")
            return;

        String[] tokens = ParseStringToElements(elements);

        for (String token: tokens)
            try
            {
                PushOneElement(token);
            }
            catch (Exception e)
            {
                throw new RuntimeException("Stack is overflow");
            }
    }

    public String Top()
    {
        if (IsEmpty())
            return "stack is empty";
        return Integer.toString(stack[topPointer]);
    }

    public String Pop()
    {
        if (IsEmpty())
            return "stack is empty";
        return Integer.toString(stack[topPointer--]);
    }

    private void PushOneElement(String element)
    {
        stack[++topPointer] = Integer.parseInt(element);
    }

    private String[] ParseStringToElements(String string)
    {
        return string.split("[,]")  ;
    }

    public int GetSize()
    {
        return SIZE_OF_STACK;
    }
}
