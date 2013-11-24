package ru.unn.agile.ComplexNumber.infrastructure;

import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(realLogger);
    }
}
