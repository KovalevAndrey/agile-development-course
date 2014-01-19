package ru.unn.agile.geometry;

import ru.unn.agile.geometry.viewModel.LinePlainIntersection;
import ru.unn.agile.geometry.viewModel.ViewModelTests;

public class ViewModelWithTextLoggerTest extends ViewModelTests {
    @Override
    public void setUp() {
        logger = new TextLogger("./ViewModelWithTextLoggerTest.log");
        viewModel = new LinePlainIntersection(logger);
    }
}
