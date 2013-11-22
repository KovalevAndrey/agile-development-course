package ru.unn.agile.Re.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class RegexViewModelLoggerTests
{
    protected RegexViewModel viewModel;
    protected String TAG;
    protected ILogger log;
    protected String pattern;
    protected String text;

    @Before
    public void setUp()
    {
        viewModel = new RegexViewModel(new MockLogger());
        pattern = "{2}a";
        viewModel.pattern = pattern;
        text = "simple text";
        viewModel.text = text;

        try
        {
            log = getLogger("log");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        TAG = getClass().getName();
    }

    @After
    public void tearDown()
    {
        viewModel = null;
    }

    @Test
    public void canCreateViewModelWithLogger()
    {
        viewModel = new RegexViewModel(new MockLogger());
    }

    @Test(expected = RuntimeException.class)
    public void throwRuntimeExceptionInConstructorWhenLoggerIsNull()
    {
        viewModel = new RegexViewModel(null);
    }

    protected ILogger getLogger(String fieldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field loggerField = viewModel.getClass().getDeclaredField(fieldName);
        loggerField.setAccessible(true);
        return (ILogger) loggerField.get(viewModel);
    }

    @Test
    public void logIsEmptyAfterInitialization()
    {
        assertEquals(0, log.getLog().size());
    }

    @Test
    public void loggingInfoEntryIncrementsEmptyLogSize()
    {
        log.i(TAG, "info entry");
        assertEquals(1, log.getLog().size());
    }

    @Test
    public void loggingWarningEntryIncrementsEmptyLogSize()
    {
        log.w(TAG, "warning entry");
        assertEquals(1, log.getLog().size());
    }

    @Test
    public void loggingErrorEntryIncrementsEmptyLogSize()
    {
        log.e(TAG, "error entry");
        assertEquals(1, log.getLog().size());
    }

    @Test
    public void logPatternTextOnFocusLost()
    {
        viewModel.focusLost(TAG, pattern);
        assertThat(log.getLog().get(0).getText(), is(equalTo(pattern)));
    }

    @Test
    public void logSearchTextOnFocusLost()
    {
        viewModel.focusLost(TAG, text);
        assertThat(log.getLog().get(0).getText(), is(equalTo(text)));
    }

    @Test
    public void logErrorOnEmptyField()
    {
        String text = "";
        viewModel.text = text;
        viewModel.focusLost(TAG, text);
        assertThat(log.getLog().get(0).getType(), is(equalTo(ILogger.ERROR)));
    }

    @Test
    public void allLoggedFieldsAreNotEmpty()
    {
        viewModel.focusLost(TAG, text);
        LogEntry logEntry = log.getLog().get(0);

        assertTrue(logEntry.getDate() != null);
        assertTrue(logEntry.getTag()  != null);
        assertTrue(logEntry.getText() != null);
    }

    @Test
    public void logInfoEntryWhenFieldContentIsChanged()
    {
        viewModel.focusLost(TAG, text);
        assertThat(log.getLog().get(0).getType(), is(equalTo(ILogger.INFO)));
    }

    @Test
    public void logWarnEntryWhenFieldContentIsNotChanged()
    {
        viewModel.focusLost(TAG, text);
        viewModel.focusLost(TAG, text);
        assertThat(log.getLog().get(1).getType(), is(equalTo(ILogger.WARN)));
    }

    @Test
    public void logErrorEntryWhenException()
    {
        viewModel.pattern = "{1}a?";
        viewModel.search();
        assertThat(log.getLog().get(0).getType(), is(equalTo(ILogger.ERROR)));
    }

    @Test
    public void logInfoEntryWhenPatternIsFound()
    {
        viewModel.pattern = "{1}a";
        viewModel.text = "cat";
        viewModel.search();
        assertThat(log.getLog().get(0).getType(), is(equalTo(ILogger.INFO)));
    }

    @Test
    public void logInfoEntryWhenPatternIsNotFound()
    {
        viewModel.pattern = "{1}a";
        viewModel.text = "dog";
        viewModel.search();
        assertThat(log.getLog().get(0).getType(), is(equalTo(ILogger.INFO)));
    }
}
