package ru.unn.agile.Polynomial.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {
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
        assertEquals("", viewModel.polynomial1);
        assertEquals("", viewModel.polynomial2);
        assertEquals(Operation.ADD, viewModel.operation);
        assertEquals("", viewModel.result);
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() throws Exception {
        viewModel.calculate();

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() throws Exception {
        viewModel.polynomial1 = "x";
        viewModel.polynomial2 = "2x^3";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void canReportBadFormat() throws Exception {
        viewModel.polynomial1 = "12^45";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusAfterBadFormat() throws Exception {
        viewModel.polynomial1 = "^*%";
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.polynomial1 = "x + 1";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isCalculateButtonIsDisabledByDefault() {
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonIsEnabledWithCorrectInput() throws Exception {
        viewModel.polynomial1 = "x + 1";
        viewModel.polynomial2 = "x^2";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canSetAddOperation() {
        viewModel.operation = Operation.ADD;

        assertEquals(Operation.ADD, viewModel.operation);
    }

    @Test
    public void canSetSubOperation() {
        viewModel.operation = Operation.SUB;

        assertEquals(Operation.SUB, viewModel.operation);
    }

    @Test
    public void canSetMulOperation() {
        viewModel.operation = Operation.MUL;

        assertEquals(Operation.MUL, viewModel.operation);
    }

    @Test
    public void canSetBadFormatMessage() throws Exception {
        viewModel.polynomial1 = "@#$";
        viewModel.calculate();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canPerformAdd() throws Exception {
        viewModel.polynomial1 = "x + 1";
        viewModel.polynomial2 = "x^2 + x";
        viewModel.operation = Operation.ADD;

        viewModel.calculate();

        assertEquals("x^2 + 2x + 1", viewModel.result);
    }

    @Test
    public void canPerformSub() throws Exception {
        viewModel.polynomial1 = "6x + 1";
        viewModel.polynomial2 = "3x^3 + 2";
        viewModel.operation = Operation.SUB;

        viewModel.calculate();

        assertEquals("-3x^3 + 6x + -1", viewModel.result);
    }

    @Test
    public void canPerformMul() throws Exception {
        viewModel.polynomial1 = "x + 1";
        viewModel.polynomial2 = "x + 1";
        viewModel.operation = Operation.MUL;

        viewModel.calculate();

        assertEquals("x^2 + 2x + 1", viewModel.result);
    }

    @Test
    public void canSetSuccessStatus() throws Exception {
        viewModel.polynomial1 = "x + 1";
        viewModel.polynomial2 = "x + 1";
        viewModel.operation = Operation.MUL;

        viewModel.calculate();

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() throws Exception {
        viewModel.polynomial1 = "x + 1";
        viewModel.polynomial2 = "x + 1";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenKeyIsEnter() throws Exception {
        viewModel.polynomial1 = "x + 1";
        viewModel.polynomial2 = "x + 1";

        viewModel.processKeyInTextField(ViewModel.ENTER_CODE);

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

}
