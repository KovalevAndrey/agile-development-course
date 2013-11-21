package ru.unn.agile.TCViewModelTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.LogFormat;
import ru.unn.agile.TC.viewmodel.LogMessage;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.*;

public abstract class CommonLoggerTests {
    protected ILogger logger;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void canInitializeEmpty() throws IOException {
        assertEquals(0, logger.getLog().size());
    }

    @Test
    public void canPutMessage() throws IOException {
        LogMessage message = new LogMessage(LOG_INFO_VM_OK);
        logger.putMessage(message);

        assertTrue(LogFormat.isFormatOk(logger.getLastMessage(),
                message.toString()));
        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void ensureMessageIsNullExceptionThrown() throws IOException{
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("LogMessage cannot be null");
        logger.putMessage(null);
    }

    @Test
    public void ensureEmptyLogGetLastMessageExceptionThrown() throws IOException{
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("Cannot get last message of empty log");
        logger.getLastMessage();
    }
}
