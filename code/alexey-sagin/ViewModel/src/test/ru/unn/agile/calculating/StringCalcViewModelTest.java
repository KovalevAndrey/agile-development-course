package ru.unn.agile.calculating;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalcViewModelTest {

    protected StringCalcViewModel viewModel;
    protected ILogger log;

    @Before
    public void beforeTest() {
        log = new FakeLogger();
        viewModel = new StringCalcViewModel(log);
    }

    @After
    public void afterTest() {
        viewModel = null;
        log = null;
    }

    @Test
    public void fieldsHaveDefaultValuesAfterCreating() {
        assertEquals("Type expression and click \"Calculate\"",
                viewModel.status);
        assertEquals("", viewModel.expression);
        assertEquals("", viewModel.result);
    }

    @Test
    public void canCalculateExpr() {
        viewModel.expression = "1 + 2 + 4";
        viewModel.processCalculate();
        assertEquals("7.0", viewModel.result);
    }

    @Test
    public void canCalculateExprWithParens() {
        viewModel.expression = "1 - (2 + 4)/2";
        viewModel.processCalculate();
        assertEquals("-2.0", viewModel.result);
    }

    @Test
    public void lettersInExprGivesNonSuccessStatusAndEmpryResult() {
        viewModel.expression = "1 + Af 2 + 4";
        assertExprCalculatingGivesError();
    }

    @Test
    public void wrongParensInExprGivesNonSuccessStatusAndEmpryResult() {
        viewModel.expression = "1 + ( 2 + 4";
        assertExprCalculatingGivesError();
    }

    @Test
    public void wrongOpsInExprGivesNonSuccessStatusAndEmpryResult() {
        viewModel.expression = "1 + + 2 + 4";
        assertExprCalculatingGivesError();
    }

    private void assertExprCalculatingGivesError() {
        viewModel.processCalculate();
        assertEquals("", viewModel.result);
        assertEquals(false, viewModel.status.equals("Success"));
    }

    @Test(expected = RuntimeException.class)
    public void logCannotBeNull() {
        viewModel = new StringCalcViewModel(null);
    }

    @Test
    public void logIsEmptyAfterCreating() {
        assertEquals(0, viewModel.getLog().getAllMessages().size());
    }

    @Test
    public void logIsNotEmptyAfterCalculating() {
        viewModel.expression = "2+2";
        viewModel.processCalculate();
        assertEquals(false, 0 == viewModel.getLog().getAllMessages().size());
    }

    @Test
    public void exceptionsIsLoggedWhenCalculating() {
        viewModel.expression = "..:???#@W#$%^";
        viewModel.processCalculate();
        boolean ok = viewModel.getLog().getLastMessage().indexOf("Exception occurred: ") >= 0;
        assertEquals(true, ok);
    }

    @Test
    public void actionsLoggedCorrectly() {
        viewModel.expression = "2 + 1 / 2";
        viewModel.processCalculate();
        boolean ok = viewModel.getLog().getLastMessage().indexOf(
                "\"Calculate\" button was pressed with expr 2 + 1 / 2") >= 0;
        assertEquals(true, ok);
    }
}
