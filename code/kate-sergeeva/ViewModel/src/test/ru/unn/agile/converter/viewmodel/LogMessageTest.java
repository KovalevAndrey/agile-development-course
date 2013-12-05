package ru.unn.agile.converter.viewmodel;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class LogMessageTest {
    private LogMessage logMessage;

    @Before
    public void setUp() {
        logMessage = new LogMessage("12.03.2012", "Test message.", LogStatus.success);
    }

    @Test
    public void canCreateMessage(){
        logMessage = new LogMessage("12.03.2012/Test message./" + LogStatus.success.toString(), "/");
        assertNotNull(logMessage);
    }

    @Test
    public void isCorrectConvertToString(){
        logMessage = new LogMessage("12.03.2012", "Test message.", LogStatus.success);
        assertEquals(logMessage.toString(), "12.03.2012 > Test message. Status: success");
    }

    @Test
    public void isCorrectConvertToStringForFile(){
        logMessage = new LogMessage("12.03.2012", "Test message.", LogStatus.success);
        assertEquals(logMessage.toStringForFile("/"), "12.03.2012/Test message./success");
    }

    @Test
    public void isCorrectSetMessageAtStringForFile() {
        logMessage = new LogMessage("12.03.2012/Test message./" + LogStatus.success.toString(), "/");
        assertEquals(logMessage.toString(), "12.03.2012 > Test message. Status: success");
    }
}
