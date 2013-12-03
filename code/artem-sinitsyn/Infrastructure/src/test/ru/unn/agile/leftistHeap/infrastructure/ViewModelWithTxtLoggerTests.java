package ru.unn.agile.leftistHeap.infrastructure;

import ru.unn.agile.leftistHeap.viewModel.ViewModel;
import ru.unn.agile.leftistHeap.viewModel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger txtLogger = new TxtLogger("./ViewModelWithTxtLoggerTestLog.log");
        viewModel = new ViewModel(txtLogger);
    }
}
