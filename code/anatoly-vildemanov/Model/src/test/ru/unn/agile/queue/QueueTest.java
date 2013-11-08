package ru.unn.agile.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue queue;

    @Before
    public void setUp() {
        queue = new Queue();
    }

    @Test
    public void createEmptyQueue()
    {
        assertEquals(true, queue.isEmpty());
    }

    @Test
    public void isEmptyAfterPush()
    {
        queue.push(2);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void isPushManyElements()
    {
        fillUpQueue(12);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void isFullTank()
    {
        fillUpQueue(7);
        assertFalse(queue.isFull());
    }

    @Test
    public void lookElement()
    {
        fillUpQueue(7);
        assertEquals(0, queue.top());
    }

    @Test
    public void takeHeadElement()
    {
        fillUpQueue(7);
        assertEquals(0, queue.pop());
    }

    @Test
    public void takeHeadElementInOnesElementQueue()
    {
        queue.push(3);
        queue.pop();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void pushPopPushElement()
    {
        queue.push(3);
        queue.pop();
        queue.push(2);
        assertEquals(2, queue.pop());
    }

    @Test
    public void pushListPopPartElements()
    {
        fillUpQueue(3);
        for (int i = 0; i < 2; i++)
            queue.pop();
        assertEquals(2, queue.pop());
    }

    @Test
    public void whenWorkLongTime()
    {
        for (int j = 0; j < 2; j++)
        {
            fillUpQueue(7);
            for (int i = 0; i < 7; i++)
                queue.pop();
        }
        fillUpQueue(7);
        assertEquals(0, queue.pop());
    }


    @Test
    public void morePopThenPush()
    {
        try
        {
            queue.push(2);
            queue.pop();
            queue.pop();
            fail();
        }
        catch (Exception e)
        {
            assertEquals("queue is empty",e.getMessage());
        }
    }

    @Test
    public void cleanQueueFromOneElement()
    {
        queue.push(2);
        queue.clean();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void addElementAfterClean()
    {
        queue.push(2);
        queue.clean();
        queue.push(3);
        assertEquals(3, queue.pop());
    }

    private void fillUpQueue(int countOfElement)
    {
        for (int i = 0; i < countOfElement; i++)
            queue.push(i);
    }

}
