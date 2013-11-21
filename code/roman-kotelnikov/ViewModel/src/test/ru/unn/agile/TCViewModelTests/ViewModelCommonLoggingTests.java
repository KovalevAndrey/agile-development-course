package ru.unn.agile.TCViewModelTests;

import org.junit.Test;
import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.LogFormat;
import ru.unn.agile.TC.viewmodel.LogMessage;
import ru.unn.agile.TC.viewmodel.ViewModel;

import static org.junit.Assert.assertTrue;
import static ru.unn.agile.TC.AvailableScales.Fahrenheit;
import static ru.unn.agile.TC.AvailableScales.Romer;
import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.*;

public abstract class ViewModelCommonLoggingTests {
    protected ViewModel viewModel;
    protected ILogger logger;

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

        checkLogLastMessage(new LogMessage(LOG_INFO_VM_OK));
    }

    public void checkUserInputsLogging() {
        viewModel.inputParametersChanged();

        LogMessage message = new LogMessage(LOG_INFO_INPUT,
                viewModel.input,
                viewModel.inputScale,
                viewModel.resultScale);

        checkLogLastMessage(message);
    }

    public void checkLogLastMessage(LogMessage message) {
        assertTrue(LogFormat.isFormatOk(logger.getLastMessage(),
                message.toString()));
    }
}
