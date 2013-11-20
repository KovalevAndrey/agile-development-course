package ru.unn.agile.Re.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Re.model.ReError;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class RegexViewModelTests
{
    private RegexViewModel viewModel;
    private String TAG;
    private ILogger logger;

    @Before
    public void setUp()
    {
        viewModel = new RegexViewModel(new MockLogger());

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
    public void canSetDefaultStatus()
    {
        assertEquals("", viewModel.status);
    }

    @Test
    public void canSetDefaultPattern()
    {
        assertEquals("", viewModel.pattern);
    }

    @Test
    public void canSetDefaultText()
    {
        assertEquals("", viewModel.text);
    }

    @Test
    public void setStatusMessageWithZeroFirstOccurrenceWhenPatternIsEmpty()
    {
        viewModel.text = "cat\n";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.FIRST_OCCURRENCE_IS + 0)));
    }

    @Test
    public void setNothingFoundStatusWhenSearchInEmptyText()
    {
        viewModel.pattern = "cat";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.NOTHING_FOUND)));
    }

    @Test
    public void setMultipleRegexMessageOnMultipleRegex()
    {
        viewModel.pattern = "?{1}*";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(ReError.MULTIPLE_REGEX)));
    }

    @Test
    public void setNotFoundMessageIfPatternNotFound()
    {
        viewModel.text = "dog";
        viewModel.pattern = "{1}cat";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.NOTHING_FOUND)));
    }

    @Test
    public void setFoundMessageWhenPatternIsFound()
    {
        viewModel.text = "dog\ncat\n";
        viewModel.pattern = "^cat";
        viewModel.search();
        assertThat(viewModel.status, is(equalTo(Status.FIRST_OCCURRENCE_IS + 4)));
    }

    @Test
    public void setSameStatusWithSamePattern()
    {
        viewModel.text = "dog\ncat\n";
        viewModel.pattern = "^cat";
        viewModel.search();

        String previousStatus = viewModel.status;
        viewModel.search();

        assertEquals(viewModel.status, previousStatus);
    }

    @Test
    public void setDifferentStatusWithDifferentPattern()
    {
        viewModel.text = "dog\ncat\n";
        viewModel.pattern = "^cat";
        viewModel.search();

        String previousStatus = viewModel.status;
        viewModel.pattern = "^dog";
        viewModel.search();

        assertNotEquals(viewModel.status, previousStatus);
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
}
