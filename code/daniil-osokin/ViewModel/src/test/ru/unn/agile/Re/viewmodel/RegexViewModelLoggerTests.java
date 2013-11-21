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
    private RegexViewModel viewModel;
    private String TAG;
    private ILogger logger;
    private String pattern;
    private String text;

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
            logger = getLogger("logger");
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

    private ILogger getLogger(String fieldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field loggerField = viewModel.getClass().getDeclaredField(fieldName);
        loggerField.setAccessible(true);
        return (ILogger) loggerField.get(viewModel);
    }

    @Test
    public void logIsEmptyAfterInitialization()
    {
        assertEquals(0, logger.getLog().size());
    }

    @Test
    public void loggingInfoEntryIncrementsEmptyLogSize()
    {
        logger.i(TAG, "info entry");
        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void loggingWarningEntryIncrementsEmptyLogSize()
    {
        logger.w(TAG, "warning entry");
        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void loggingErrorEntryIncrementsEmptyLogSize()
    {
        logger.e(TAG, "error entry");
        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void logPatternTextOnFocusLost()
    {
        viewModel.focusLost(TAG, pattern);
        assertThat(pattern, is(equalTo(logger.getLog().get(0).getText())));
    }

    @Test
    public void logSearchTextOnFocusLost()
    {
        viewModel.focusLost(TAG, text);
        assertThat(text, is(equalTo(logger.getLog().get(0).getText())));
    }

    @Test
    public void logErrorOnEmptyField()
    {
        String text = "";
        viewModel.text = text;
        viewModel.focusLost(TAG, text);
        assertThat(ILogger.ERROR, is(equalTo(logger.getLog().get(0).getPriority())));
    }

    @Test
    public void allLoggedFieldsAreNotEmpty()
    {
        viewModel.focusLost(TAG, text);
        LogEntry logEntry = logger.getLog().get(0);

        assertTrue(logEntry.getDate() != null);
        assertTrue(logEntry.getTag()  != null);
        assertTrue(logEntry.getText() != null);
    }

    @Test
    public void logInfoEntryWhenFieldContentIsChanged()
    {
        viewModel.focusLost(TAG, text);
        assertThat(ILogger.INFO, is(equalTo(logger.getLog().get(0).getPriority())));
    }

    @Test
    public void logWarnEntryWhenFieldContentIsNotChanged()
    {
        viewModel.focusLost(TAG, text);
        viewModel.focusLost(TAG, text);
        assertThat(ILogger.WARN, is(equalTo(logger.getLog().get(1).getPriority())));
    }
}
