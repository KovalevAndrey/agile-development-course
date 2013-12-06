package ru.unn.agile.converter.infrastructure;

import ru.unn.agile.converter.viewmodel.ViewModel;
import ru.unn.agile.converter.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(realLogger);
    }
}