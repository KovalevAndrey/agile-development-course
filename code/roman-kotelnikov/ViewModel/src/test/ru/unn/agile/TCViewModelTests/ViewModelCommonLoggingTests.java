package ru.unn.agile.TCViewModelTests;

import org.junit.Test;
import ru.unn.agile.TC.viewmodel.ILogger;
import ru.unn.agile.TC.viewmodel.LogFormat;
import ru.unn.agile.TC.viewmodel.LogMessage;
import ru.unn.agile.TC.viewmodel.ViewModel;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.unn.agile.TC.AvailableScales.Fahrenheit;
import static ru.unn.agile.TC.AvailableScales.Romer;
import static ru.unn.agile.TC.viewmodel.LogMessage.MessagePattern.*;

public abstract class ViewModelCommonLoggingTests {
    protected ViewModel viewModel;
    protected ILogger logger;

    @Test
    public void ensureTempValueLoggedOnChange() throws IOException {
        viewModel.input = "36.5";

        checkUserInputsLogging();
    }

    @Test
    public void ensureInputScaleLoggedOnChange() throws IOException {
        viewModel.inputScale = Fahrenheit;

        checkUserInputsLogging();
    }

    @Test
    public void ensureResultScaleLoggedOnChange() throws IOException {
        viewModel.resultScale = Romer;

        checkUserInputsLogging();
    }

    @Test
    public void ensureConversionResultLogged() throws IOException {
        viewModel.input = "36.5";

        viewModel.convert();

        checkLogLastMessageAndSize(new LogMessage(LOG_INFO_VM_OK), 2);
    }

    @Test
    public void ensureWrongInputStringConversionLogged() throws IOException {
        viewModel.input = "o-lo-lo";

        viewModel.convert();

        checkLogLastMessageAndSize(new LogMessage(LOG_ERROR_WRONG_INPUT_STRING), 1);
    }

    @Test
    public void ensureNullInputScaleConversionLogged() throws IOException {
        viewModel.inputScale = null;

        viewModel.convert();

        checkLogLastMessageAndSize(new LogMessage(LOG_ERROR_INPUT_SCALE_IS_NULL), 1);
    }

    @Test
    public void ensureNullResultScaleConversionLogged() throws IOException{
        viewModel.resultScale = null;

        viewModel.convert();

        checkLogLastMessageAndSize(new LogMessage(LOG_ERROR_RESULT_SCALE_IS_NULL), 1);
    }

    public void checkUserInputsLogging() throws IOException{
        viewModel.inputParametersChanged();

        LogMessage message = new LogMessage(LOG_INFO_INPUT,
                viewModel.input,
                viewModel.inputScale,
                viewModel.resultScale);

        checkLogLastMessageAndSize(message, 1);
    }

    public void checkLogLastMessageAndSize(LogMessage message, int size) throws IOException {
        assertTrue(LogFormat.isFormatOk(logger.getLastMessage(),
                message.toString()));

        assertEquals(size, logger.getLog().size());
    }
}
