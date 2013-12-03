package ru.unn.agile.TCViewModelTests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.unn.agile.TC.viewmodel.LogFormat;
import ru.unn.agile.TC.viewmodel.LogMessage;

import static org.junit.Assert.*;

import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.LOG_INFO_CONVERT;

public class LogFormatTests {
    private LogMessage message;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        message = new LogMessage(LOG_INFO_CONVERT, "34.0 F", "24.0 C");
    }

    @Test
    public void canFormatEntry() {
        String formatString = LogFormat.formatEntry(message);

        assertTrue(LogFormat.isFormatOk(formatString, message.toString()));
    }

    @Test
    public void ensureFormatEntryNullMessageExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("LogMessage cannot be null");
        LogFormat.formatEntry(null);
    }

    @Test
    public void canCheckWhetherFormatIsOk() {
        String formatString = LogFormat.formatEntry(message);
        assertTrue(LogFormat.isFormatOk(formatString, message.toString()));
    }

    @Test
    public void ensureIsFormatOkNullLogMessageExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("LogMessage cannot be null");
        LogFormat.isFormatOk(null, "fake pattern");
    }

    @Test
    public void ensureIsFormatOkEmptyLogMessageExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("LogMessage cannot be empty");
        LogFormat.isFormatOk("", "fake pattern");
    }

    @Test
    public void ensureIsFormatOkNullPatternExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Pattern cannot be null");
        LogFormat.isFormatOk(LogFormat.formatEntry(message), null);
    }

    @Test
    public void ensureIsFormatOkEmptyPatternExceptionThrown() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Pattern cannot be empty");
        LogFormat.isFormatOk(LogFormat.formatEntry(message), "");
    }

    @Test
    public void ensureWrongFormatStringIsRecognized() {
        boolean testFailed = false;
        testFailed |= LogFormat.isFormatOk(">2013-11-21 23:15:34 Delimiter in wrong place",
                "2013-11-21 23:15:34 Delimiter in wrong place");
        testFailed |= LogFormat.isFormatOk("2013 11 21 23:15:34>Wrong data format", "Wrong data format");
        testFailed |= LogFormat.isFormatOk("2013-11-21 23:15:34>smth", "wrong pattern");

        assertTrue(!testFailed);
    }
}
