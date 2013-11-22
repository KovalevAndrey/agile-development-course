package ru.unn.agile.stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelStackTest {

    protected ViewModelStack viewModel;

    @Before
    public void setUp()
    {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModelStack(logger);
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

    @Test
    public void canCreateViewModelStackWithLogger()
    {
        FakeLogger logger = new FakeLogger();
        ViewModelStack viewModelLogged = new ViewModelStack(logger);
        assertNotNull(viewModelLogged);
    }

    @Test
    public void ifCreateViewModelStackWithEmptyLogger()
    {
        try
        {
            viewModel = new ViewModelStack(null);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void isLogEmptyWhenLogIsCreated()
    {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isLogNotEmptyWhenOneElementPushed()
    {
        pushToStack("8");
        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogNotEmptyWhenPopActionPerformed()
    {
        viewModel.popPushAction();
        assertNotEquals(0, viewModel.getLog().size());
    }

    @Test
    public void isLogNotEmptyWhenTopActionPerformed()
    {
        viewModel.topPushAction();
        assertNotEquals(0, viewModel.getLog().size());
    }

    @Test
    public void canWriteToLogOneElementPushed()
    {
        pushToStack("9");
        assertEqualsWithDate("tried to push",viewModel.getLog().get(0).toString());
        assertEqualsWithDate("pushed",viewModel.getLog().get(1).toString());
    }

    @Test
    public void canWriteToLogNotOneElementPushed()
    {
        pushToStack("9,5,7");
        assertEqualsWithDate("tried to push",viewModel.getLog().get(0).toString());
        assertEqualsWithDate("pushed",viewModel.getLog().get(1).toString());
    }

    @Test
    public void canWriteToLogTooManyElementsCanNotPushed()
    {
        pushToStack("9,5,1,7,4,9,3,6,3,7,8,2");
        assertEqualsWithDate("tried to push",viewModel.getLog().get(0).toString());
        assertEqualsWithDate("not pushed",viewModel.getLog().get(1).toString());
    }

    @Test
    public void canWriteToLogTopElementToppedFromNotEmptyStack()
    {
        pushToStack("8");
        viewModel.topPushAction();
        assertEqualsWithDate("tried to top",viewModel.getLog().get(2).toString());
        assertEqualsWithDate("topped",viewModel.getLog().get(3).toString());
    }

    @Test
    public void canWriteToLogTopElementCanNotToppedFromEmptyStack()
    {
        viewModel.topPushAction();
        assertEqualsWithDate("tried to top",viewModel.getLog().get(0).toString());
        assertEqualsWithDate("not topped",viewModel.getLog().get(1).toString());
    }

    @Test
    public void canWriteToLogTopElementPoppedFromNotEmptyStack()
    {
        pushToStack("8");
        viewModel.popPushAction();
        assertEqualsWithDate("tried to pop",viewModel.getLog().get(2).toString());
        assertEqualsWithDate("popped",viewModel.getLog().get(3).toString());
    }

    @Test
    public void canWriteToLogTopElementCanNotPoppedFromEmptyStack()
    {
        viewModel.popPushAction();
        assertEqualsWithDate("tried to pop",viewModel.getLog().get(0).toString());
        assertEqualsWithDate("not popped",viewModel.getLog().get(1).toString());
    }

    private void pushToStack(String inputString)
    {
        viewModel.input = inputString;
        viewModel.pushPushAction();
    }

    private void assertEqualsWithDate(String testString, String output)
    {
        Pattern pattern = Pattern.compile(".*" + testString + "$");
        Matcher matcher = pattern.matcher(output);
        assertEquals(true, matcher.matches());
    }
}
