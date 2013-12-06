package ru.unn.agile.Triangle.infrastucture;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TxtLoggerTest
{
    private static final String fileName = "./TxtLoggerTests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp()
    {
        txtLogger = new TxtLogger(fileName);
    }

    @Test
    public void canCreateLoggerWithFileName()
    {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk()
    {
        try
        {
            new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e)
        {
            fail("File " + fileName + " wasn't found!");
        }
    }

    @Test
    public void canWriteLogMessage()
    {
        String testMessage = "Test message";
        txtLogger.Log(testMessage);
        String message = readLog().get(0).substring(txtLogger.dateSize());
        assertEquals(message, testMessage);
    }
    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"Test message 1", "Test message 2"};

        txtLogger.Log(messages[0]);
        txtLogger.Log(messages[1]);

        List<String> actualMessages = readLog();
        for (int i = 0; i < actualMessages.size(); i++)
            assertEquals(actualMessages.get(i).substring(txtLogger.dateSize()), messages[i]);
    }

    private List<String> readLog() {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
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
