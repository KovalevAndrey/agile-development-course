package ru.unn.agile.queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    private ViewModel viewModel;
    private Queue queue;

    @Before
    public void setUp()
    {
        viewModel = new ViewModel();
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
        viewModel.pushActionHandler.onClick();

        assertEquals("1", viewModel.size);
    }

    @Test
    public void canSetBadFormatMessage()
    {
        viewModel.Element = "ololo";
        viewModel.pushActionHandler.onClick();

        assertEquals("Bad Format", viewModel.message);
    }

    @Test
    public void canSetSuccessMessageAfterPush()
    {
        viewModel.Element = "2";
        viewModel.pushActionHandler.onClick();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canPopElementAfterPush()
    {
        viewModel.Element = "2";
        viewModel.pushActionHandler.onClick();
        viewModel.popActionHandler.onClick();

        assertEquals("2", viewModel.topElement);
    }

    @Test
    public void canChangeSizeAfterPopElement()
    {
        viewModel.Element = "2";
        viewModel.pushActionHandler.onClick();
        viewModel.popActionHandler.onClick();

        assertEquals("0", viewModel.size);
    }

    @Test
    public void canSetSuccessMassageAfterPopElement()
    {
        viewModel.Element = "2";
        viewModel.pushActionHandler.onClick();
        viewModel.popActionHandler.onClick();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canSetMessageWhenPopEmptyQueue()
    {
        viewModel.popActionHandler.onClick();

        assertEquals("Queue is empty", viewModel.message);
    }

    @Test
    public void canSetMessageWhenPushInFullQueue()
    {
        for (int i = 0; i < queue.getMaxCount() + 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushActionHandler.onClick();
        }

        assertEquals("Queue is full", viewModel.message);
    }

    @Test
    public void canCleanQueue()
    {
        for (int i = 0; i < queue.getMaxCount() - 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushActionHandler.onClick();
        }
        viewModel.cleanActionHandler.onClick();

        assertEquals("0", viewModel.size);
    }

    @Test
    public void canSetMessageAfterClean()
    {
        for (int i = 0; i < queue.getMaxCount() - 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushActionHandler.onClick();
        }
        viewModel.cleanActionHandler.onClick();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canCleanWorkElements()
    {
        for (int i = 0; i < queue.getMaxCount() - 2; i++)
        {
            viewModel.Element = "2";
            viewModel.pushActionHandler.onClick();
        }
        viewModel.popActionHandler.onClick();
        viewModel.cleanActionHandler.onClick();

        assertEquals("", viewModel.topElement);
        assertEquals("", viewModel.Element);
    }
}
