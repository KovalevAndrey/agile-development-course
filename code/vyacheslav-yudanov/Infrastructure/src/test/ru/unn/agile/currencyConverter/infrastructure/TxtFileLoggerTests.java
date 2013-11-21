package ru.unn.agile.currencyConverter.infrastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.currencyConverter.viewmodel.FakeLogger;
import ru.unn.agile.currencyConverter.viewmodel.FakeLoggerTests;
import ru.unn.agile.currencyConverter.viewmodel.ILogger;
import ru.unn.agile.currencyConverter.viewmodel.LoggingLevel;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TxtFileLoggerTests extends FakeLoggerTests{
    private final String filename ="MyTestLog.txt";

    @Before
    public  void SetUp()
    {
        logger = new TxtFileLogger(filename);
    }

    @After
    public void CleanUp()
    {
        logger = null;
    }

    @Test
    public void isFileCreationAvaliable()
    {
        try {
            new FileWriter(filename);
        } catch (Exception e) {
            Assert.fail("Can't create file on disk.");
        }
    }
}
