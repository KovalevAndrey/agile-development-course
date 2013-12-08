package ru.unn.agile.BitArray.Insfrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class TextLoggerTest {
    private static final String LoggerFileName = "./log.log";
    private TextLogger logger;

    @Before
    public void setUp() throws Exception {
        logger = new TextLogger(LoggerFileName);
    }

    @Test
    public void canCreateWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void canCreateFile() {
        File file = new File(LoggerFileName);
        assertTrue(file.exists());
    }

    @Test
    public void hasTime() {
        logger.log("some message");

        List<String> lines = readLoggedLines();

        Date date = getDateFromLogEntry(lines.get(0));
        assertNotNull(date);
    }

    @Test
    public void hasMessage() {
        String expectedMessage = "some message1";
        logger.log(expectedMessage);
        List<String> lines = readLoggedLines();

        String message = getMessage(lines.get(0));
        assertEquals(message, expectedMessage);
    }

    @Test
    public void hasSequence() {
        String expectedMessage = "some message2";
        logger.log(expectedMessage);
        List<String> lines = readLoggedLines();

        int seq = getSequence(lines.get(0));
        assertEquals(seq, 1);
    }

    @Test
    public void seqOrder() {
        String expected1stMsg = "first message";
        String expected2ndMsg = "second message";
        logger.log(expected1stMsg);
        logger.log(expected2ndMsg);

        List<String> lines = readLoggedLines();

        String msg1 = getMessage(lines.get(0));
        String msg2 = getMessage(lines.get(1));
        int seq1 = getSequence(lines.get(0));
        int seq2 = getSequence(lines.get(1));

        assertEquals(msg1, expected1stMsg);
        assertEquals(msg2, expected2ndMsg);
        assertEquals(seq1+1, seq2);
    }

    @Test
    public void testGetLogs() throws Exception {
        List<String> expectedLogs = new ArrayList<String>(){ {
            add("first log message");
            add("second log message");
            add("third log message");
        }};
        logger.log(expectedLogs.get(0));
        logger.log(expectedLogs.get(1));
        logger.log(expectedLogs.get(2));
        List<String> actualLogs = logger.getLogs();
        assertEquals(actualLogs.size(), expectedLogs.size());
        for(int i = 0; i < expectedLogs.size(); ++i) {
            assertEquals(expectedLogs.get(i), getMessage(actualLogs.get(i)));
        }
    }

    private int getSequence(String logEntry) {
        String[] elements = logEntry.split(TextLogger.STRUCT_DELIMITER);
        String strSeq = elements[0];
        int res = Integer.parseInt(strSeq);
        return res;
    }

    private String getMessage(String logEntry) {
        String[] elements = logEntry.split(TextLogger.STRUCT_DELIMITER);
        return elements[2];
    }

    private Date getDateFromLogEntry(String logEntry)
    {
        Date result = null;
        try {
            String[] elements = logEntry.split(TextLogger.STRUCT_DELIMITER);
            String target = elements[1];
            DateFormat df = new SimpleDateFormat(TextLogger.DATE_FORMAT, Locale.getDefault());
            result = df.parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<String> readLoggedLines()
    {
        BufferedReader reader;
        ArrayList<String> log = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(LoggerFileName));
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
