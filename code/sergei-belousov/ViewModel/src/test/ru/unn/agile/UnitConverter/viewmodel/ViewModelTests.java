package ru.unn.agile.UnitConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        ViewModel viewModel = new ViewModel(new FakeLogger());
        assertEquals("", viewModel.fromValueText);
        assertEquals("", viewModel.toValueText);
        assertEquals("", viewModel.fromUnitText);
        assertEquals("", viewModel.toUnitText);
        assertEquals("", viewModel.errorMsg);
        assertEquals("convert", viewModel.actionButtonText);
        assertFalse(viewModel.fromUnitEditable);
        assertFalse(viewModel.toUnitEditable);
        assertFalse(viewModel.toValueEnabled);
        assertFalse(viewModel.addMode);
        assertFalse(viewModel.unitList.isEmpty());
    }

    @Test
    public void canAddConvertPair() {
        viewModel.setAddMode(true);
        viewModel.fromValueText = "1.";
        viewModel.toValueText = "1000.";
        viewModel.fromUnitText = "kg";
        viewModel.toUnitText = "g";
        viewModel.processKey();
        assertEquals("key added.", viewModel.errorMsg);
    }

    @Test
    public void canGetConvertedValue() {
        viewModel.fromUnitText = "g";
        viewModel.toUnitText = "kg";
        viewModel.fromValueText = "1.";
        viewModel.processKey();
        assertEquals(0.001, Double.parseDouble(viewModel.toValueText), 1e-15);
    }

    @Test
    public void canGetErrorMsgForIncorrectInputData() {
        viewModel.fromUnitText = "";
        viewModel.toUnitText = "";
        viewModel.processKey();
        assertEquals("incorrect input data.", viewModel.errorMsg);
    }

    @Test
    public void canGetErrorMsgForIncorrectKey() {
        viewModel.fromUnitText = "t";
        viewModel.toUnitText = "ct";
        viewModel.fromValueText = "1.";
        viewModel.processKey();
        assertEquals("key not found.", viewModel.errorMsg);
    }

    @Test
    public void canGetEmptyErrorMsgForCorrectKey() {
        viewModel.fromUnitText = "kg";
        viewModel.toUnitText = "kg";
        viewModel.fromValueText = "1.";
        viewModel.processKey();
        assertEquals("", viewModel.errorMsg);
    }

    @Test
    public void isAdditionUnitListWhenYouAddPair() {
        assertFalse(viewModel.unitList.isEmpty());
    }

    @Test
    public void canSetAddModeTrue() {
        viewModel.setAddMode(true);
        assertTrue(viewModel.addMode);
        assertTrue(viewModel.fromUnitEditable);
        assertTrue(viewModel.toUnitEditable);
        assertTrue(viewModel.toValueEnabled);
        assertEquals("add", viewModel.actionButtonText);
    }

    @Test
    public void canSetAddModeFalse() {
        viewModel.setAddMode(false);
        assertFalse(viewModel.addMode);
        assertFalse(viewModel.fromUnitEditable);
        assertFalse(viewModel.toUnitEditable);
        assertFalse(viewModel.toValueEnabled);
        assertEquals("convert", viewModel.actionButtonText);
    }

    @Test
    public void viewErrorMsgForNullLogger() {
        try {
            ViewModel viewModel = new ViewModel(null);
        } catch(RuntimeException e) {
            assertEquals(e.getMessage(), "Logger must not be null");
        }
    }

    @Test
    public void correctCreateViewModelWithLogger() {
        try {
            ViewModel viewModel1 = new ViewModel(new FakeLogger());
            assertTrue(true);
        } catch(RuntimeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void canGetLoggerMessages() {
        assertTrue(viewModel.getLog().get(0).contains("ViewModel: start program"));
    }

    @Test
    public void canLoggedModeChange() {
        viewModel.setAddMode(true);
        assertTrue(viewModel.getLog().get(1).contains("setAddMode: add"));
    }

    @Test
    public void canLoggedAddPair() {
        viewModel.setAddMode(true);
        viewModel.fromValueText = "1.";
        viewModel.toValueText = "1000.";
        viewModel.fromUnitText = "kg";
        viewModel.toUnitText = "g";
        viewModel.processKey();
        assertTrue(viewModel.getLog().get(3).contains("addPair"));
    }

    @Test
    public void canLoggedParseInputStatus() {
        viewModel.setAddMode(true);
        viewModel.fromValueText = "1.";
        viewModel.toValueText = "1000.";
        viewModel.fromUnitText = "kg";
        viewModel.toUnitText = "g";
        viewModel.processKey();
        assertTrue(viewModel.getLog().get(2).contains("parseInput"));
    }

    @Test
    public void canLoggedConvertDate() {
        viewModel.setAddMode(false);
        viewModel.fromUnitText = "g";
        viewModel.toUnitText = "kg";
        viewModel.fromValueText = "1.";
        viewModel.processKey();
        assertTrue(viewModel.getLog().get(3).contains("convert"));
    }

}
