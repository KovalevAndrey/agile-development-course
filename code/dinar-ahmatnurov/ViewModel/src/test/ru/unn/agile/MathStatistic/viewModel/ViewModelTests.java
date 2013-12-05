package ru.unn.agile.MathStatistic.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private final float EPS = 1e-5f;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canDealWithEmptyInput() {
        viewModel.setInputData("");
        viewModel.convertToArrayOfDoubles();
        assertEquals(null, viewModel.getInputArray());
    }

    @Test
    public void canDealWithInputWithLetters() {
        viewModel.setInputData("12 3 a");
        viewModel.convertToArrayOfDoubles();
        assertEquals(null, viewModel.getInputArray());
    }

    @Test
    public void canDealWithInputWithNonLetters() {
        viewModel.setInputData("17.17 -100 ?");
        viewModel.convertToArrayOfDoubles();
        assertEquals(null, viewModel.getInputArray());
    }

    @Test
    public void canDealWithInputWithDuplicatedWhitespace() {
        viewModel.setInputData("  17.17   -100.1  44.4  ");
        viewModel.convertToArrayOfDoubles();
        float[] correctInputArray = {17.17f, -100.1f, 44.4f};
        assertArrayEquals(correctInputArray, viewModel.getInputArray(), EPS);
    }

    @Test
    public void canDealWithOnePositiveNumber() {
        viewModel.setInputData("1.1");
        viewModel.convertToArrayOfDoubles();
        float[] correctInputArray = {1.1f};
        assertArrayEquals(correctInputArray, viewModel.getInputArray(), EPS);
    }

    @Test
    public void canDealWithBatchPositiveNumbers() {
        viewModel.setInputData("1.1 0.2 1.003 100 107.7");
        viewModel.convertToArrayOfDoubles();
        float[] correctInputArray = {1.1f, 0.2f, 1.003f, 100f, 107.7f};
        assertArrayEquals(correctInputArray, viewModel.getInputArray(), EPS);
    }

    @Test
    public void canDealWithOneNegativeNumber() {
        viewModel.setInputData("-1.02");
        viewModel.convertToArrayOfDoubles();
        float[] correctInputArray = {-1.02f};
        assertArrayEquals(correctInputArray, viewModel.getInputArray(), EPS);
    }

    @Test
    public void canDealWithBatchNegativeNumber() {
        viewModel.setInputData("-1.02 -0.01 -200 -10.3 -1");
        viewModel.convertToArrayOfDoubles();
        float[] correctInputArray = {-1.02f, -0.01f, -200f, -10.3f, -1f};
        assertArrayEquals(correctInputArray, viewModel.getInputArray(), EPS);
    }

    @Test
    public void canDealWithMixedNumbers() {
        viewModel.setInputData("-1 1 -2.7 3.1 77.01 17.8 -100 2.1 87");
        viewModel.convertToArrayOfDoubles();
        float[] correctInputArray = {-1f, 1f, -2.7f, 3.1f, 77.01f, 17.8f, -100f, 2.1f, 87f};
        assertArrayEquals(correctInputArray, viewModel.getInputArray(), EPS);
    }

    @Test
    public void isDefaultStateOK() {
        assertEquals(false, viewModel.getCalculateButtonState());
        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
        assertEquals(ViewModel.Statistic.EXPECTED_VALUE, viewModel.getOperation());
    }

    @Test
    public void isBehaviourOkOnGoodData() {
        viewModel.setInputData("-1 1 -2.7 3.1 17.8 -100 2.1 87");
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);
        assertEquals(true, viewModel.getCalculateButtonState());
        assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
        assertNotEquals("", viewModel.getOutputData());
    }

    @Test
    public void isBehaviourOkOnNoData() {
        assertEquals(false, viewModel.getCalculateButtonState());
        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
        assertEquals("", viewModel.getOutputData());
    }

    @Test
    public void isBehaviourOkOnBadData() {
        viewModel.setInputData("-1 1 -2.7 3.1 17.8 qqq -100 2.1 87");
        viewModel.processKeyInTextField(viewModel.ENTER_CODE);
        assertEquals(ViewModel.Status.BAD_INPUT, viewModel.getStatus());
        assertEquals(ViewModel.Statistic.EXPECTED_VALUE, viewModel.getOperation());
    }
}
