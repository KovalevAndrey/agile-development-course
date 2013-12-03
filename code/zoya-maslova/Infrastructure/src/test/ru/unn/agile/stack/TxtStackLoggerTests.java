package ru.unn.agile.stack;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class TxtStackLoggerTests {

    private TxtStackLogger txtStackLogger;
    private static final String fileName="TxtStackLoggerTests.log";
    private static final String str = "Test, test, test";
    private static final String[] strArray  = {"Ping","Pong"};

    @Before
    public void setUp()
    {
        txtStackLogger = new TxtStackLogger(fileName);
    }

    @Test
    public void canCreateLoggerWithFileName()
    {
        assertNotNull(txtStackLogger);
    }

    @Test
    public void isLogFileCreatedOnDisk()
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            assertNotNull(reader);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void canWriteToLogOneMessageWithoutDate()
    {
        txtStackLogger.LogString(str);
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            assertEquals(str, reader.readLine());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void canWriteToLogTwoMessagesWithoutDate()
    {
        txtStackLogger.LogString(strArray[0]);
        txtStackLogger.LogString(strArray[1]);
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            assertEquals(strArray[0], reader.readLine());
            assertEquals(strArray[1], reader.readLine());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void canWriteToLogMessageWithDate()
    {
        txtStackLogger.Log(str);
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            assertEqualsWithRegularExpr(".*" + str + "$",reader.readLine());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void isLogContainDate() {
        txtStackLogger.Log(str);
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            assertEqualsWithRegularExpr("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} .*",reader.readLine());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void ifPerformGetLogForEmptyLog()
    {
        try
        {
            List<String> log = txtStackLogger.getLog();
            assertEquals("[]", log.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void ifPerformGetLogForNotEmptyLog()
    {
        txtStackLogger.Log(str);
        try
        {
            List<String> log = txtStackLogger.getLog();
            assertNotEquals("[]",log.toString());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void ifPerformGetLogForLogWithOneMessage()
    {
        txtStackLogger.Log(str);
        try
        {
            List<String> log = txtStackLogger.getLog();
            assertEqualsWithRegularExpr(".*" + str + "$",log.get(0));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void ifPerformGetLogForLogWithTwoMessages()
    {
        txtStackLogger.Log(strArray[0]);
        txtStackLogger.Log(strArray[1]);
        try
        {
            List<String> log = txtStackLogger.getLog();
            assertEqualsWithRegularExpr(".*" + strArray[0] + "$",log.get(0));
            assertEqualsWithRegularExpr(".*" + strArray[1] + "$",log.get(1));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void assertEqualsWithRegularExpr(String expr, String output)
    {
        Pattern pattern = Pattern.compile(expr);
        Matcher matcher = pattern.matcher(output);
        assertEquals(true, matcher.matches());
    }
}
