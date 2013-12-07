package ru.unn.agile.colorConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
    public static final int ANY_KEY = 0;
    protected ViewModel viewModel;

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void terDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("255", viewModel.getFirstColorFirstValue());
        assertEquals("0", viewModel.getFirstColorSecondValue());
        assertEquals("0", viewModel.getFirstColorThirdValue());

        assertEquals("", viewModel.getSecondColorFirstValue());
        assertEquals("", viewModel.getSecondColorSecondValue());
        assertEquals("", viewModel.getSecondColorThirdValue());

        assertEquals(ViewModel.ColorSpace.RGB, viewModel.getFirstColorSpace());
        assertEquals(ViewModel.ColorSpace.RGB, viewModel.getSecondColorSpace());
        assertEquals(true, viewModel.isConvertButtonEnabled());

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void convertButtonIsDisabledWhenNoValue() {
        viewModel.setFirstColorFirstValue("");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void statusIsWaitingDataWhenNoValue() {
        viewModel.setFirstColorFirstValue("");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void convertButtonIsDisabledWhenIncorrectValue() {
        viewModel.setFirstColorFirstValue("1.0");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, viewModel.isConvertButtonEnabled());
    }

    @Test
    public void statusIsBadFormatWhenNoValue() {
        viewModel.setFirstColorFirstValue("1.0");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void statusIsReadyDataWhenReady() {
        viewModel.setFirstColorFirstValue("0");
        viewModel.setFirstColorSecondValue("0");
        viewModel.setFirstColorThirdValue("0");
        viewModel.processKeyInTextField(ANY_KEY);

        assertEquals(ViewModel.Status.READY, viewModel.getStatus());
    }

    @Test
    public void statusIsErrorWhenError() {
        viewModel.setFirstColorFirstValue("260");
        viewModel.setFirstColorSpace(ViewModel.ColorSpace.RGB);
        viewModel.convert();

        assertEquals(ViewModel.Status.ERROR_CONVERTING, viewModel.getStatus());
    }

    @Test
    public void statusIsSuccessWhenSuccess() {
        viewModel.setFirstColorFirstValue("255");
        viewModel.setFirstColorSecondValue("128");
        viewModel.setFirstColorThirdValue("0");

        viewModel.setFirstColorSpace(ViewModel.ColorSpace.RGB);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.HSV);

        viewModel.convert();

        assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
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
        viewModel.setFirstColorFirstValue("255");
        viewModel.setFirstColorSecondValue("128");
        viewModel.setFirstColorThirdValue("0");

        viewModel.setFirstColorSpace(ViewModel.ColorSpace.RGB);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.HSV);
        viewModel.processKeyInTextField(ViewModel.ENTER_CODE);

        assertEquals(ViewModel.Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void convertsRGBtoRGB() {
        viewModel.setFirstColorFirstValue("255");
        viewModel.setFirstColorSecondValue("128");
        viewModel.setFirstColorThirdValue("64");

        viewModel.setFirstColorSpace(ViewModel.ColorSpace.RGB);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.RGB);

        viewModel.convert();

        assertEquals("255", viewModel.getSecondColorFirstValue());
        assertEquals("128", viewModel.getSecondColorSecondValue());
        assertEquals("64", viewModel.getSecondColorThirdValue());
    }

    @Test
    public void convertsHSVtoHSV() {
        viewModel.setFirstColorFirstValue("30");
        viewModel.setFirstColorSecondValue("70");
        viewModel.setFirstColorThirdValue("100");

        viewModel.setFirstColorSpace(ViewModel.ColorSpace.HSV);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.HSV);
        viewModel.convert();

        assertEquals("30", viewModel.getSecondColorFirstValue());
        assertEquals("70", viewModel.getSecondColorSecondValue());
        assertEquals("100", viewModel.getSecondColorThirdValue());
    }

    @Test
    public void convertsLABtoLAB() {
        viewModel.setFirstColorFirstValue("5");
        viewModel.setFirstColorSecondValue("1");
        viewModel.setFirstColorThirdValue("2");

        viewModel.setFirstColorSpace(ViewModel.ColorSpace.LAB);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.LAB);
        viewModel.convert();

        assertEquals("5", viewModel.getSecondColorFirstValue());
        assertEquals("1", viewModel.getSecondColorSecondValue());
        assertEquals("2", viewModel.getSecondColorThirdValue());
    }

    private String getLastLogMessage() throws Exception {
        List<String> log = viewModel.getLog();
        if (log.size() == 0)
            throw new Exception("Log is empty");

        return log.get(log.size() - 1);
    }

    @Test
    public void changingValuesIsBeingLogged() {
        int size = viewModel.getLog().size();

        viewModel.setFirstColorFirstValue("5");
        viewModel.setFirstColorSecondValue("3");
        viewModel.setFirstColorThirdValue("7");

        assertEquals(viewModel.getLog().size(), size + 3);
    }

    @Test
    public void changingColorSpaceIsBeingLogged() {
        int size = viewModel.getLog().size();
        viewModel.setFirstColorSpace(ViewModel.ColorSpace.HSV);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.LAB);

        assertEquals(viewModel.getLog().size(), size + 2);
    }

    @Test
    public void convertingIsBeingLogged() {
        int size = viewModel.getLog().size();
        viewModel.convert();

        assertEquals(viewModel.getLog().size(), size + 1);
    }

    @Test
    public void changingValuesCorrectlyLogged() throws Exception {
        viewModel.setFirstColorFirstValue("5");

        assert(getLastLogMessage().startsWith(ViewModel.LogMessage.ValueChanged));
    }

    @Test
    public void changingColorSpaceCorrectlyLogged() throws Exception {
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.LAB);

        assert(getLastLogMessage().startsWith(ViewModel.LogMessage.ColorSpaceChanged));
    }

    @Test
    public void convertingCorrectlyLogged() throws Exception {
        viewModel.convert();

        assert(getLastLogMessage().startsWith(ViewModel.LogMessage.ConvertClicked));
    }

    @Test
    public void valuesAreCorrectlyLogged() throws Exception {
        viewModel.setFirstColorFirstValue("5");
        viewModel.setFirstColorSecondValue("3");
        viewModel.setFirstColorThirdValue("7");

        viewModel.setFirstColorSpace(ViewModel.ColorSpace.RGB);
        viewModel.setSecondColorSpace(ViewModel.ColorSpace.HSV);

        String logMsg = ViewModel.LogMessage.ColorSpaceChanged + " Values are: [5, 3, 7]; RGB -> HSV";

        assertEquals(getLastLogMessage(), logMsg);
    }
}
