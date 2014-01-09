package ru.unn.agile.interpolationSearch.infrastructure;


import ru.unn.agile.interpolationSearch.viewmodel.ViewModel;
import ru.unn.agile.interpolationSearch.viewmodel.ViewModelTests;

public class ViewModelWithTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TextLogger realLogger = new TextLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(realLogger);
    }
}