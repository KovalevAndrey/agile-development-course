package ru.unn.agile.VolumeCalculator.Infrastructure;

import ru.unn.agile.VolumeCalculator.viewmodel.VolumeCalculatorViewModel;
import ru.unn.agile.VolumeCalculator.viewmodel.VolumeCalculatorViewModelTest;

public class ViewModelWithTxtLoggerTests extends VolumeCalculatorViewModelTest {
    @Override
    public void beforeTest() {
        TxtLogger txtLogger = new TxtLogger("./VolumeCalculatorViewModelWithTxtLoggerTestLog.log");
        viewModel=new VolumeCalculatorViewModel(txtLogger);
    }
}
