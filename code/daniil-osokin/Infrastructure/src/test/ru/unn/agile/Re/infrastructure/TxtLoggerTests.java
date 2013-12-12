package ru.unn.agile.Re.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Re.viewmodel.ILogger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TxtLoggerTests
{
    private TxtLogger log;
    private String tag;
    private String text;

    @Before
    public void setUp()
    {
        log = new TxtLogger("textLoggerOutput.log");
        tag = "TxtLoggerTests";
        text = "logged event";
    }

    @After
    public void tearDown()
    {
        log = null;
    }

    @Test
    public void canCreateWithNotEmptyFilename()
    {
        log = new TxtLogger("qqq.log");
    }

    @Test(expected = RuntimeException.class)
    public void throwExceptionWhenCreateWithNullName()
    {
        log = new TxtLogger(null);
    }

    @Test(expected = RuntimeException.class)
    public void throwExceptionWhenCreateWithEmptyName()
    {
        log = new TxtLogger("");
    }

    @Test
    public void logInfoEntry()
    {
        log.i(tag, text);
        assertThat(log.getLog().get(0)[ILogger.TYPE_ID], is(equalTo(ILogger.INFO)));
    }

    @Test
    public void logWarnEntry()
    {
        log.w(tag, text);
        assertThat(log.getLog().get(0)[ILogger.TYPE_ID], is(equalTo(ILogger.WARN)));
    }

    @Test
    public void logErrorEntry()
    {
        log.e(tag, text);
        assertThat(log.getLog().get(0)[ILogger.TYPE_ID], is(equalTo(ILogger.ERROR)));
    }

    @Test
    public void logMultipleEntry()
    {
        log.i(tag, text);
        log.e(tag, text);
        log.w(tag, text);
        assertThat(log.getLog().size(), is(equalTo(3)));
    }

    @Test
    public void loggedDateIsNoNull()
    {
        log.i(tag, text);
        assertTrue(log.getLog().get(0)[ILogger.DATE_ID] != null);
    }

    @Test
    public void sanityCheckWithOneEntry()
    {
        log.i(tag, text);

        String[] actualLogEntry = log.getLog().get(0);
        assertThat(actualLogEntry, is(equalTo(new String[]{ILogger.INFO, tag, text, actualLogEntry[ILogger.DATE_ID]})));
    }

    private String[][] getEntriesArray()
    {
        String[][] entries = new String[2][2];
        entries[0][0] = "tag1";
        entries[0][1] = "text1";
        entries[1][0] = "tag2";
        entries[1][1] = "text2";

        return entries;
    }

    @Test
    public void sanityCheckWithMultipleLogEntries()
    {
        String[][] entries = getEntriesArray();
        log.i(entries[0][0], entries[0][1]);
        log.i(entries[1][0], entries[1][1]);

        for (int i = 0; i < entries.length; i++)
        {
            String[] actualLogEntry = log.getLog().get(i);
            assertThat(actualLogEntry, is(equalTo(new String[]{ILogger.INFO, entries[i][0], entries[i][1], actualLogEntry[ILogger.DATE_ID]})));
        }
    }

}
