package ru.unn.agile.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest
{
    private Stack stack;

    @Before
    public void setUp()
    {
        stack=new Stack();
    }

    @Test
    public void IsEmptyAfterCreation()
    {
        assertEmpty(true);
    }

    @Test
    public void IsEmptyAfterPushingNullString()
    {
        stack.Push("");
        assertEmpty(true);
    }

    @Test
    public void IsNotEmptyAfterPushingStringWithOneElement()
    {
        String element="6";
        stack.Push(element);
        assertEmpty(false);
    }

    @Test
    public void PushOneElementIsItOnTop()
    {
        String element="9";
        stack.Push(element);
        assertEquals(element, stack.Top());
    }

    @Test
    public void PushListOfElementsIsLastElementOnTop()
    {
        String elementList="7,4,9";
        stack.Push(elementList);
        assertEquals("9",stack.Top());
    }

    @Test
    public void IsStackOverflowAfterPushingBigListOfElements()
    {
        String elementList="8,4,1,6,8,2,6,1,9,5,3,9";
        try
        {
            stack.Push(elementList);
            fail("Runtime exception was expected");
        }
        catch (RuntimeException e)
        {
            assertEquals("Stack is overflow", e.getMessage());
        }
        catch (Exception e)
        {
            fail("Unexpected exception: " + e.toString());
        }
    }

    @Test
    public void CanPopOneElementFromNonEmptyStack()
    {
        String elementList="7,4,9";
        stack.Push(elementList);
        assertEquals("9", stack.Pop());
    }

    @Test
    public void IsEmptyAfterPoppingAllElements()
    {
        String elementList="7,4,9";
        stack.Push(elementList);
        for (int i=0;i<3;i++)
            stack.Pop();
        assertEmpty(true);
    }

    @Test
    public void IsEmptyIfPopEmptyStack()
    {
        assertEquals("stack is empty", stack.Pop());
    }

    @Test
    public void IsEmptyIfTopEmptyStack()
    {
        assertEquals("stack is empty", stack.Top());
    }

    @Test
    public void IsEmptyAfterPushingStringWithOnlyCommas()
    {
        String element=",,,,,,";
        stack.Push(element);
        assertEmpty(true);
    }

    @Test
    public void PushListOfElementsWithOddComaIsLastElementOnTop()
    {
        String elementList="8,6,";
        stack.Push(elementList);
        assertEquals("6", stack.Top());
    }

    private void assertEmpty(boolean expected)
    {
        assertEquals(expected,stack.IsEmpty());
    }
}
