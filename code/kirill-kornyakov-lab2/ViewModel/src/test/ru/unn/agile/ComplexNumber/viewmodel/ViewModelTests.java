package ru.unn.agile.ComplexNumber.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;

import static org.junit.Assert.*;

public class ViewModelTests {
    public static final int ANY_KEY = 0;
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
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
        assertEquals(ViewModel.Operation.ADD, viewModel.operation);
        assertEquals("", viewModel.result);
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        viewModel.re1 = "1";
        viewModel.im1 = "1";
        viewModel.re2 = "3";
        viewModel.im2 = "3";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void canReportBadFormat() {
        viewModel.re1 = "a";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.re1 = "a";
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.re1 = "1.0";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
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
        String addName = ViewModel.Operation.ADD.toString();
        assertEquals("Add", addName);
    }

    @Test
    public void canGetNumberOfOperations() {
        int nOperations = ViewModel.Operation.values().length;
        assertEquals(2, nOperations);
    }

    @Test
    public void canGetListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        ViewModel.Operation[] currentOperations = new ViewModel.Operation[]{
                ViewModel.Operation.ADD,
                ViewModel.Operation.MULTIPLY};

        assertArrayEquals(currentOperations, operations);
    }

    @Test
    public void canCompareOperationsByName() {
        assertEquals(ViewModel.Operation.ADD, ViewModel.Operation.ADD);
        assertNotEquals(ViewModel.Operation.ADD, ViewModel.Operation.MULTIPLY);
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
        viewModel.operation = ViewModel.Operation.ADD;
        assertEquals(ViewModel.Operation.ADD, viewModel.operation);
    }

    @Test
    public void canSetMulOperation() {
        viewModel.operation = ViewModel.Operation.MULTIPLY;
        assertEquals(ViewModel.Operation.MULTIPLY, viewModel.operation);
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
        assertEquals(ViewModel.Operation.ADD, viewModel.operation);
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
        viewModel.operation = ViewModel.Operation.ADD;

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

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.re1 = "a";

        viewModel.calculate();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        viewModel.re1 = "1";
        viewModel.im1 = "0";
        viewModel.re2 = "2";
        viewModel.im2 = "0";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        viewModel.re1 = "1";
        viewModel.im1 = "0";
        viewModel.re2 = "2";
        viewModel.im2 = "0";

        viewModel.processKeyInTextField(ViewModel.ENTER_CODE);

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canMultiplyNumbers() {
        viewModel.re1 = "1";
        viewModel.im1 = "0";
        viewModel.re2 = "2";
        viewModel.im2 = "0";
        viewModel.operation = ViewModel.Operation.MULTIPLY;

        viewModel.calculate();

        assertEquals("2.0 + 0.0i", viewModel.result);
    }

    @Test
    public void canPerformAddWithArbitraryNumbers() {
        viewModel.re1 = "1.2";
        viewModel.im1 = "2.3";
        viewModel.re2 = "-10.4";
        viewModel.im2 = "-20.5";
        viewModel.operation = ViewModel.Operation.ADD;

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.result);
    }
}
