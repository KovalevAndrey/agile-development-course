package ru.unn.agile.Huffman.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class TxtLoggerTests {
    private static final String filename = "./HuffmanTxtLoggerTestLog.log";
    private TxtLogger txtLogger;

    @Before
    public void beforeTest() {
        txtLogger = new TxtLogger(filename);
    }

    @Test
    public void txtLoggerCanBeCreated() {
        assertNotNull(txtLogger);
    }

    @Test
    public void logFileCanBeCreated() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void txtLogCanBeGet() {
        assertNotNull(txtLogger.getLog());
    }

    @Test
    public void RecordGetFromFileLogAndFromLoggerAreEquals(){
        txtLogger.logInfo("Example");
        assertLists(readLogFile(), txtLogger.getLog());
    }

    @Test
    public void logSizeMustBeIncreaseThenAddRecord() {
        txtLogger.logInfo("Example");
        assertTrue(txtLogger.getLog().size() > 0);
    }

    @Test
    public void messageWriteToLoggerCorrect() {
        String message = "Example";
        txtLogger.logInfo(message);
        assertEquals(true, txtLogger.getLog().get(0).indexOf("Example")>0);
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
