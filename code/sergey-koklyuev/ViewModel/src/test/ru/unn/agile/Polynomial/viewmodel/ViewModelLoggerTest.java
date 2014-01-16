package ru.unn.agile.Polynomial.viewmodel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class ViewModelLoggerTest {
    public ViewModel viewModel;
    public ILogger logger;

    @Before
    public void setUp() {
        logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @Test
    public void canCreateViewModelWithLogger() {
        ILogger logger = new FakeLogger();
        ViewModel viewModel = new ViewModel(logger);

        assertNotNull(viewModel);
    }

    @Test
    public void canSetLogLevel() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        assertEquals(LoggingLevel.DEBUG, viewModel.getLogLevel());

        viewModel.setLogLevel(LoggingLevel.RELEASE);
        assertEquals(LoggingLevel.RELEASE, viewModel.getLogLevel());
    }

    @Test
    public void throwExceptionIfLoggerIsNull() {
        try
        {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        }
        catch(IllegalArgumentException ex)
        {
            assertEquals("Logger parameter cannot be null", ex.getMessage());
        }
        catch(Exception ex)
        {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void operationAddSelectedAndPutToTheLogWithDebug() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setOperation(Operation.MUL);
        viewModel.setOperation(Operation.ADD);

        assertEquals("Operation Add was selected", viewModel.getLastLogMessage());
    }

    @Test
    public void operationAddSelectedWithRelease() {
        viewModel.setLogLevel(LoggingLevel.RELEASE);
        viewModel.setOperation(Operation.ADD);

        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void operationMulSelectedAndPutToTheLoggerWithDebug() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setOperation(Operation.MUL);

        assertEquals("Operation Mul was selected", viewModel.getLastLogMessage());
    }

    @Test
    public void operationMulSelectedWithRelease() {
        viewModel.setLogLevel(LoggingLevel.RELEASE);
        viewModel.setOperation(Operation.MUL);

        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void operationSubSelectedAndPutToTheLoggerWithDebug() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setOperation(Operation.SUB);

        assertEquals("Operation Sub was selected", viewModel.getLastLogMessage());
    }

    @Test
    public void operationSubSelectedWithRelease() {
        viewModel.setLogLevel(LoggingLevel.RELEASE);
        viewModel.setOperation(Operation.SUB);

        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void putResultToTheLog() {
        viewModel.setLogLevel(LoggingLevel.RELEASE);
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x");
        viewModel.setOperation(Operation.ADD);
        viewModel.calculate();

        assertEquals("Result: 2x + 1", viewModel.getLastLogMessage());
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.setLogLevel(LoggingLevel.RELEASE);
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x");
        viewModel.setOperation(Operation.ADD);

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(6, viewModel.getLog().size());
    }

    @Test
    public void operationLoggedOnlyIfChanged() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setOperation(Operation.MUL);
        viewModel.setOperation(Operation.MUL);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isEditingFinishLoggedForPolynomial1() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setPolynomial1("x + 1");

        viewModel.focusLost();
        assertEquals("Input arguments changed: p1 = x + 1, p2 = None", viewModel.getLastLogMessage());
    }

    @Test
    public void isEditingFinishLoggedForPolynomial2() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setPolynomial2("12x");

        viewModel.focusLost();
        assertEquals("Input arguments changed: p1 = None, p2 = 12x", viewModel.getLastLogMessage());
    }

    @Test
    public void isLogWhenCalledEnter() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("2x");
        viewModel.setOperation(Operation.ADD);

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);
        assertEquals("Result: 3x + 1", viewModel.getLastLogMessage());
        assertEquals(2, viewModel.getLog().size());
    }

    @Test
    public void isNotLogWhenButtonIsDisabled() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);
        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void editingLogOnlyIfPolynomialChanged() {
        viewModel.setLogLevel(LoggingLevel.DEBUG);

        viewModel.setPolynomial1("x");
        viewModel.focusLost();
        viewModel.setPolynomial1("x");
        viewModel.focusLost();

        assertEquals(1, viewModel.getLog().size());
    }

}
