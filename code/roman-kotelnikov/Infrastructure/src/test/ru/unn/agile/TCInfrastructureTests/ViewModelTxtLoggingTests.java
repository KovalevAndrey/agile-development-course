package ru.unn.agile.TCInfrastructureTests;

import org.junit.Before;
import ru.unn.agile.TC.infrastructure.TxtLogger;
import ru.unn.agile.TC.viewmodel.ViewModel;
import ru.unn.agile.TCViewModelTests.ViewModelCommonLoggingTests;

import java.io.IOException;

public class ViewModelTxtLoggingTests extends ViewModelCommonLoggingTests {

    @Before
    public void setUp() throws IOException {
        logger = new TxtLogger("TxtLoggerViewModelTestLog.txt");
        viewModel = new ViewModel(logger);
    }
}
