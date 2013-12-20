package ru.unn.agile.qsolver.infrastructure;

import ru.unn.agile.QSolverViewModel.ILogger;
import ru.unn.agile.QSolverViewModel.QSolverViewModel;
import ru.unn.agile.QSolverViewModel.QSolverViewModelTest;

public class ViewModelWithRealLoggerTest extends QSolverViewModelTest {

    @Override
    public void setUp() {
        ILogger logger = new FileLogger("./viewModelTest.log");
        viewModel = new QSolverViewModel(logger);
    }
}
