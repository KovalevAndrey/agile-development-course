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
        String[] messages = new String[3];
        for (int i=0; i<messages.length; i++)
            messages[i] = "My " + i +" message to log!";

        for (int i=0; i<messages.length; i++)
            realLogger.Log(messages[i]);

        List<String> logMessages = realLogger.getLog();

        for (int i=0; i<messages.length; i++)
            Assert.assertEquals(true, RegexHelper.IsMessageOfLogString(messages[i], logMessages.get(i)));
    }
}
