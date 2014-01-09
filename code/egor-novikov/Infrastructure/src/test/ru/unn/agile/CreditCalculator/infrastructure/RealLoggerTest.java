package ru.unn.agile.CreditCalculator.infrastructure;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CreditCalculator.viewmodel.RegexHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class RealLoggerTest {
    private static final String LOGS_FILE_PATH = "./test-log.txt";
    private RealLogger realLogger;

    @Before
    public void setUp() {
        realLogger = new RealLogger(LOGS_FILE_PATH);
    }

    @After
    public void tearDown() {
        realLogger = null;
    }

    @Test
    public void canCreateLogger() {
        assertNotNull(realLogger);
    }

    @Test
    public void canCreateLogFile() {
        try {
            FileReader fileReader = new FileReader(LOGS_FILE_PATH);
            new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            fail("File " + LOGS_FILE_PATH + " has not been found!");
        }
    }

    @Test
    public void canWriteMessageToLogger() {
        String testMessage = "My first message to log!";
        realLogger.Log(testMessage);
        String logString = realLogger.getLog().get(0);
        assertEquals(true, RegexHelper.IsMessageOfLogString(testMessage, logString));
    }

    @Test
    public void canWriteListOfMessagesToLogger() {
        String message1 = "My 1st message to log!";
        String message2 = "My 2nd message to log!";
        String message3 = "My 3rd message to log!";

        realLogger.Log(message1);
        realLogger.Log(message2);
        realLogger.Log(message3);

        List<String> loggerMessages = realLogger.getLog();

        Assert.assertEquals(true, RegexHelper.IsMessageOfLogString(message1, loggerMessages.get(0)));
        Assert.assertEquals(true, RegexHelper.IsMessageOfLogString(message2, loggerMessages.get(1)));
        Assert.assertEquals(true, RegexHelper.IsMessageOfLogString(message3, loggerMessages.get(2)));
    }
}
