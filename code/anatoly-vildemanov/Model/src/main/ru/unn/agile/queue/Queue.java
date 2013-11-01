package ru.unn.agile.queue;

public class Queue {

    private int[] queue;
    private int tail;
    private int head;
    private int count;
    private final int MAX_LEN = 10;

    public Queue()
    {
        queue = new int[MAX_LEN];
        count = head = 0;
        tail = -1;
    }

    public boolean isEmpty()
    {
        return (count == 0);
    }

    public void push(int element)
    {
        if (!isFull())
        {
            tail = (tail + 1) % MAX_LEN;
            queue[tail] = element;
            count++;
        }
    }

    public int top()
    {
        return queue[head];
    }

    public int pop()
    {
       if(isEmpty())
       {
           throw new RuntimeException("queue is empty");
       }
       count--;
       int res = queue[head];
       head = (head + 1) % MAX_LEN;
       return res;
    }

    public boolean isFull()
    {
        return count >= MAX_LEN;
    }
}
