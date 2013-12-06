package ru.unn.agile.Triangle.infrastucture;

import ru.unn.agile.Triangle.viewmodel.ViewModel;
import ru.unn.agile.Triangle.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests
{
    @Override
    public void setUp()
    {
        TxtLogger logger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new ViewModel(logger);
    }
}
