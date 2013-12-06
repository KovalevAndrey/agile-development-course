package ru.unn.agile.MergeSort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MergeSortViewModelTest {

    private String failText = "Invalid input!!!";
    private String successText = "Sort success!!!";
    protected MergeSortViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new MergeSortViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void fieldsHaveDefaultValuesAfterCreatingForm() {
        assertEquals("", viewModel.statusText);
        assertEquals("", viewModel.resultText);
        assertEquals("", viewModel.arrayText);
    }

    @Test
    public void sortedArrayGivesThatArray() {
        viewModel.arrayText = "1 2 3";
        viewModel.processSort();
        assertEquals(successText, viewModel.statusText);
        assertEquals("1 2 3", viewModel.resultText);
    }

    @Test
    public void emptyStringGivesEmptyString() {
        viewModel.arrayText = "";
        viewModel.processSort();
        assertEquals(successText, viewModel.statusText);
        assertEquals("", viewModel.resultText);
    }

    @Test
    public void invalidInputGivesFailStatus() {
        viewModel.arrayText = "3 asd2 1";
        viewModel.processSort();
        assertEquals(failText, viewModel.statusText);
        assertEquals("", viewModel.resultText);
    }

    @Test
    public void arrayWithOneValueGivesOneValue() {
        viewModel.arrayText = "3";
        viewModel.processSort();
        assertEquals(successText, viewModel.statusText);
        assertEquals("3", viewModel.resultText);
    }

    @Test
    public void canSortArray() {
        viewModel.arrayText = "3 2 1";
        viewModel.processSort();
        assertEquals(successText, viewModel.statusText);
        assertEquals("1 2 3", viewModel.resultText);
    }

    @Test
    public void canSortArrayWithManySpaces() {
        viewModel.arrayText = "1     2      1      1";
        viewModel.processSort();
        assertEquals(successText, viewModel.statusText);
        assertEquals("1 1 1 2", viewModel.resultText);
    }

    @Test
    public void logIs() {
        viewModel.arrayText = "1     2      1      1";
        viewModel.processSort();
        assertEquals(successText, viewModel.statusText);
        assertEquals("1 1 1 2", viewModel.resultText);
    }

    @Test
    public void logIsEmptyAfterCreatingViewModel() {
        assertEquals(0, viewModel.logger.getLog().size());
    }

    @Test
    public void logNotEmptyAfterActions() {
        viewModel.arrayText = " 1 2 45 33 2 ";
        viewModel.processSort();
        assertNotEquals(0, viewModel.logger.getLog().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSetNullLog() {
        viewModel = new MergeSortViewModel(null);
    }

    @Test
    public void processSortAddedToLog() {
        viewModel.arrayText = " 1 2 45 33 2 ";
        viewModel.processSort();
        String mes = viewModel.logger.getLog().get(0);
        assertFindedInString(mes,
                "Processing array:");
    }

    void assertFindedInString(String in, String what){
        int pos = in.indexOf(what);
        assertTrue(pos >= 0);
    }

    @Test
    public void lastMessageIsResultIfArrayValid() {
        viewModel.arrayText = " 4 3 2 1 ";
        viewModel.processSort();
        String mes = viewModel.logger.getLog().get(
                viewModel.logger.getLog().size() - 1);
        assertFindedInString(mes,
                "Result array:");
    }

    @Test
    public void lastMessageIsInvalidIfArrayInvalid() {
        viewModel.arrayText = " @4 23 2() a 1 ";
        viewModel.processSort();
        String mes = viewModel.logger.getLog().get(
                viewModel.logger.getLog().size() - 1);
        assertFindedInString(mes,
                "Array string is invalid");
    }

    @Test
    public void validatingLoggedBeforeValidatingResult() {
        viewModel.arrayText = " @4 23 2() a 1 ";
        viewModel.processSort();
        String mes = viewModel.logger.getLog().get(
                viewModel.logger.getLog().size() - 2);
        assertFindedInString(mes,
                "Array string validating");
    }
}
