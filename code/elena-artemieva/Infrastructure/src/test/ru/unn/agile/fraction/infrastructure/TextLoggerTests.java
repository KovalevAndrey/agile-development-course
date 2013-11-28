package ru.unn.agile.fraction.infrastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertNotNull;

public class TextLoggerTests {

    private static final String filename = "./TextLoggerTests.log";
    private TextLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TextLogger(filename);
    }

    @Test
    public void canCreateTxtLogger() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            fail("File " + filename + " was not found!");
        }
    }

    @Test
    public void canGetLog() {
        assertNotNull(txtLogger.getLog());
    }

    @Test
    public void isLogSizeNotEmptyWhenAddAnything() {
        txtLogger.log("Test message");

        assertNotEquals(0, txtLogger.getLog().size());
    }

    @Test
    public void canWriteOneMessage() {
        String testMessage = "Test message";
        txtLogger.log(testMessage);
        String logString = txtLogger.getLog().get(0);

        assertEqualsWithData(testMessage, logString);
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] testMessages = {"Test message 1", "Test message 2"};

        txtLogger.log(testMessages[0]);
        txtLogger.log(testMessages[1]);

        List<String> logMessages = txtLogger.getLog();

        assertEqualsWithData(testMessages[0], logMessages.get(0));
        assertEqualsWithData(testMessages[1], logMessages.get(1));
    }

    @Test
    public void canLogContainDate() {
        txtLogger.log("Test message");

        assertEqualsWithData("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} .*", txtLogger.getLog().get(0));
    }

    private void assertEqualsWithData(String message, String logMessages) {
        Pattern pattern = Pattern.compile(".*" + message + ".*");
        Matcher matcher = pattern.matcher(logMessages);

        assertEquals(true, matcher.matches());
    }
}
