package ru.unn.agile.TCViewModelTests;

import org.junit.Before;

import ru.unn.agile.TC.viewmodel.ViewModel;

public class ViewModelFakeLoggingTests extends ViewModelCommonLoggingTests {

    @Before
    public void setUp() {
        logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }
}