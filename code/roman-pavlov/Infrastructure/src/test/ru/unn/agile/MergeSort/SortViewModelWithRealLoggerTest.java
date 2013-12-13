package ru.unn.agile.MergeSort;

public class SortViewModelWithRealLoggerTest extends MergeSortViewModelTest {

    @Override
    public void setUp() {
        viewModel = new MergeSortViewModel(new MergeSortTxtLogger("./SortViewModelWithRealLoggerTest.log"));
    }
}
