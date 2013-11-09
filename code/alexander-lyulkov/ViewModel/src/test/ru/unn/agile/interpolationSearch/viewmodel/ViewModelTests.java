package ru.unn.agile.interpolationSearch.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ViewModelTests {
    public static final int ANY_KEY = 0;
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.key);
        assertEquals("", viewModel.listOfElements);
        assertEquals("", viewModel.keyIndex);
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
        assertEquals(false, viewModel.isSearchButtonEnabled);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        viewModel.listOfElements = "1 2 3";
        viewModel.key = "1";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isStatusUndortedWhenListIsUnsorted() {
        viewModel.listOfElements = "1 3 2";
        viewModel.key = "5";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.UNSORTED, viewModel.status);
    }


    @Test
    public void canReportBadFormat() {
        viewModel.listOfElements = "qwewq";
        viewModel.key = "5";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.listOfElements = "a";
        viewModel.processKeyInTextField(ANY_KEY);
        viewModel.listOfElements = "-3 -2";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }


    @Test
    public void isSearchButtonDisabledWhenFormatIsBad() {
        viewModel.isSearchButtonEnabled = true;
        viewModel.listOfElements = "no numbers";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isSearchButtonEnabled);
    }

    @Test
    public void isSearchButtonDisabledWithIncompleteInput() {
        viewModel.key = "15";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isSearchButtonEnabled);
    }

    @Test
    public void isSearchButtonEnabledWithCorrectInput() {
        viewModel.listOfElements = "1 5 9 14";
        viewModel.key = "1";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, viewModel.isSearchButtonEnabled);
    }


    @Test
    public void canConvertStringToArrayOfNumbers() {
        String string = "1 4 5 8 9";

        int[] arrayOfNumbers = viewModel.ConvertStringToIntArray(string);

        assertArrayEquals(new int[] {1,4,5,8,9}, arrayOfNumbers);
    }

    @Test
    public void canConvertStringWithNegativeNumberToArrayOfNumbers() {
        String string = "1 3 -1 4";

        int[] arrayOfNumbers = viewModel.ConvertStringToIntArray(string);

        assertArrayEquals(new int[] {1,3,-1,4}, arrayOfNumbers);
    }

    @Test
    public void canConvertStringToOneNumber() {
        String string = "7";

        int[] arrayOfNumbers = viewModel.ConvertStringToIntArray(string);

        assertArrayEquals(new int[] {7}, arrayOfNumbers);
    }

    @Test
    public void canPerformSearchAction() {
        viewModel.listOfElements = "7 8 9 10";
        viewModel.key = "8";
        viewModel.calculate();

        assertEquals("1", viewModel.keyIndex);
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.listOfElements = "1";
        viewModel.key = "1";
        viewModel.calculate();

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.listOfElements = "oigoidfjgoi";
        viewModel.key = "qweq";

        viewModel.calculate();

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        viewModel.listOfElements = " -1 0";
        viewModel.key = "5";

        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        viewModel.listOfElements = " -1 20 30";
        viewModel.key = "-1";

        viewModel.processKeyInTextField(ViewModel.ENTER_CODE);

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

}
