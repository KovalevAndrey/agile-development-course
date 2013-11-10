package ru.unn.agile.ComplexNumber.viewmodel;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModel.LogMessages;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModel.Status;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModel.Operation;

import static org.junit.Assert.*;
import static ru.unn.agile.ComplexNumber.viewmodel.RegexMatcher.matchesPattern;

public class ViewModelTests {
    public static final int ANY_KEY = 0;
    private ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.re1);
        assertEquals("", viewModel.im1);
        assertEquals("", viewModel.re2);
        assertEquals("", viewModel.im2);
        assertEquals(Operation.ADD, viewModel.operation);
        assertEquals("", viewModel.result);
        assertEquals(Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        viewModel.re1 = "1";
        viewModel.im1 = "1";
        viewModel.re2 = "3";
        viewModel.im2 = "3";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(Status.READY, viewModel.status);
    }

    @Test
    public void canReportBadFormat() {
        viewModel.re1 = "a";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.re1 = "a";
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.re1 = "1.0";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(Status.WAITING, viewModel.status);
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        viewModel.isCalculateButtonEnabled = true;
        viewModel.re1 = "trash";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.re1 = "1";
        viewModel.im1 = "1";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canGetOperationName() {
        String addName = Operation.ADD.toString();
        assertEquals("Add", addName);
    }

    @Test
    public void canGetNumberOfOperations() {
        int nOperations = Operation.values().length;
        assertEquals(2, nOperations);
    }

    @Test
    public void canGetListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        ViewModel.Operation[] currentOperations = new ViewModel.Operation[]{
                Operation.ADD,
                Operation.MULTIPLY};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareOperationsByName() {
        assertEquals(Operation.ADD, Operation.ADD);
        assertNotEquals(Operation.ADD, Operation.MULTIPLY);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.re1 = "1";
        viewModel.im1 = "1";
        viewModel.re2 = "3";
        viewModel.im2 = "3";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operation = Operation.ADD;
        assertEquals(Operation.ADD, viewModel.operation);
    }

    @Test
    public void canSetMulOperation() {
        viewModel.operation = Operation.MULTIPLY;
        assertEquals(Operation.MULTIPLY, viewModel.operation);
    }

    @Test
    public void canConvertStringToComplexNumber() {
        String re = "10";
        String im = "20";
        ComplexNumber z = viewModel.convertToComplexNumber(re, im);

        assertEquals(new ComplexNumber(10, 20), z);
    }

    @Test
    public void isDefaultOperationAdd() {
        assertEquals(Operation.ADD, viewModel.operation);
    }

    @Test
    public void canConvertScientificStringToComplexNumber() {
        String re = "3.14";
        String im = "-1e3";
        ComplexNumber z = viewModel.convertToComplexNumber(re, im);

        assertEquals(new ComplexNumber(3.14, -1e3), z);
    }

    @Test
    public void canPerformCalcAction() {
        viewModel.re1 = "1";
        viewModel.im1 = "2";
        viewModel.re2 = "-10";
        viewModel.im2 = "-20";
        viewModel.operation = Operation.ADD;

        viewModel.calculate();

        assertEquals("-9.0 - 18.0i", viewModel.result);
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.re1 = "0";
        viewModel.im1 = "0";
        viewModel.re2 = "0";
        viewModel.im2 = "0";

        viewModel.calculate();

        assertEquals(Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.re1 = "a";

        viewModel.calculate();

        assertEquals(Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        viewModel.re1 = "1";
        viewModel.im1 = "0";
        viewModel.re2 = "2";
        viewModel.im2 = "0";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(Status.READY, viewModel.status);
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        viewModel.re1 = "1";
        viewModel.im1 = "0";
        viewModel.re2 = "2";
        viewModel.im2 = "0";

        viewModel.processKeyInTextField(ViewModel.ENTER_CODE);

        assertEquals(Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canMultiplyNumbers() {
        viewModel.re1 = "1";
        viewModel.im1 = "0";
        viewModel.re2 = "2";
        viewModel.im2 = "0";
        viewModel.operation = Operation.MULTIPLY;

        viewModel.calculate();

        assertEquals("2.0 + 0.0i", viewModel.result);
    }

    @Test
    public void canPerformAddWithArbitraryNumbers() {
        viewModel.re1 = "1.2";
        viewModel.im1 = "2.3";
        viewModel.re2 = "-10.4";
        viewModel.im2 = "-20.5";
        viewModel.operation = Operation.ADD;

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.result);
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void isLogEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void isCalculatePuttingSomething() {
        viewModel.calculate();

        List<String> log = viewModel.getLog();
        assertNotEquals(0, log.size());
    }

    @Test
    public void isLogContainsProperMessage() {
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertThat(message, matchesPattern("^" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void isLogContainsInputArguments() {
        viewModel.re1 = "10";
        viewModel.im1 = "11";
        viewModel.re2 = "12";
        viewModel.im2 = "13";

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*" + viewModel.re1
                                         + ".*" + viewModel.im1
                                         + ".*" + viewModel.re2
                                         + ".*" + viewModel.im2 + ".*"
        ));
    }

    @Test
    public void isProperlyFormattingInfoAboutArguments() {
        viewModel.re1 = "21";
        viewModel.im1 = "22";
        viewModel.re2 = "23";
        viewModel.im2 = "24";

        String message = viewModel.prepareLogMessage();

        assertThat(message, matchesPattern(".*With input arguments"
                + ": Re1 = " + viewModel.re1
                + "; Im1 = " + viewModel.im1
                + "; Re2 = " + viewModel.re2
                + "; Im2 = " + viewModel.im2 + ".*"
        ));
    }

    @Test
    public void isOperationMentionedInTheLog() {
        viewModel.operation = Operation.ADD;

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Add.*"));
    }

    @Test
    public void isMulOperationMentionedInTheLog() {
        viewModel.operation = Operation.MULTIPLY;

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertThat(message, matchesPattern(".*Mul.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }
}


