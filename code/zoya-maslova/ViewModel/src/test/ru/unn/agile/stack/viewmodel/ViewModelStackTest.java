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
        assertEquals(ViewModelStack.Status.STARTING, viewModel.status);
    }

    @Test
    public void IfPushEmptyString()
    {
        pushToStack("");
        assertEquals(ViewModelStack.Status.NO_ADDED, viewModel.status);
    }

    @Test
    public void ifIncorrectStringPushed()
    {
        pushToStack(",,,");
        assertEquals(ViewModelStack.Status.NO_ADDED, viewModel.status);
    }

    @Test
    public void ifOtherIncorrectStringPushed()
    {
        pushToStack("9,4,2,");
        assertEquals(ViewModelStack.Status.ADDED, viewModel.status);
    }

    @Test
    public void canPushOneElement()
    {
        pushToStack("4");
        assertEquals(ViewModelStack.Status.ADDED, viewModel.status);
    }

    @Test
    public void canPushManyElements()
    {
        pushToStack("4,7,2");
        assertEquals(ViewModelStack.Status.ADDED, viewModel.status);
    }

    @Test
    public void canNotPushTooManyElements()
    {
        pushToStack("9,5,1,7,4,9,3,6,3,7,8,2");
        assertEquals(ViewModelStack.Status.OVERFLOW, viewModel.status);
    }

    @Test
    public void canPopFromStackWithOneElement()
    {
        pushToStack("7");
        viewModel.popPushAction();
        assertEquals("7", viewModel.topElement);
        assertEquals(ViewModelStack.Status.POPPED, viewModel.status);
    }

    @Test
    public void canPopElementFromStackWithNotOneElement()
    {
        pushToStack("7,4,3");
        viewModel.popPushAction();
        assertEquals("3", viewModel.topElement);
        assertEquals(ViewModelStack.Status.POPPED, viewModel.status);
    }

    @Test
    public void canNotPopFromEmptyStack()
    {
        viewModel.popPushAction();
        assertEquals(ViewModelStack.Status.EMPTY, viewModel.status);
        assertEquals("", viewModel.topElement);
    }

    @Test
    public void canTopElementFromStackWithOneElement()
    {
        pushToStack("7");
        viewModel.topPushAction();
        assertEquals("7", viewModel.topElement);
        assertEquals(ViewModelStack.Status.TOPPED, viewModel.status);
    }

    @Test
    public void canTopElementFromStackWithNotOneElement()
    {
        pushToStack("7,4,3");
        viewModel.topPushAction();
        assertEquals("3", viewModel.topElement);
        assertEquals(ViewModelStack.Status.TOPPED, viewModel.status);
    }

    @Test
    public void canNotTopFromEmptyStack()
    {
        viewModel.topPushAction();
        assertEquals(ViewModelStack.Status.EMPTY, viewModel.status);
        assertEquals("", viewModel.topElement);
    }

    private void pushToStack(String inputString)
    {
        viewModel.input = inputString;
        viewModel.pushPushAction();
    }
}
