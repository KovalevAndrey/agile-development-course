package ru.unn.agile.Polynomial.infrastucture;

import ru.unn.agile.Polynomial.viewmodel.ViewModel;
import ru.unn.agile.Polynomial.viewmodel.ViewModelLoggerTest;

public class ViewModelWithTxtLoggerTest extends ViewModelLoggerTest {
    @Override
    public void setUp() {
        TxtLogger logger = new TxtLogger("./log.txt");
        viewModel = new ViewModel(logger);
    }
}
