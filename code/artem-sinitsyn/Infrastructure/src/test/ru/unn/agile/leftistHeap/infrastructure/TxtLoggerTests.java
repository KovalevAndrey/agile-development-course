package ru.unn.agile.leftistHeap.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class TxtLoggerTests {
    private static final String filename = "./TxtLoggerTestLog.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void canCreateTxtLogger() {
        assertNotNull(txtLogger);
    }

    @Test
    public void isLogFileCreated() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void canGetLog() {
        assertNotNull(txtLogger.getLog());
    }

    @Test
    public void areLogMessagesGotProperly() {
        txtLogger.log("First message");
        List<String> logFileContent = readLogFile();
        List<String> gotLog = txtLogger.getLog();
        assertLists(logFileContent, gotLog);
    }

    @Test
    public void doesLogSizeChangeOnWriting() {
        txtLogger.log("First message");
        assertTrue(txtLogger.getLog().size() > 0);
    }

    @Test
    public void isMessageWrittenProperly() {
        String message = "First message";
        txtLogger.log(message);
        assertEquals(message, txtLogger.getLog().get(0));
    }

    private void assertLists(List<String> expected, List<String> actual) {
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private List<String> readLogFile() {
        BufferedReader reader;
        ArrayList<String> logList = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                logList.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return logList;
    }
}
