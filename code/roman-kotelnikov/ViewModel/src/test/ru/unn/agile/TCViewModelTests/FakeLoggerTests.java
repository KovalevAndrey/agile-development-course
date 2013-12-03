package ru.unn.agile.TCViewModelTests;

import org.junit.Before;

public class FakeLoggerTests extends CommonLoggerTests {

    @Before
    public void setUp() {
        logger = new FakeLogger();
    }
}
