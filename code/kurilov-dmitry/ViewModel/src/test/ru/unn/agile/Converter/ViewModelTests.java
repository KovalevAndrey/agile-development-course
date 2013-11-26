package ru.unn.agile.Converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests
{
    public static final int ANY_KEY = 0;
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
        assertEquals("", viewModel.inputNumber);
        assertEquals(ViewModel.NumeralSystems.Binary, viewModel.inputSys);
        assertEquals(ViewModel.NumeralSystems.Binary, viewModel.outputSys);
        assertEquals("", viewModel.result);
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingInTheBeginning()
    {
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields()
    {
        viewModel.calculate();
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill()
    {
        viewModel.inputNumber = "10";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void canReportBadFormat()
    {
        viewModel.inputNumber = "gt";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK()
    {
        viewModel.inputNumber = "gt";
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.inputNumber = "10";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isCalculateButtonDisabledInitially()
    {
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad()
    {
        viewModel.isCalculateButtonEnabled = true;
        viewModel.inputNumber = "gt";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canGetSystemNumber()
    {
        int system = ViewModel.NumeralSystems.Decimal.toInt();
        assertEquals(10, system);
    }

    @Test
    public void canGetListOfSystems()
    {
        ViewModel.NumeralSystems[] systems = ViewModel.NumeralSystems.values();
        ViewModel.NumeralSystems[] currentSystems = new ViewModel.NumeralSystems[]
                {
                    ViewModel.NumeralSystems.Binary,
                    ViewModel.NumeralSystems.Octal,
                    ViewModel.NumeralSystems.Decimal,
                    ViewModel.NumeralSystems.Hexadecimal
                };
        assertArrayEquals(currentSystems, systems);
    }

    @Test
    public void canCompareSystemsByName()
    {
        assertEquals(ViewModel.NumeralSystems.Decimal, ViewModel.NumeralSystems.Decimal);
        assertNotEquals(ViewModel.NumeralSystems.Decimal, ViewModel.NumeralSystems.Binary);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput()
    {
        viewModel.inputNumber = "10";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canSetDecimalInputSystem()
    {
        viewModel.inputSys = ViewModel.NumeralSystems.Decimal;
        assertEquals(ViewModel.NumeralSystems.Decimal, viewModel.inputSys);
    }

    @Test
    public void canSetDecimalOutputSystem()
    {
        viewModel.outputSys = ViewModel.NumeralSystems.Decimal;
        assertEquals(ViewModel.NumeralSystems.Decimal, viewModel.outputSys);
    }

    @Test
    public void isDefaultInputSystemBinary()
    {
        assertEquals(ViewModel.NumeralSystems.Binary, viewModel.inputSys);
    }

    @Test
    public void isDefaultOutputSystemBinary()
    {
        assertEquals(ViewModel.NumeralSystems.Binary, viewModel.outputSys);
    }

    @Test
    public void canPerformCalcAction()
    {
        viewModel.inputNumber = "3";
        viewModel.inputSys = ViewModel.NumeralSystems.Decimal;
        viewModel.outputSys = ViewModel.NumeralSystems.Binary;

        viewModel.calculate();

        assertEquals("11", viewModel.result);
    }

    @Test
    public void canSetBadFormatMessage()
    {
        viewModel.inputNumber = "gt";
        viewModel.calculate();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }
}
