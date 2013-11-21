package ru.unn.agile.currencyConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class NullSafeLoggerWrapperTest extends FakeLoggerTests{

    @Before
    public  void init()
    {
        logger = new NullSafeLoggerWrapper(new FakeLogger());
    }

    @After
    public  void tearDown()
    {
        logger = null;
    }

    private void initNullLogger()
    {
        logger = new NullSafeLoggerWrapper(null);
    }

    @Test
    public void isNullSafeWrapperSafeForWriteMessage()
    {
        initNullLogger();
        logger.logMessage("test");
    }

    @Test
    public void isNullSafeWrapperSafeForWriteError()
    {
        initNullLogger();
        logger.logError("test");
    }

    @Test
    public void isNullSafeWrapperSafeForSetLoggingLevel()
    {
        initNullLogger();
        logger.setLogLevel(LoggingLevel.Release);
    }

    @Test
    public void isNullSafeWrapperSafeForGetLoggingLevel()
    {
        initNullLogger();
        Assert.assertNull(logger.getLogLevel());
    }

    @Test
    public void isNullSafeWrapperSafeForGetLogFull()
    {
        initNullLogger();
        Assert.assertNull(logger.getLogFull());
    }

    @Test
    public void isNullSafeWrapperSafeForGetLastLogMessage()
    {
        initNullLogger();
        Assert.assertNull(logger.getLastLogMessage());
    }
}
