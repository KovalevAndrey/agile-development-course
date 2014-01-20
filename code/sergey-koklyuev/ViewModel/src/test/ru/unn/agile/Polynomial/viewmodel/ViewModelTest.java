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
        ILogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getPolynomial1());
        assertEquals("", viewModel.getPolynomial2());
        assertEquals(Operation.ADD, viewModel.getOperation());
        assertEquals("", viewModel.getResult());
        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() throws Exception {
        viewModel.calculate();

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() throws Exception {
        viewModel.setPolynomial1("x");
        viewModel.setPolynomial2("2x^3");

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() throws Exception {
        viewModel.setPolynomial1("12^45");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusAfterBadFormat() throws Exception {
        viewModel.setPolynomial1("^*%");
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.setPolynomial1("x + 1");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonIsDisabledByDefault() {
        assertEquals(false, viewModel.getIsCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonIsEnabledWithCorrectInput() throws Exception {
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x^2");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.getIsCalculateButtonEnabled());
    }

    @Test
    public void canSetAddOperation() {
        viewModel.setOperation(Operation.ADD);

        assertEquals(Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void canSetSubOperation() {
        viewModel.setOperation(Operation.SUB);

        assertEquals(Operation.SUB, viewModel.getOperation());
    }

    @Test
    public void canSetMulOperation() {
        viewModel.setOperation(Operation.MUL);

        assertEquals(Operation.MUL, viewModel.getOperation());
    }

    @Test
    public void canSetBadFormatMessage() throws Exception {
        viewModel.setPolynomial1("@#$");
        viewModel.calculate();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canPerformAdd() throws Exception {
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x^2 + x");
        viewModel.setOperation(Operation.ADD);

        viewModel.calculate();

        assertEquals("x^2 + 2x + 1", viewModel.getResult());
    }

    @Test
    public void canPerformSub() throws Exception {
        viewModel.setPolynomial1("6x + 1");
        viewModel.setPolynomial2("3x^3 + 2");
        viewModel.setOperation(Operation.SUB);

        viewModel.calculate();

        assertEquals("-3x^3 + 6x + -1", viewModel.getResult());
    }

    @Test
    public void canPerformMul() throws Exception {
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x + 1");
        viewModel.setOperation(Operation.MUL);

        viewModel.calculate();

        assertEquals("x^2 + 2x + 1", viewModel.getResult());
    }

    @Test
    public void canSetSuccessStatus() throws Exception {
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x + 1");
        viewModel.setOperation(Operation.MUL);

        viewModel.calculate();

        assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() throws Exception {
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x + 1");

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsEnter() throws Exception {
        viewModel.setPolynomial1("x + 1");
        viewModel.setPolynomial2("x + 1");

        viewModel.processKeyInTextField(KeyboardKeys.ENTER);

        assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
    }

}
