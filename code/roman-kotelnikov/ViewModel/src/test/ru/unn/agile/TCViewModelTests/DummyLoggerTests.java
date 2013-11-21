package ru.unn.agile.TCViewModelTests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.unn.agile.TC.viewmodel.DummyLogger;
import ru.unn.agile.TC.viewmodel.LogMessage;

import java.io.IOException;

import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.LOG_INFO_VM_OK;

public class DummyLoggerTests {
    private DummyLogger logger;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        logger = new DummyLogger();
    }

    @Test
    public void ensurePutMessageDoesNothingCriminal() {
        logger.putMessage(new LogMessage(LOG_INFO_VM_OK));
    }

    @Test
    public void ensureGetLogThrowsException() throws IOException {
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("DummyLogger cant return log");
        logger.getLog();
    }

    @Test
    public void ensureGetLastMessageThrownException() {
        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("Dummy logger cant get log last message");
        logger.getLastMessage();
    }
}
