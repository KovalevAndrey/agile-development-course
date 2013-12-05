package ru.unn.agile.MathStatistic.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MathStatistic.viewModel.ILogger;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class TextFileLoggerTest {

    private TextFileLogger textFileLogger;

    @Before
    public void setUp() {
        try {
            textFileLogger = new TextFileLogger("./test.log");
        } catch (IOException e) {
            fail("cannot create logger");
        }
    }

    @After
    public void tearDown() {
        textFileLogger = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void canConstructorHandleEmptyArgument() {
        try {
            textFileLogger = new TextFileLogger("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canConstructorHandleBadArgument() {
        String fileName = ".!@#$%^&*().txt";
        try {
            textFileLogger = new TextFileLogger(fileName);
            fail("you cant create a file named \"" + fileName + "\"");
        } catch (IOException e) {
        }
    }

    @Test
    public void canGetOnlyInfoType() {
        textFileLogger.saveToLog("testMessage1", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage2", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage3", ILogger.MessageType.ERROR);
        textFileLogger.saveToLog("testMessage4", ILogger.MessageType.ERROR);
        textFileLogger.saveToLog("testMessage5", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage6", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage7", ILogger.MessageType.ERROR);
        ArrayList<String> particularTypeLog = textFileLogger.getParticularType(ILogger.MessageType.INFO);

        assertEquals(2, particularTypeLog.size());

        String logItem = particularTypeLog.get(0);
        assertEquals(true, logItem.matches(".*testMessage1"));

        logItem = particularTypeLog.get(1);
        assertEquals(true, logItem.matches(".*testMessage5"));
    }

    @Test
    public void canGetOnlyWarningType() {
        textFileLogger.saveToLog("testMessage1", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage2", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage3", ILogger.MessageType.ERROR);
        textFileLogger.saveToLog("testMessage4", ILogger.MessageType.ERROR);
        textFileLogger.saveToLog("testMessage5", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage6", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage7", ILogger.MessageType.ERROR);
        ArrayList<String> particularTypeLog = textFileLogger.getParticularType(ILogger.MessageType.WARNING);

        assertEquals(2, particularTypeLog.size());

        String logItem = particularTypeLog.get(0);
        assertEquals(true, logItem.matches(".*testMessage2"));

        logItem = particularTypeLog.get(1);
        assertEquals(true, logItem.matches(".*testMessage6"));
    }

    @Test
    public void canGetOnlyErrorType() {
        textFileLogger.saveToLog("testMessage1", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage2", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage3", ILogger.MessageType.ERROR);
        textFileLogger.saveToLog("testMessage4", ILogger.MessageType.ERROR);
        textFileLogger.saveToLog("testMessage5", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage6", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage7", ILogger.MessageType.ERROR);
        ArrayList<String> particularTypeLog = textFileLogger.getParticularType(ILogger.MessageType.ERROR);

        assertEquals(3, particularTypeLog.size());

        String logItem = particularTypeLog.get(0);
        assertEquals(true, logItem.matches(".*testMessage3"));

        logItem = particularTypeLog.get(1);
        assertEquals(true, logItem.matches(".*testMessage4"));

        logItem = particularTypeLog.get(2);
        assertEquals(true, logItem.matches(".*testMessage7"));
    }

    @Test
    public void canAddToLog() {
        textFileLogger.saveToLog("testMessage1", ILogger.MessageType.INFO);
        textFileLogger.saveToLog("testMessage2", ILogger.MessageType.WARNING);
        textFileLogger.saveToLog("testMessage3", ILogger.MessageType.ERROR);
        ArrayList<String> entireLog = textFileLogger.getEntireLog();

        assertEquals(3, entireLog.size());

        String logItem = entireLog.get(0);
        assertEquals(true, logItem.matches(".*testMessage1"));

        logItem = entireLog.get(1);
        assertEquals(true, logItem.matches(".*testMessage2"));

        logItem = entireLog.get(2);
        assertEquals(true, logItem.matches(".*testMessage3"));
    }
}
