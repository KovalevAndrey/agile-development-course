package ru.unn.agile.calculating;

public class ViewModelWithFileLoggerTest extends StringCalcViewModelTest {

    @Override
    public void beforeTest() {
        this.log = new FileLogger("./log_test.log");
        this.viewModel = new StringCalcViewModel(log);
    }
}
