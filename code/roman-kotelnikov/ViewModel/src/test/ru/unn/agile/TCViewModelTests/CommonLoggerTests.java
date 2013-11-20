package ru.unn.agile.TCViewModelTests;

import org.junit.Test;
import ru.unn.agile.TC.viewmodel.ILogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.unn.agile.TC.viewmodel.ILogger.Errors.*;

public abstract class CommonLoggerTests {
    protected ILogger logger;

    @Test
    public void canInitializeEmpty() {
        assertEquals(0, logger.getLog().size());
    }

    @Test
    public void canPutMessage() {
        String message = "Test message";
        logger.putMessage(message);

        assertTrue(logger.getLastMessage().contains(message));
    }

    @Test
    public void ensureLogSizeOnPutMessage() {
        logger.putMessage("msg");

        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void canPutError() {
        logger.putError(LOG_ERROR_WRONG_INPUT_STRING);
        String lastMessage = logger.getLastMessage();

        assertTrue(lastMessage.contains(LOG_ERROR_WRONG_INPUT_STRING.toString()));
    }

    @Test
    public void ensureLogSizeOnPutError() {
        logger.putError(LOG_ERROR_RESULT_SCALE_IS_NULL);

        assertEquals(1, logger.getLog().size());
    }

    @Test
    public void canGetEmptyStringIfEmpty() {
        assertEquals("", logger.getLastMessage());
    }
}
