package ru.unn.agile.TCViewModelTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.unn.agile.TC.viewmodel.LogMessage;

import static org.junit.Assert.*;
import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.*;

public class LogMessageTests {
    private LogMessage logMessage;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void canGetPrettyInfo() {
        assertEquals("Conversion result success",
                LOG_INFO_VM_OK.toString());
    }

    @Test
    public void canGetPrettyError() {
        assertEquals("ERROR #1:User haven't selected input scale",
                LOG_ERROR_INPUT_SCALE_IS_NULL.toString());
    }

    @Test
    public void canCreateLogMessage() {
        logMessage = new LogMessage(LOG_INFO_VM_OK);

        assertNotNull(logMessage);
    }

    @Test
    public void canCreateLogMessageWithMoreThanZeroArgs() {
        logMessage = new LogMessage(LOG_INFO_INPUT, "fake1", "fake2", "fake3");

        assertNotNull(logMessage);
    }

    @Test
    public void canGetPrettyLogMessageString() {
        logMessage =
                new LogMessage(LOG_INFO_CONVERT, "this", "that");
        String checingkMessage = String.format(LOG_INFO_CONVERT.toString(),
                "this", "that");
        assertEquals(logMessage.toString(), checingkMessage);
    }

    @Test
    public void ensureWrongSlotsNumberExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Inconsistent number of arguments passed");
        logMessage = new LogMessage(LOG_INFO_VM_OK, "extra arg");
    }

    @Test
    public void ensureNullMessagePatternExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("MessagePattern cannot be null");
        logMessage = new LogMessage(null);
    }
}
