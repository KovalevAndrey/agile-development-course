package ru.unn.agile.colorConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.colorConverter.model.Converter;

import static org.junit.Assert.*;

public class ViewModelTest {
    public static final int ANY_KEY = 0;
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void terDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("255", viewModel.firstColorFirstValue);
        assertEquals("0", viewModel.firstColorSecondValue);
        assertEquals("0", viewModel.firstColorThirdValue);

        assertEquals("", viewModel.secondColorFirstValue);
        assertEquals("", viewModel.secondColorSecondValue);
        assertEquals("", viewModel.secondColorThirdValue);

        assertEquals(ViewModel.ColorSpace.RGB, viewModel.firstColorSpace);
        assertEquals(ViewModel.ColorSpace.RGB, viewModel.secondColorSpace);
        assertEquals(true, viewModel.isConvertButtonEnabled);

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void convertButtonIsDisabledWhenNoValue() {
        viewModel.firstColorFirstValue = "";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isConvertButtonEnabled);
    }

    @Test
    public void statusIsWaitingDataWhenNoValue() {
        viewModel.firstColorFirstValue = "";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void convertButtonIsDisabledWhenIncorrectValue() {
        viewModel.firstColorFirstValue = "1.0";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isConvertButtonEnabled);
    }

    @Test
    public void statusIsBadFormatWhenNoValue() {
        viewModel.firstColorFirstValue = "1.0";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.status);
    }

    @Test
    public void statusIsReadyDataWhenReady() {
        viewModel.firstColorFirstValue = "0";
        viewModel.firstColorSecondValue = "0";
        viewModel.firstColorThirdValue = "0";
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void statusIsErrorWhenError() {
        viewModel.firstColorFirstValue = "260";
        viewModel.firstColorSpace = ViewModel.ColorSpace.RGB;
        viewModel.convert();

        assertEquals(ViewModel.Status.ERROR_CONVERTING, viewModel.status);
    }

    @Test
    public void statusIsSuccessWhenSuccess() {
        viewModel.firstColorFirstValue = "255";
        viewModel.firstColorSecondValue = "128";
        viewModel.firstColorThirdValue = "0";
        viewModel.firstColorSpace = ViewModel.ColorSpace.RGB;

        viewModel.secondColorSpace = ViewModel.ColorSpace.HSV;
        viewModel.convert();

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void canGetListOfColorSpaces() {
        ViewModel.ColorSpace[] colorSpaces = ViewModel.ColorSpace.values();
        ViewModel.ColorSpace[] currentSpaces = new ViewModel.ColorSpace[] {
            ViewModel.ColorSpace.RGB, ViewModel.ColorSpace.HSV, ViewModel.ColorSpace.LAB};

        assertArrayEquals(currentSpaces, colorSpaces);
    }

    @Test
    public void canCompareColorSpacesByName() {
        assertEquals(ViewModel.ColorSpace.RGB, ViewModel.ColorSpace.RGB);
        assertEquals(ViewModel.ColorSpace.HSV, ViewModel.ColorSpace.HSV);
        assertEquals(ViewModel.ColorSpace.LAB, ViewModel.ColorSpace.LAB);
    }

    @Test
    public void isConvertingByEnter() {
        viewModel.firstColorFirstValue = "255";
        viewModel.firstColorFirstValue = "128";
        viewModel.firstColorFirstValue = "0";
        viewModel.firstColorSpace = ViewModel.ColorSpace.RGB;

        viewModel.secondColorSpace = ViewModel.ColorSpace.HSV;
        viewModel.processKeyInTextField(ViewModel.ENTER_CODE);

        assertEquals(ViewModel.Status.SUCCESS, viewModel.status);
    }

    @Test
    public void convertsRGBtoRGB() {
        viewModel.firstColorFirstValue = "255";
        viewModel.firstColorSecondValue = "128";
        viewModel.firstColorThirdValue = "64";
        viewModel.firstColorSpace = ViewModel.ColorSpace.RGB;

        viewModel.secondColorSpace = ViewModel.ColorSpace.RGB;
        viewModel.convert();

        assertEquals("255", viewModel.secondColorFirstValue);
        assertEquals("128", viewModel.secondColorSecondValue);
        assertEquals("64", viewModel.secondColorThirdValue);
    }

    @Test
    public void convertsHSVtoHSV() {
        viewModel.firstColorFirstValue = "30";
        viewModel.firstColorSecondValue = "70";
        viewModel.firstColorThirdValue = "100";
        viewModel.firstColorSpace = ViewModel.ColorSpace.HSV;

        viewModel.secondColorSpace = ViewModel.ColorSpace.HSV;
        viewModel.convert();

        assertEquals("30", viewModel.secondColorFirstValue);
        assertEquals("70", viewModel.secondColorSecondValue);
        assertEquals("100", viewModel.secondColorThirdValue);
    }

    @Test
    public void convertsLABtoLAB() {
        viewModel.firstColorFirstValue = "5";
        viewModel.firstColorSecondValue = "1";
        viewModel.firstColorThirdValue = "2";
        viewModel.firstColorSpace = ViewModel.ColorSpace.LAB;

        viewModel.secondColorSpace = ViewModel.ColorSpace.LAB;
        viewModel.convert();

        assertEquals("5", viewModel.secondColorFirstValue);
        assertEquals("1", viewModel.secondColorSecondValue);
        assertEquals("2", viewModel.secondColorThirdValue);
    }
}
