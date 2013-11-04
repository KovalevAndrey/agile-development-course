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
}
