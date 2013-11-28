package ru.unn.agile.fraction.infrastructure;

import ru.unn.agile.fraction.viewmodel.ViewModel;
import ru.unn.agile.fraction.viewmodel.ViewModelTests;

public class ViewModelWithTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TextLogger realLogger = new TextLogger("./ViewModelWithTextLoggerTests.log");
        viewModel = new ViewModel(realLogger);
    }
}
