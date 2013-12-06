package ru.unn.agile.determinant.infrastructure;

import ru.unn.agile.determinant.viewmodel.DeterminantViewModel;
import ru.unn.agile.determinant.viewmodel.DeterminantViewModelTest;

public class ViewModelWithTxtLoggerTests extends DeterminantViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        viewModel = new DeterminantViewModel(realLogger);
    }
}
