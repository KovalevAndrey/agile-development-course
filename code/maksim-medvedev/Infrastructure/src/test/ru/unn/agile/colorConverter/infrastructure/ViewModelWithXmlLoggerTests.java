package ru.unn.agile.colorConverter.infrastructure;

import ru.unn.agile.colorConverter.viewmodel.ViewModel;
import ru.unn.agile.colorConverter.viewmodel.ViewModelTest;

public class ViewModelWithXmlLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        XmlLogger logger = null;
        try {
            logger = new XmlLogger("./ViewModelWithTxtLoggerTests.log");
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewModel = new ViewModel(logger);
    }
}