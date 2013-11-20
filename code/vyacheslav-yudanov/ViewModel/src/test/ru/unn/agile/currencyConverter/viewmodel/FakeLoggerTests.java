package ru.unn.agile.currencyConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.unn.agile.currencyConverter.*;

public class FakeLoggerTests {
     private FakeLogger logger;

    @Before
    public  void SetUp()
    {
        logger = new FakeLogger();
    }

    @After
    public void CleanUp()
    {
        logger = null;
    }

    @Test
    public void fakeLoggerConstructorWorksCorrect()
    {
        Assert.assertEquals(LoggingLevel.Debug, logger.getLogLevel());
        Assert.assertNotNull(logger.getLogFull());
        Assert.assertEquals(0, logger.getLogFull().size());
    }

    @Test
    public void setLoggingLevelWorksCorrect()
    {
        logger.setLogLevel(LoggingLevel.Release);
        Assert.assertEquals(LoggingLevel.Release, logger.getLogLevel());
    }

    @Test
    public void lastMessageIsNullInEmptyLog()
    {
        Assert.assertNull(logger.getLastLogMessage());
    }

    @Test
    public void releaseGreaterThanDebugOrdinal()
    {
        Assert.assertTrue(LoggingLevel.Release.ordinal() > LoggingLevel.Debug.ordinal());
    }

    @Test
    public void debugLevelLogsErrors()
    {
        String message = "Error to log";

        logger.logError(message);

        Assert.assertEquals("ERROR: " + message, logger.getLastLogMessage());
    }

    @Test
    public void releaseLevelLogsErrors()
    {
        String message = "Error to log";

        logger.setLogLevel(LoggingLevel.Release);
        logger.logError(message);

        Assert.assertEquals("ERROR: " + message, logger.getLastLogMessage());
    }

    @Test
    public void debugLevelLogsInformation()
    {
        String message = "Information to log";

        logger.logMessage(message);

        Assert.assertEquals(message, logger.getLastLogMessage());
    }

    @Test
    public void releaseLevelNotLogInformation()
    {
        String message = "Information to log";

        logger.setLogLevel(LoggingLevel.Release);
        logger.logMessage(message);

        Assert.assertNull(logger.getLastLogMessage());
    }
}
