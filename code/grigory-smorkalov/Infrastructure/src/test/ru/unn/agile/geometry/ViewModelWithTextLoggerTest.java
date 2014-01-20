package ru.unn.agile.geometry;

import ru.unn.agile.geometry.viewModel.LinePlaneIntersection;
import ru.unn.agile.geometry.viewModel.ViewModelTests;

public class ViewModelWithTextLoggerTest extends ViewModelTests {
    @Override
    public void setUp() {
        logger = new TextLogger("./ViewModelWithTextLoggerTest.log");
        viewModel = new LinePlaneIntersection(logger);
    }
}
