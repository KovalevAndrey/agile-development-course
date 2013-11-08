package ru.unn.agile.MergeSort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeSortViewModelTest {

    private String failText = "Invalid input!!!";
    private String successText = "Sort success!!!";
    private MergeSortViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new MergeSortViewModel();
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
}
