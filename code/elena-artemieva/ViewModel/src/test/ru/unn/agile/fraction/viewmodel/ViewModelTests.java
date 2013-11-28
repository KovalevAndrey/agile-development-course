package ru.unn.agile.fraction.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewModelTests {
    protected ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.input1);
        assertEquals("", viewModel.input2);
        assertEquals("", viewModel.result);
    }

    @Test
    public void isAddButtonsDisabledInitially() {
        assertEquals(false, viewModel.isAddButtonEnabled);
    }

    @Test
    public void isSubtractButtonsDisabledInitially() {
        assertEquals(false, viewModel.isSubtractButtonEnabled);
    }

    @Test
    public void isMultiplyButtonsDisabledInitially() {
        assertEquals(false, viewModel.isMultiplyButtonEnabled);
    }

    @Test
    public void isDivideButtonsDisabledInitially() {
        assertEquals(false, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void isCalculateButtonsDisabledWithIncompleteInput() {
        viewModel.input1 = "1";
        viewModel.input2 = "";
        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isAddButtonEnabled);
        assertEquals(false, viewModel.isSubtractButtonEnabled);
        assertEquals(false, viewModel.isMultiplyButtonEnabled);
        assertEquals(false, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void isCalculateButtonsDisabledWhenFormatFirstIsBad() {
        viewModel.input1 = "1/2/3";
        viewModel.input2 = "1/2";
        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isAddButtonEnabled);
        assertEquals(false, viewModel.isSubtractButtonEnabled);
        assertEquals(false, viewModel.isMultiplyButtonEnabled);
        assertEquals(false, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void isCalculateButtonsDisabledWhenFormatSecondIsBad() {
        viewModel.input1 = "1/8";
        viewModel.input2 = "one/two";
        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isAddButtonEnabled);
        assertEquals(false, viewModel.isSubtractButtonEnabled);
        assertEquals(false, viewModel.isMultiplyButtonEnabled);
        assertEquals(false, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void isCalculateButtonsDisabledWhenFormatBothIsBad() {
        viewModel.input1 = "1//3";
        viewModel.input2 = "3//4";
        viewModel.processKeyInTextField();

        assertEquals(false, viewModel.isAddButtonEnabled);
        assertEquals(false, viewModel.isSubtractButtonEnabled);
        assertEquals(false, viewModel.isMultiplyButtonEnabled);
        assertEquals(false, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void isCalculateButtonsEnabledWithCorrectInput() {
        viewModel.input1 = "11/12";
        viewModel.input2 = "13/14";
        viewModel.processKeyInTextField();

        assertEquals(true, viewModel.isAddButtonEnabled);
        assertEquals(true, viewModel.isSubtractButtonEnabled);
        assertEquals(true, viewModel.isMultiplyButtonEnabled);
        assertEquals(true, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void isDivideButtonDisabledWhenSecondIsZero() {
        viewModel.input1 = "4/5";
        viewModel.input2 = "0";
        viewModel.processKeyInTextField();

        assertEquals(true, viewModel.isAddButtonEnabled);
        assertEquals(true, viewModel.isSubtractButtonEnabled);
        assertEquals(true, viewModel.isMultiplyButtonEnabled);
        assertEquals(false, viewModel.isDivideButtonEnabled);
    }

    @Test
    public void canSumOperands() {
        viewModel.input1 = "4/5";
        viewModel.input2 = "1/5";
        viewModel.add();

        assertEquals("1", viewModel.result);
    }

    @Test
    public void canSubtractOperands() {
        viewModel.input1 = "-4/5";
        viewModel.input2 = "1/5";
        viewModel.subtract();

        assertEquals("-1", viewModel.result);
    }

    @Test
    public void canMultiplyOperands() {
        viewModel.input1 = "4/5";
        viewModel.input2 = "2/5";
        viewModel.multiply();

        assertEquals("8/25", viewModel.result);
    }

    @Test
    public void canDivideOperands() {
        viewModel.input1 = "1/7";
        viewModel.input2 = "8/7";
        viewModel.divide();

        assertEquals("1/8", viewModel.result);
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test (expected=IllegalArgumentException.class)
    public void canCreateViewModelWithEmptyLogger() {
        viewModel = new ViewModel(null);
    }

    @Test
    public void isLogEmptyAtStart() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isLogNotEmptyWhenAddAnything() {
        viewModel.input1 = "3/10";
        viewModel.input2 = "1/10";

        viewModel.add();
        viewModel.subtract();
        viewModel.multiply();
        viewModel.divide();

        List<String> log = viewModel.getLog();

        assertEquals(8, log.size());
    }

    @Test
    public void canWriteToLogSumOperands() {
        viewModel.input1 = "3/10";
        viewModel.input2 = "1/10";
        viewModel.add();

        assertEqualsWithData("Trying to add fractions: ", viewModel.getLog().get(0));
        assertEqualsWithData("Calculation successful. Result: ",viewModel.getLog().get(1));
    }

    @Test
    public void canWriteToLogSubtractOperands() {
        viewModel.input1 = "3/10";
        viewModel.input2 = "1/10";
        viewModel.subtract();

        assertEqualsWithData("Trying to subtract fractions: ", viewModel.getLog().get(0));
        assertEqualsWithData("Calculation successful. Result: ",viewModel.getLog().get(1));
    }

    @Test
    public void canWriteToLogMultiplyOperands() {
        viewModel.input1 = "3/10";
        viewModel.input2 = "1/10";
        viewModel.multiply();

        assertEqualsWithData("Trying to multiply fractions: ", viewModel.getLog().get(0));
        assertEqualsWithData("Calculation successful. Result: ",viewModel.getLog().get(1));
    }

    @Test
    public void canWriteToLogDivideOperands() {
        viewModel.input1 = "3/10";
        viewModel.input2 = "1/10";
        viewModel.divide();

        assertEqualsWithData("Trying to divide fractions: ", viewModel.getLog().get(0));
        assertEqualsWithData("Calculation successful. Result: ",viewModel.getLog().get(1));
    }

    private void assertEqualsWithData(String message, String logMessages) {
        Pattern pattern = Pattern.compile(".*" + message + ".*");
        Matcher matcher = pattern.matcher(logMessages);

        assertEquals(true, matcher.matches());
    }
}
