package ru.unn.agile.MathStatistic.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MathStatistic.model.MathStatistic;

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
    public void canDealWithEmptyInput(){
        float[] inputArray = viewModel.convertToArrayOfDoubles("");
        assertEquals(null, inputArray);
    }

    @Test
    public void canDealWithInputWithLetters() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("12 3 a");
        assertEquals(null, inputArray);
    }

    @Test
    public void canDealWithInputWithNonLetters() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("17.17 -100 ?");
        assertEquals(null, inputArray);
    }

    @Test
    public void canDealWithInputWithDuplicatedWhitespace() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("  17.17   -100.1  44.4  ");
        float[]  correctInputArray = {17.17f, -100.1f, 44.4f};
        assertArrayEquals(correctInputArray, inputArray, EPS);
    }

    @Test
    public void canDealWithOnePositiveNumber() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("1.1");
        float[]  correctInputArray = {1.1f};
        assertArrayEquals(correctInputArray, inputArray, EPS);
    }

    @Test
    public void canDealWithBatchPositiveNumbers() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("1.1 0.2 1.003 100 107.7");
        float[]  correctInputArray = {1.1f, 0.2f, 1.003f, 100f, 107.7f};
        assertArrayEquals(correctInputArray, inputArray, EPS);
    }

    @Test
    public void canDealWithOneNegativeNumber() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("-1.02");
        float[]  correctInputArray = {-1.02f};
        assertArrayEquals(correctInputArray, inputArray, EPS);
    }

    @Test
    public void canDealWithBatchNegativeNumber() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("-1.02 -0.01 -200 -10.3 -1");
        float[]  correctInputArray = {-1.02f, -0.01f, -200f, -10.3f, -1f};
        assertArrayEquals(correctInputArray, inputArray, EPS);
    }

    @Test
    public void canDealWithMixedNumbers() {
        float[] inputArray = viewModel.convertToArrayOfDoubles("-1 1 -2.7 3.1 77.01 17.8 -100 2.1 87");
        float[]  correctInputArray = {-1f, 1f, -2.7f, 3.1f, 77.01f, 17.8f, -100f, 2.1f, 87f};
        assertArrayEquals(correctInputArray, inputArray, EPS);
    }
}
