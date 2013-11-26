package ru.unn.agile.converter.viewmodel;

import ru.unn.agile.converter.model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    public static final int ANY_KEY = 0;

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
    public void isCorrectInitialValue()
    {
        assertEquals("", viewModel.inputValue);
        assertEquals("", viewModel.outputValue);
        assertEquals(Unit.meter, viewModel.inputUnit);
        assertEquals(Unit.meter, viewModel.outputUnit);
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isDefaultInputUnitMeter() {
        assertEquals(Unit.meter, viewModel.inputUnit);
    }

    @Test
    public void isDefaultOutputUnitMeter() {
        assertEquals(Unit.meter, viewModel.outputUnit);
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isConvertButtonEnabled);
    }

    @Test
    public void canConvertValue()
    {
        viewModel.inputValue = "100";
        viewModel.inputUnit = Unit.decimeter;
        viewModel.outputUnit = Unit.meter;
        viewModel.convert();
        assertEquals("10.0", viewModel.outputValue);
    }

    @Test
    public void canSuccessStatus()
    {
        viewModel.inputValue = "0";
        viewModel.convert();
        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canReadyStatus()
    {
        viewModel.inputValue = "1";
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void canBadFormatStatus()
    {
        viewModel.inputValue = "d";
        viewModel.convert();
        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void  canBadFormatBeforePressButton()
    {
        viewModel.inputValue = "d";
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.inputValue = "a";
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.inputValue = "1.0";
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        viewModel.isConvertButtonEnabled = true;
        viewModel.inputValue = "trash";
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(false, viewModel.isConvertButtonEnabled);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.inputValue = "1";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.isConvertButtonEnabled);
    }

    @Test
    public void canSetInputValueInInch() {
        viewModel.inputUnit = Unit.inch;
        assertEquals(Unit.inch, viewModel.inputUnit);
    }

    @Test
    public void canSetOutputValueInMile() {
        viewModel.inputUnit = Unit.mile;
        assertEquals(Unit.mile, viewModel.inputUnit);
    }

}
