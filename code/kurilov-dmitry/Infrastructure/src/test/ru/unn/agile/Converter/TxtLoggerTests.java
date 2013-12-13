package ru.unn.agile.Converter;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TxtLoggerTests
{
    private static final String filename = "./TxtLoggerTestLog.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp()
    {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void tryingOfCreatingTxtLogger()
    {
        assertNotNull(txtLogger);
    }

    @Test
    public void tryingOfCreatingLogFileCreated()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void tryingOfGettingLog()
    {
        assertNotNull(txtLogger.getLog());
    }

    @Test
    public void validOfLogMessages()
    {
        txtLogger.log("Test message");
        List<String> logFileContent = readLogFile();
        List<String> gotLog = txtLogger.getLog();
        assertLists(logFileContent, gotLog);
    }

    @Test
    public void changingOfLogSizeOnWriting()
    {
        txtLogger.log("Test message");
        assertTrue(txtLogger.getLog().size() > 0);
    }

    @Test
    public void isMessageWrittenProperly()
    {
        String message = "Test message";
        txtLogger.log(message);
        assertEquals(message, txtLogger.getLog().get(0));
    }

    private void assertLists(List<String> expected, List<String> actual)
    {
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    private List<String> readLogFile()
    {
        BufferedReader reader;
        ArrayList<String> logList = new ArrayList<String>();

        try
        {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null)
            {
                logList.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return logList;
    }
}
