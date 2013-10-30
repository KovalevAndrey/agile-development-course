package ru.unn.agile.ComplexNumber.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.unn.agile.ComplexNumber.model.ComplexNumber;

public class ViewModelTests
{
    private ViewModel viewModel;

    @Before
    public void setUp()
    {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown()
    {
        viewModel = null;
    }

    @Test
    public void canConvertStringToComplexNumber()
    {
        String re = "10"; String im = "20";
        ComplexNumber z = viewModel.convertToComplexNumber(re, im);

        assertEquals(new ComplexNumber(10, 20), z);
    }

    @Test
    public void isDefaultOperationAdd()
    {
        assertEquals(ViewModel.Operation.ADD, viewModel.op);
    }

    @Test
    public void canConvertStringToComplexNumberWithFloatingPointNumbers()
    {
        String re = "3.14";
        String im = "-1e3";
        ComplexNumber z = viewModel.convertToComplexNumber(re, im);

        assertEquals(new ComplexNumber(3.14, -1e3), z);
    }

    @Test
    public void canPerformCalcAction()
    {
        viewModel.re1 = "1";   viewModel.im1 = "2";
        viewModel.re2 = "-10"; viewModel.im2 = "-20";
        viewModel.op = ViewModel.Operation.ADD;

        viewModel.calcActionHandler.onClick();

        assertEquals("-9.0 - 18.0i", viewModel.result);
    }

    @Test
    public void canSetSuccessMessage()
    {
        viewModel.re1 = "0"; viewModel.im1 = "0";
        viewModel.re2 = "0"; viewModel.im2 = "0";

        viewModel.calcActionHandler.onClick();

        assertEquals("Success", viewModel.message);
    }

    @Test
    public void canSetBadFormatMessage()
    {
        viewModel.re1 = "a";

        viewModel.calcActionHandler.onClick();

        assertEquals("Bad Format", viewModel.message);
    }

    @Test
    public void isResultNAWhenBadFormat()
    {
        viewModel.re1 = "a";

        viewModel.calcActionHandler.onClick();

        assertEquals("NA", viewModel.result);
    }

    @Test
    public void canMultiplyNumbers()
    {
        viewModel.re1 = "1"; viewModel.im1 = "0";
        viewModel.re2 = "2"; viewModel.im2 = "0";
        viewModel.op = ViewModel.Operation.MULTIPLY;

        viewModel.calcActionHandler.onClick();

        assertEquals("2.0 + 0.0i", viewModel.result);
    }

    @Test
    public void canPerformAddActionWithRealNumbers()
    {
        viewModel.re1 = "1.2";   viewModel.im1 = "2.3";
        viewModel.re2 = "-10.4"; viewModel.im2 = "-20.5";

        viewModel.calcActionHandler.onClick();

        assertEquals("-9.2 - 18.2i", viewModel.result);
    }
}
