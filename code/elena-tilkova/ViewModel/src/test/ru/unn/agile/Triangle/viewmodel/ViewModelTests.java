package ru.unn.agile.Triangle.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Triangle.Triangle;

import static org.junit.Assert.*;

public class ViewModelTests
{
    private ViewModel viewModel;
    public static final int ANY_KEY = 1;
    public static final int ENTER_CODE = 10;
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
    public void isDefaultValuesRight()
    {
        assertEquals("", viewModel.pointA1);
        assertEquals("", viewModel.pointA2);
        assertEquals("", viewModel.pointB1);
        assertEquals("", viewModel.pointB2);
        assertEquals("", viewModel.pointC1);
        assertEquals("", viewModel.pointC2);
        assertEquals(ViewModel.Operation.PERIMETR, viewModel.operation);
        assertEquals("", viewModel.result);
        assertEquals("", viewModel.status);
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonEnabledIfValuesRight()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(1);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(1);
        viewModel.enterKeyInTextField(ANY_KEY);
        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isStatusDataCorrectIfValuesRight()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(1);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(1);
        viewModel.enterKeyInTextField(ANY_KEY);
        assertEquals("Data is correct", viewModel.status);
    }

    @Test
    public void isStatusBadFormatIfValuesNotRight()
    {
        viewModel.pointA1 = "error";
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(1);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(1);
        viewModel.enterKeyInTextField(ANY_KEY);
        assertEquals("Bad format numbers", viewModel.status);
    }

    @Test
    public void isNotCalculateButtonEnabledIfValuesNotRight()
    {
        viewModel.pointA1 = "error";
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(1);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(1);
        viewModel.enterKeyInTextField(ANY_KEY);
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isStatusPointsNotDiffIfValuesAreSame()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(0);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(0);
        viewModel.enterKeyInTextField(ENTER_CODE);
        assertEquals("Points must be different.", viewModel.status);
    }

    @Test
    public void isCalculateButtonEnabledIfValuesAreSame()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(1);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(0);
        viewModel.enterKeyInTextField(ENTER_CODE);
        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isStatusPointsNotCollinearIfValuesAreCollinear()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(1);
        viewModel.pointC1 = Double.toString(2);
        viewModel.pointC2 = Double.toString(2);
        viewModel.enterKeyInTextField(ENTER_CODE);
        assertEquals("Points can't be collinear.", viewModel.status);
    }

    @Test
    public void iisCalculateButtonEnabledIfValuesAreCollinear()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toHexString(1);
        viewModel.pointC1 = Double.toString(2);
        viewModel.pointC2 = Double.toHexString(2);
        viewModel.enterKeyInTextField(ENTER_CODE);
        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canSetPerimetrOperation()
    {
        viewModel.operation = ViewModel.Operation.PERIMETR;
        assertEquals(ViewModel.Operation.PERIMETR, viewModel.operation);
    }

    @Test
    public void canSetSquareOperation()
    {
        viewModel.operation = ViewModel.Operation.SQUARE;
        assertEquals(ViewModel.Operation.SQUARE, viewModel.operation);
    }

    @Test
    public void canSetCircumradiusOperation()
    {
        viewModel.operation = ViewModel.Operation.CIRCUMRADIUS;
        assertEquals(ViewModel.Operation.CIRCUMRADIUS, viewModel.operation);
    }

    @Test
    public void canSetInradiusOperation()
    {
        viewModel.operation = ViewModel.Operation.INRADIUS;
        assertEquals(ViewModel.Operation.INRADIUS, viewModel.operation);
    }

    @Test
    public void canSetAngelAOperation()
    {
        viewModel.operation = ViewModel.Operation.ANGLEA;
        assertEquals(ViewModel.Operation.ANGLEA, viewModel.operation);
    }

    @Test
    public void canSetAngleBOperation()
    {
        viewModel.operation = ViewModel.Operation.ANGLEB;
        assertEquals(ViewModel.Operation.ANGLEB, viewModel.operation);
    }

    @Test
    public void canSetAngleCOperation()
    {
        viewModel.operation = ViewModel.Operation.ANGLEC;
        assertEquals(ViewModel.Operation.ANGLEC, viewModel.operation);
    }

    @Test
    public void canSetSideLengthABOperation()
    {
        viewModel.operation = ViewModel.Operation.SIDELENGTHAB;
        assertEquals(ViewModel.Operation.SIDELENGTHAB, viewModel.operation);
    }

    @Test
    public void canSetSideLengthBCOperation()
    {
        viewModel.operation = ViewModel.Operation.SIDELENGTHBC;
        assertEquals(ViewModel.Operation.SIDELENGTHBC, viewModel.operation);
    }

    @Test
    public void canSetSideLengthACOperation()
    {
        viewModel.operation = ViewModel.Operation.SIDELENGTHAC;
        assertEquals(ViewModel.Operation.SIDELENGTHAC, viewModel.operation);
    }

    @Test
    public void canGetResultPerimetrRightValues()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(3);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(4);
        viewModel.operation = ViewModel.Operation.PERIMETR;
        viewModel.calculate();
        assertEquals(Double.toString(12.0), viewModel.result);
    }

    @Test
    public void canGetResultSquareRightValues()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(3);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(4);
        viewModel.operation = ViewModel.Operation.SQUARE;
        viewModel.calculate();
        assertEquals(Double.toString(6.0), viewModel.result);
    }


    @Test
    public void canGetResultCircumradiusRightValues()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(3);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(4);
        viewModel.operation = ViewModel.Operation.CIRCUMRADIUS;
        viewModel.calculate();
        assertEquals(Double.toString(2.5), viewModel.result);
    }

    @Test
    public void canGetResultSideLengtABhsRightValues()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(10);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(5);
        viewModel.operation = ViewModel.Operation.SIDELENGTHAB;
        viewModel.calculate();
        assertEquals(Double.toString(10.0), viewModel.result);
    }

    @Test
    public void canGetResultAngleAhsRightValues()
    {
        viewModel.pointA1 = Double.toString(0);
        viewModel.pointA2 = Double.toString(0);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(1);
        viewModel.operation = ViewModel.Operation.ANGLEA;
        viewModel.calculate();
        assertEquals(Double.toString(1.5707963267948968), viewModel.result);
    }

    @Test
    public void canGetResultInRadiusRightValues()
    {
        viewModel.pointA1 = Double.toString(0.5);
        viewModel.pointA2 = Double.toString(Math.sqrt(3.0) / 2);
        viewModel.pointB1 = Double.toString(1);
        viewModel.pointB2 = Double.toString(0);
        viewModel.pointC1 = Double.toString(0);
        viewModel.pointC2 = Double.toString(0);
        viewModel.operation = ViewModel.Operation.INRADIUS;
        viewModel.calculate();
        assertEquals(Double.toString(0.288675134594813), viewModel.result);
    }
}

