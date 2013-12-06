package ru.unn.agile.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ViewModelTests {
    protected ViewModel viewModel;
    protected Queue queue;

    @Before
    public void setUp()
    {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
        queue = new Queue();
    }

    @After
    public void tearDown()
    {
        viewModel = null;
    }

    @Test
    public void isDefaultSize()
    {
        assertEquals("0", viewModel.size);
    }

    @Test
    public void canPushElement()
    {
        viewModel.Element = "1";
        viewModel.pushProcessAction();

        assertEquals("1", viewModel.size);
    }

    @Test
    public void canSetBadFormatMessage()
    {
        viewModel.Element = "ololo";
        viewModel.pushProcessAction();

        assertEquals("Bad Format", viewModel.message);
    }

    @Test
    public void canSetSuccessMessageAfterPush()
    {
        viewModel.Element = "2";
        viewModel.pushProcessAction();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canPopElementAfterPush()
    {
        viewModel.Element = "2";
        viewModel.pushProcessAction();
        viewModel.popProcessAction();

        assertEquals("2", viewModel.topElement);
    }

    @Test
    public void canChangeSizeAfterPopElement()
    {
        viewModel.Element = "2";
        viewModel.pushProcessAction();
        viewModel.popProcessAction();

        assertEquals("0", viewModel.size);
    }

    @Test
    public void canSetSuccessMassageAfterPopElement()
    {
        viewModel.Element = "2";
        viewModel.pushProcessAction();
        viewModel.popProcessAction();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canSetMessageWhenPopEmptyQueue()
    {
        viewModel.popProcessAction();

        assertEquals("Queue is empty", viewModel.message);
    }

    @Test
    public void canSetMessageWhenPushInFullQueue()
    {
        for (int i = 0; i < queue.getMaxCount() + 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushProcessAction();
        }

        assertEquals("Queue is full", viewModel.message);
    }

    @Test
    public void canCleanQueue()
    {
        for (int i = 0; i < queue.getMaxCount() - 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushProcessAction();
        }
        viewModel.cleanProcessAction();

        assertEquals("0", viewModel.size);
    }

    @Test
    public void canSetMessageAfterClean()
    {
        for (int i = 0; i < queue.getMaxCount() - 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushProcessAction();
        }
        viewModel.cleanProcessAction();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canCleanWorkElements()
    {
        for (int i = 0; i < queue.getMaxCount() - 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushProcessAction();
        }
        viewModel.popProcessAction();
        viewModel.cleanProcessAction();

        assertEquals("", viewModel.topElement);
        assertEquals("", viewModel.Element);
    }

    @Test
    public void createViewModelWithLogger() {
        FakeLogger fakeLogger = new FakeLogger();
        ViewModel viewModelFakeLogged = new ViewModel(fakeLogger);

        assertNotNull(viewModelFakeLogged);
    }

    @Test
    public void catchExceptionWithNullLogger() {
        try
        {
            new ViewModel(null);
        }
        catch(IllegalArgumentException ex)
        {
            assertEquals("Error! Logger is NULL!", ex.getMessage());
        }
    }

    @Test
    public void getEmptyLog() {
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void pushElementCheckLog() {
        viewModel.Element = "2";
        viewModel.pushProcessAction();

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isLogContainMassageAboutFullQueue() {
        for (int i = 0; i < queue.getMaxCount() + 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushProcessAction();
        }

        String message = viewModel.getLog().get(queue.getMaxCount());
        String regexp = ".*Queue is full.*";

        assertTrue(isRegexpInText(regexp, message));
    }

    @Test
    public void pushMoreMaxSizeElementInQueueCheckLog() {
        for (int i = 0; i < queue.getMaxCount() + 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushProcessAction();
        }

        assertEquals(queue.getMaxCount() + 2, viewModel.getLog().size());
    }

    @Test
    public void isLogContainRightMassage() {
        viewModel.Element = "2";
        viewModel.pushProcessAction();

        String message = viewModel.getLog().get(0);
        String regexp = ".*Push Element: "  + viewModel.Element + ".*";

        assertTrue(isRegexpInText(regexp, message));
    }

    @Test
    public void pushManyElementInQueueCheckLog() {
        for (int i = 0; i < queue.getMaxCount(); i++)
        {
            viewModel.Element = Integer.toString(i);
            viewModel.pushProcessAction();
        }

        String message = viewModel.getLog().get(queue.getMaxCount() - 1);
        String regexp = ".*Push Element: "  + Integer.toString(queue.getMaxCount() - 1) + ".*";

        assertTrue(isRegexpInText(regexp, message));
    }

    @Test
    public void isLogCleanQueue() {
        viewModel.cleanProcessAction();

        String message = viewModel.getLog().get(0);
        String regexp = ".*Cleaned.*";

        assertTrue(isRegexpInText(regexp, message));
    }

    @Test
    public void isLogContainMassageErrorAfterBadPush() {
        viewModel.Element = "w";
        viewModel.pushProcessAction();

        String message = viewModel.getLog().get(0);
        String regexp = ".*Error! Bad Format. Push: " + viewModel.Element + ".*";

        assertTrue(isRegexpInText(regexp, message));
    }

    @Test
    public void isLogContainMassageAboutEmptyQueue() {
        viewModel.popProcessAction();

        String message = viewModel.getLog().get(0);
        String regexp = ".*Queue is empty.*";

        assertTrue(isRegexpInText(regexp, message));
    }

    @Test
    public void isLogContainTopElement() {
        viewModel.Element = "2";
        viewModel.pushProcessAction();
        viewModel.popProcessAction();

        String message = viewModel.getLog().get(1);
        String regexp = ".*Pop Element: "  + viewModel.Element + ".*";

        assertTrue(isRegexpInText(regexp, message));
    }


    private boolean isRegexpInText(String regexp, String text)
    {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
