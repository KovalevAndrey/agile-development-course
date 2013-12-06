package ru.unn.agile.queue;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(realLogger);
        queue = new Queue();
    }
}
