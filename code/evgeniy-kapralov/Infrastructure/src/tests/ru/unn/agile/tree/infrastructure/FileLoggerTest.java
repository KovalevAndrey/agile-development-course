package ru.unn.agile.tree.infrastructure;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.unn.agile.tree.infrastructure.RegexMatcher.matchesPattern;

public class FileLoggerTest {
    private FileLogger fileLogger;

    @Before
    public void setUp() {
        fileLogger = new FileLogger("./FileLoggerTest.log");
    }

    @Test
    public void loggerNotNull() {
         assertNotEquals(null, fileLogger);
    }

    @Test
    public void exceptionWithNullFileName() {
        try {
            new FileLogger(null);
            fail("Exception was not thrown");
        }
        catch(IllegalArgumentException ex) {
            assertEquals("File name cannot be empty", ex.getMessage());
        }
        catch(Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void exceptionWithEmptyFileName() {
        try {
            new FileLogger("");
            fail("Exception was not thrown");
        }
        catch(IllegalArgumentException ex) {
            assertEquals("File name cannot be empty", ex.getMessage());
        }
        catch(Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logMessage() {
        String message = "ololo";
        fileLogger.log(message);
        assertEquals(readLog().get(0), message);
    }

    @Test
    public void logMultipleMessages() {
        ArrayList<String> log = new ArrayList<String>();
        for (int i = 0; i < 5; i++)
        {
            log.add("ololo " + Integer.toString(i));
            fileLogger.log("ololo " + Integer.toString(i));
        }
        assertEquals(readLog(), log);
    }

    @Test
    public void logMessageWithDate() {
        String message = "ololo";
        fileLogger.logWithDate(message);
        assertThat(readLog().get(0), matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} .*"));
    }

    @Test
    public void logMultipleMessagesWithDate() {
        for (int i = 0; i < 5; i++)
        {
            fileLogger.logWithDate("ololo " + Integer.toString(i));
        }
        List<String> log = readLog();
        for (String line : log) {
            assertThat(line, matchesPattern("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} .*"));
        }
    }

    @Test
    public void readLogCorrectly() {
        ArrayList<String> log = new ArrayList<String>();
        for (int i = 0; i < 5; i++)
        {
            log.add("ololo " + Integer.toString(i));
            fileLogger.log("ololo " + Integer.toString(i));
        }
        assertEquals(log, fileLogger.readLog());
    }

    private List<String> readLog() {
        BufferedReader bufReader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            bufReader = new BufferedReader(new FileReader("./FileLoggerTest.log"));
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                log.add(line);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
