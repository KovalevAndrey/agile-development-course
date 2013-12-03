package ru.unn.agile.currencyConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class NullLoggerTest{
    ILogger logger;

    @Before
    public  void SetUp()
    {
        logger = new NullLogger();
    }

    @After
    public  void CleanUp()
    {
        logger = null;
    }

    @Test
    public void isNullSafeWrapperSafeForWriteMessage()
    {
        logger.logMessage("test");
    }

    @Test
    public void isNullSafeWrapperSafeForWriteError()
    {
        logger.logError("test");
    }

    @Test
    public void isNullSafeWrapperSafeForSetLoggingLevel()
    {
        logger.setLogLevel(LoggingLevel.Release);
    }

    @Test
    public void isNullSafeWrapperSafeForGetLoggingLevel()
    {
        Assert.assertNull(logger.getLogLevel());
    }

    @Test
    public void isNullSafeWrapperSafeForGetLogFull()
    {
        Assert.assertNull(logger.getLogFull());
    }

    @Test
    public void isNullSafeWrapperSafeForGetLastLogMessage()
    {
        Assert.assertNull(logger.getLastLogMessage());
    }
}
