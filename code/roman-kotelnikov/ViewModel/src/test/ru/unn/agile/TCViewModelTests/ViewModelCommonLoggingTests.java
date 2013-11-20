package ru.unn.agile.TCViewModelTests;

import org.junit.Assert;
import org.junit.Test;
import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.ViewModel;

import static ru.unn.agile.TC.AvailableScales.Fahrenheit;
import static ru.unn.agile.TC.AvailableScales.Romer;

public abstract class ViewModelCommonLoggingTests {
    protected ViewModel viewModel;

    @Test
    public void ensureTempValueLoggedOnChange() {
        viewModel.input = "36.5";

        checkUserInputsLogging();
    }

    @Test
    public void ensureInputScaleLoggedOnChange() {
        viewModel.inputScale = Fahrenheit;

        checkUserInputsLogging();
    }

    @Test
    public void ensureResultScaleLoggedOnChange() {
        viewModel.resultScale = Romer;

        checkUserInputsLogging();
    }

    @Test
    public void ensureConversionResultLogged() {
        viewModel.input = "36.5";

        viewModel.convert();

        checkStatusLogging(ILogger.Messages.LOG_VIEW_MODEL_OK);
    }

    public void checkUserInputsLogging() {
        viewModel.inputFocusLost();

        String message = String.format(ILogger.Messages.LOG_INPUT_MESSAGE,
                viewModel.input,
                viewModel.inputScale,
                viewModel.resultScale);

        Assert.assertTrue(viewModel.getLogger().getLastMessage().contains(message));
    }

    public void checkStatusLogging(String message) {
        String loggerLastMessage = viewModel.getLogger().getLastMessage();
        Assert.assertTrue(loggerLastMessage.contains(message));
    }
}
