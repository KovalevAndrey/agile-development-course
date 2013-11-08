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
        assertEquals(ViewModel.Systems.Decimal, viewModel.inputSys);
        assertEquals(ViewModel.Systems.Decimal, viewModel.outputSys);
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

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
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
        int syst = ViewModel.Systems.Decimal.toInt();
        assertEquals(10, syst);
    }

    @Test
    public void canGetListOfSystems()
    {
        ViewModel.Systems[] systems = ViewModel.Systems.values();
        ViewModel.Systems[] currentSystems = new ViewModel.Systems[]
                {
                    ViewModel.Systems.Binary,
                    ViewModel.Systems.Octal,
                    ViewModel.Systems.Decimal,
                    ViewModel.Systems.Hexadecimal
                };
        assertArrayEquals(currentSystems, systems);
    }

    @Test
    public void canCompareSystemsByName()
    {
        assertEquals(ViewModel.Systems.Decimal, ViewModel.Systems.Decimal);
        assertNotEquals(ViewModel.Systems.Decimal, ViewModel.Systems.Binary);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput()
    {
        viewModel.inputNumber = "10";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canSetBinaryInputSystem()
    {
        viewModel.inputSys = ViewModel.Systems.Binary;
        assertEquals(ViewModel.Systems.Binary, viewModel.inputSys);
    }

    @Test
    public void canSetBinaryOutputSystem()
    {
        viewModel.outputSys = ViewModel.Systems.Binary;
        assertEquals(ViewModel.Systems.Binary, viewModel.outputSys);
    }

    @Test
    public void isDefaultInputSystemDecimal()
    {
        assertEquals(ViewModel.Systems.Decimal, viewModel.inputSys);
    }

    @Test
    public void isDefaultOutputSystemDecimal()
    {
        assertEquals(ViewModel.Systems.Decimal, viewModel.outputSys);
    }

    @Test
    public void canPerformCalcAction()
    {
        viewModel.inputNumber = "3";
        viewModel.inputSys = ViewModel.Systems.Decimal;
        viewModel.outputSys = ViewModel.Systems.Binary;

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
