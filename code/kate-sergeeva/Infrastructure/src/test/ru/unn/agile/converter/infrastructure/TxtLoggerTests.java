package ru.unn.agile.converter.infrastructure;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.converter.viewmodel.LogStatus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class TxtLoggerTests {
    private TxtLogger txtLogger;
    private String file = "./TxtLogger.log";

    @Before
    public void setUp(){
        txtLogger = new TxtLogger(file);
    }

    @Test
    public void isCreateLoggerWithFile(){
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            fail("File " + file + " wasn't found!");
        }
    }

    @Test
    public void isCorrectAddingMessageInLog(){
        String message = "Adding test log message.";
        txtLogger.Add(message, LogStatus.success);
        assertEquals(txtLogger.getLog(LogStatus.all).get(0), txtLogger.time() + " > " + "Adding test log message. Status: success");
    }

    @Test
    public void isCorrectGetLogWithDefinedStatus(){
        String message = "Adding test log message.";
        txtLogger.Add(message, LogStatus.error);
        assertEquals(txtLogger.getLog(LogStatus.error).get(0), txtLogger.time() + " > " + "Adding test log message. Status: error");
    }

    @Test
     public void isListEmptyWhenGetMessageWithAnotherStatus(){
        String message = "Adding test log message.";
        txtLogger.Add(message, LogStatus.error);
        txtLogger.Add(message, LogStatus.error);
        Assert.assertEquals(txtLogger.getLog(LogStatus.success).size(), 0);
    }

    @Test
    public void isListNotEmptyWhenGetMessageWithEqualStatus(){
        String message = "Adding test log message.";
        txtLogger.Add(message, LogStatus.error);
        txtLogger.Add(message, LogStatus.error);
        Assert.assertEquals(txtLogger.getLog(LogStatus.error).size(), 2);
    }
}
