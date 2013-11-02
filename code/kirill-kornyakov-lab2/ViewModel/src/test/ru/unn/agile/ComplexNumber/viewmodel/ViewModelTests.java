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
    public void canSetDefaultValues()
    {
        assertEquals("", viewModel.re1);
        assertEquals("", viewModel.im1);
        assertEquals("", viewModel.re2);
        assertEquals("", viewModel.im2);
        assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
        assertEquals("", viewModel.result);
        assertEquals(ViewModel.Statuses.DEFAULT, viewModel.status);
    }

    @Test
    public void isStatusDefaultInTheBeginning()
    {
        assertEquals(ViewModel.Statuses.DEFAULT, viewModel.status);
    }

    @Test
    public void canReportBadFormat()
    {
        viewModel.re1 = "a";
        viewModel.parseInputFields();

        assertEquals(ViewModel.Statuses.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK()
    {
        viewModel.re1 = "a";
        viewModel.parseInputFields();
        viewModel.re1 = "1.0";
        viewModel.parseInputFields();

        assertEquals(ViewModel.Statuses.DEFAULT, viewModel.status);
    }

    @Test
    public void canSetOperationFromString()
    {
        viewModel.setOperation("Add");
        assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
    }

    @Test
    public void canSetMulOperationFromString()
    {
        viewModel.setOperation("Mul");
        assertEquals(ViewModel.Operation.MULTIPLY, viewModel.getOperation());
    }

    @Test (expected=IllegalArgumentException.class)
    public void testNullParameter() throws IllegalArgumentException {
        viewModel.setOperation("Wrong string");
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
        assertEquals(ViewModel.Operation.ADD, viewModel.getOperation());
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
        viewModel.setOperation("Add");

        viewModel.calculate();

        assertEquals("-9.0 - 18.0i", viewModel.result);
    }

    @Test
    public void canSetSuccessMessage()
    {
        viewModel.re1 = "0"; viewModel.im1 = "0";
        viewModel.re2 = "0"; viewModel.im2 = "0";

        viewModel.calculate();

        assertEquals(ViewModel.Statuses.SUCCESS, viewModel.status);
    }

    @Test
    public void canSetBadFormatMessage()
    {
        viewModel.re1 = "a";

        viewModel.calculate();

        assertEquals(ViewModel.Statuses.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void isResultNAWhenBadFormat()
    {
        viewModel.re1 = "a";

        viewModel.calculate();

        assertEquals("NA", viewModel.result);
    }

    @Test
    public void canMultiplyNumbers()
    {
        viewModel.re1 = "1"; viewModel.im1 = "0";
        viewModel.re2 = "2"; viewModel.im2 = "0";
        viewModel.setOperation("Mul");

        viewModel.calculate();

        assertEquals("2.0 + 0.0i", viewModel.result);
    }

    @Test
    public void canPerformAddActionWithRealNumbers()
    {
        viewModel.re1 = "1.2";   viewModel.im1 = "2.3";
        viewModel.re2 = "-10.4"; viewModel.im2 = "-20.5";

        viewModel.calculate();

        assertEquals("-9.2 - 18.2i", viewModel.result);
    }
}
