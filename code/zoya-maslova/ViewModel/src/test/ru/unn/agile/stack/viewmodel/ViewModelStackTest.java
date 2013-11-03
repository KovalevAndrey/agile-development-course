package ru.unn.agile.stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelStackTest {

    private  ViewModelStack viewModel;

    @Before
    public void setUp()
    {
        viewModel=new ViewModelStack();
    }

    @After
    public void tearDown()
    {
        viewModel = null;
    }

    @Test
    public void canDisplayDefaultValues()
    {
        assertEquals("", viewModel.input);
        assertEquals("", viewModel.topElement);
        assertEquals("You can add element to stack", viewModel.status);
    }

    @Test
    public void IfPushEmptyString()
    {
        pushToStack("");
        assertEquals("No elements add", viewModel.status);
    }

    @Test
    public void ifIncorrectStringPushed()
    {
        pushToStack(",,,");
        assertEquals("No elements add", viewModel.status);
    }

    @Test
    public void ifOtherIncorrectStringPushed()
    {
        pushToStack("9,4,2,");
        assertEquals("Elements added", viewModel.status);
    }

    @Test
    public void canPushOneElement()
    {
        pushToStack("4");
        assertEquals("Elements added", viewModel.status);
    }

    @Test
    public void canPushManyElements()
    {
        pushToStack("4,7,2");
        assertEquals("Elements added", viewModel.status);
    }

    @Test
    public void canNotPushTooManyElements()
    {
        pushToStack("9,5,1,7,4,9,3,6,3,7,8,2");
        assertEquals("Stack is overflow", viewModel.status);
    }

    @Test
    public void canPopFromStackWithOneElement()
    {
        pushToStack("7");
        viewModel.popActionHandler.onClick();
        assertEquals("7", viewModel.topElement);
        assertEquals("Top element popped", viewModel.status);
    }

    @Test
    public void canPopElementFromStackWithNotOneElement()
    {
        pushToStack("7,4,3");
        viewModel.popActionHandler.onClick();
        assertEquals("3", viewModel.topElement);
        assertEquals("Top element popped", viewModel.status);
    }

    @Test
    public void canNotPopFromEmptyStack()
    {
        viewModel.popActionHandler.onClick();
        assertEquals("Stack is empty", viewModel.status);
        assertEquals("", viewModel.topElement);
    }

    @Test
    public void canTopElementFromStackWithOneElement()
    {
        pushToStack("7");
        viewModel.topActionHandler.onClick();
        assertEquals("7", viewModel.topElement);
        assertEquals("Top element topped", viewModel.status);
    }

    @Test
    public void canTopElementFromStackWithNotOneElement()
    {
        pushToStack("7,4,3");
        viewModel.topActionHandler.onClick();
        assertEquals("3", viewModel.topElement);
        assertEquals("Top element topped", viewModel.status);
    }

    @Test
    public void canNotTopFromEmptyStack()
    {
        viewModel.topActionHandler.onClick();
        assertEquals("Stack is empty", viewModel.status);
        assertEquals("", viewModel.topElement);
    }

    private void pushToStack(String inputString)
    {
        viewModel.input = inputString;
        viewModel.pushActionHandler.onClick();
    }
}
