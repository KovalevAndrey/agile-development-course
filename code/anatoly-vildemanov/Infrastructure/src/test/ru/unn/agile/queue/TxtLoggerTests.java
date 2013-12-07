package ru.unn.agile.queue;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TxtLoggerTests {
    private static final String filename = "./TxtLoggerTests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canGetExceptionCreateLoggerWithEmptyFileName() {
        try
        {
            TxtLogger logger =  new TxtLogger("");
        }
        catch (Exception e)
        {
            assertEquals("Filename cannot be empty", e.getMessage());
        }
    }

    @Test
    public void canGetExceptionCreateLoggerWithNull() {
        try
        {
            TxtLogger logger =  new TxtLogger(null);
        }
        catch (Exception e)
        {
            assertEquals("Filename cannot be null", e.getMessage());
        }
    }

    @Test
    public void canFoundLogOnDisk() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            fail("Log " + filename + " not found!");
        }
    }

    @Test
    public void canWriteToLog() {
        String text = "Ololo";

        txtLogger.writeLog(text);

        try
        {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            assertTrue(isRegexpInText(".*" + text + "$", reader.readLine()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void canWriteDataToLog() {
        String text = "Ololo";

        txtLogger.writeLog(text);

        try
        {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            assertTrue(isRegexpInText("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}>.*", reader.readLine()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void canGetEmptyLog() {
        List<String> log = txtLogger.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void canGetMessageFromLog() {
        String text = "Ololo";

        txtLogger.writeLog(text);
        List<String> log = txtLogger.getLog();
        assertTrue(isRegexpInText(".*" + text + "$", log.get(0)));
    }

    @Test
    public void canGetSeveralMessageFromLog() {
        String text = "Ololo",
        text2 = "test";
        txtLogger.writeLog(text);
        txtLogger.writeLog(text2);

        List<String> log = txtLogger.getLog();
        assertTrue(isRegexpInText(".*" + text + "$", log.get(0)));
        assertTrue(isRegexpInText(".*" + text2 + "$", log.get(1)));
    }

    private boolean isRegexpInText(String regexp, String text)
    {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }


}
