package ru.unn.agile.converter.viewmodel;

import ru.unn.agile.converter.model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    protected ViewModel viewModel;
    public static final int ANY_KEY = 0;

    @Before
    public void setUp()
    {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown()
    {
        viewModel = null;
    }

    @Test
    public void isCorrectInitialValue()
    {
        assertEquals("", viewModel.getInputValue());
        assertEquals("", viewModel.getOutputValue());
        assertEquals(Unit.meter, viewModel.getInputUnit());
        assertEquals(Unit.meter, viewModel.getOutputUnit());
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNullLogger(){
        viewModel = new ViewModel(null);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void isDefaultInputUnitMeter() {
        assertEquals(Unit.meter, viewModel.getInputUnit());
    }

    @Test
    public void isDefaultOutputUnitMeter() {
        assertEquals(Unit.meter, viewModel.getOutputUnit());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.getIsConvertButtonEnabled());
    }

    @Test
    public void canConvertValue()
    {
        viewModel.setInputValue("100");
        viewModel.setInputUnit(Unit.decimeter);
        viewModel.setOutputUnit(Unit.meter);
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.convert();
        assertEquals("10.0", viewModel.getOutputValue());
    }

    @Test
    public void canSuccessStatus()
    {
        viewModel.setInputValue("0");
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.convert();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canReadyStatus()
    {
        viewModel.setInputValue("1");
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canBadFormatStatus()
    {
        viewModel.setInputValue("d");
        viewModel.convert();
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void  canBadFormatBeforePressButton()
    {
        viewModel.setInputValue("d");
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setInputValue("a");
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.setInputValue("1.0");
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        viewModel.setIsConvertButtonEnabled(true);
        viewModel.setInputValue("trash");
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(false, viewModel.getIsConvertButtonEnabled());
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.setInputValue("1");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.getIsConvertButtonEnabled());
    }

    @Test
    public void canSetInputValueInInch() {
        viewModel.setInputUnit(Unit.inch);
        assertEquals(Unit.inch, viewModel.getInputUnit());
    }

    @Test
    public void canSetOutputValueInMile() {
        viewModel.setInputUnit(Unit.mile);
        assertEquals(Unit.mile, viewModel.getInputUnit());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);
        assertNotNull(viewModelLogged);
    }

    @Test
    public  void isLoggerEmptyInTheBeginning() {

        List<String> log = viewModel.getLog(LogStatus.all);
        assertEquals(0, log.size());
    }

    @Test
    public void isCorrectCreateMessageForConvert() {
        viewModel.setInputValue("100");
        viewModel.setInputUnit(Unit.decimeter);
        viewModel.setOutputUnit(Unit.meter);
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.convert();
        assertEquals(viewModel.createMessageForConvert(), "Doing success convert " +
                "100 decimeter to meter. Result is 10.0 meter.");
    }

    @Test
    public void isCorrectCreateMessageForAdding() {
        viewModel.setInputValue("1");
        assertEquals(viewModel.createMessageForAdding(), "Adding input value: 1.");
    }

    @Test
    public void isCorrectCreateMessageForChangeInputUnit() {
        viewModel.setInputUnit(Unit.centimeter);
        assertEquals(viewModel.createMessageForChangeInputUnit(), "Change input unit: centimeter.");
    }

    @Test
    public void isCorrectCreateMessageForChangeOutputUnit() {
        viewModel.setOutputUnit(Unit.inch);
        assertEquals(viewModel.createMessageForChangeOutputUnit(), "Change output unit: inch.");
    }

    @Test
     public void isCorrectSetInputUnit() {
        viewModel.setInputUnit(Unit.inch);
        assertEquals(viewModel.getInputUnit().name, "inch");
    }

    @Test
    public void isCorrectSetOutputUnit() {
        viewModel.setOutputUnit(Unit.inch);
        assertEquals(viewModel.getOutputUnit().name, "inch");
    }

    @Test
    public void isLogEmptyWhenInputUnitNotChanged() {
        viewModel.setInputUnit(Unit.meter);
        viewModel.setInputUnit(Unit.meter);
        assertEquals(0, viewModel.getLog(LogStatus.all).size());
    }

    @Test
    public void isLogEmptyWhenOutputUnitNotChanged() {
        viewModel.setOutputUnit(Unit.meter);
        viewModel.setOutputUnit(Unit.meter);
        assertEquals(0, viewModel.getLog(LogStatus.all).size());
    }

    @Test
    public void isNotAddingMessageWhenInputValueNotChanged(){
        viewModel.setInputValue("34");
        viewModel.setInputValue("34");
        viewModel.processKeyInTextField(ANY_KEY);
        assertEquals(1, viewModel.getLog(LogStatus.all).size());
    }

    @Test
    public void isLogSizeCorrect() {
        viewModel.setInputValue("100");
        viewModel.setInputUnit(Unit.decimeter);
        viewModel.setOutputUnit(Unit.inch);
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.convert();
        assertEquals(4, viewModel.getLog(LogStatus.all).size());
    }

}
