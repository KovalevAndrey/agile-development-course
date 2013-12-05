package ru.unn.agile.UnitConverter.viewmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FakeLoggerTests {
    private FakeLogger fakeLogger;

    @Before
    public void setUp() {
        fakeLogger = new FakeLogger();
    }

    @Test
    public void canWriteFakeLog() {
        String tag = "canWriteFakeLog";
        String msg = "example";
        fakeLogger.Log(tag, msg);

        assertEquals(fakeLogger.getLog().get(0), tag + ": " + msg);
    }

    @Test
    public void canWriteFakeLogs() {
        String tag = "canWriteFakeLogs";
        String msg0 = "example0";
        String msg1 = "example1";
        fakeLogger.Log(tag, msg0);
        fakeLogger.Log(tag, msg1);

        assertEquals(fakeLogger.getLog().get(0), tag + ": " + msg0);
        assertEquals(fakeLogger.getLog().get(1), tag + ": " + msg1);
    }

}
