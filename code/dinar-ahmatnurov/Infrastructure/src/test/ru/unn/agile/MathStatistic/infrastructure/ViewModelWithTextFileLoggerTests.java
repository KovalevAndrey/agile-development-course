package ru.unn.agile.MathStatistic.infrastructure;


import ru.unn.agile.MathStatistic.viewModel.ViewModel;
import ru.unn.agile.MathStatistic.viewModel.ViewModelLogsTests;

import java.io.IOException;

public class ViewModelWithTextFileLoggerTests extends ViewModelLogsTests {
    @Override
    public void setUp() {
        TextFileLogger realLogger = null;
        try {
            realLogger = new TextFileLogger("./ViewModelWithTextFileLoggerTests.log");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        viewModel = new ViewModel(realLogger);
    }
}
