package ru.unn.agile.Converter;

public class ViewModelWithTxtLoggerTests extends ViewModelTests
{
    @Override
    public void setUp()
    {
        TxtLogger txtLogger = new TxtLogger("./ViewModelWithTxtLoggerTestLog.log");
        viewModel = new ViewModel(txtLogger);
    }
}
