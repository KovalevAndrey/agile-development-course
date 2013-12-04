package ru.unn.agile.UnitConverter.infrastructure;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class TxtFileLoggerTests {
    private static final String filename = "./TxtFileLoggerTests.log";
    private TxtFileLogger txtFileLogger;

    @Before
    public void setUp()
    {
        txtFileLogger = new TxtFileLogger(filename);
    }

    @Test
    public void canCreateTxtFileForLogs() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            fail("File " + filename + " can not be created");
        }
    }

    @Test
    public void  canCreateLogger() {
        assertNotNull(txtFileLogger);
    }

    @Test
    public void canWriteLogMessage() {
        String logMsg = "example";
        String tag = "canWriteLogMessage";
        txtFileLogger.Log(tag, logMsg);

        String message = readLogFile().get(0);
        assertTrue(message.contains(logMsg));
    }

    @Test
    public void canWriteLogMessages() {
        String logMsg = "example1";
        String tag = "canWriteLogMessages";
        txtFileLogger.Log(tag, logMsg);
        logMsg = "example2";
        txtFileLogger.Log(tag, logMsg);

        List<String> messages = readLogFile();
        assertTrue(messages.get(0).contains("example1"));
        assertTrue(messages.get(1).contains("example2"));
    }

    @Test
    public void canWriteLogTag() {
        String logMsg = "example";
        String tag = "canWriteLogTag";
        txtFileLogger.Log(tag, logMsg);

        String message = readLogFile().get(0);
        assertTrue(message.contains(tag));
    }

    @Test
    public void canWriteLogDate() {
        String logMsg = "example";
        String tag = "canWriteLogDate";
        txtFileLogger.Log(tag, logMsg);

        String message = readLogFile().get(0);
        assertTrue(message.matches("^\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\].*"));
    }

    private List<String> readLogFile() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
