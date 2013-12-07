package ru.unn.agile.deque.infrastructure;

import ru.unn.agile.deque.viewmodel.*;

public class ViewModelWithTxtDequeLoggerTests extends ViewModelTests{
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(realLogger);
    }
}
