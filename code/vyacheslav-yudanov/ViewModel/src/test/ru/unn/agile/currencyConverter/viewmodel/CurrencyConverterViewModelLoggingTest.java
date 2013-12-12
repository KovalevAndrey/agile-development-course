package ru.unn.agile.currencyConverter.viewmodel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.currencyConverter.ConstantCurrencyProvider;

public class CurrencyConverterViewModelLoggingTest {
    private CurrencyConverterViewModel viewModel;
    private ILogger logger;

    protected ILogger getLogger()
    {
        return new FakeLogger();
    }

    @Before
    public void init(){
        logger = getLogger();
        viewModel = new CurrencyConverterViewModel(new ConstantCurrencyProvider(), logger);
    }

    @Test
    public void moneyAmountSetLogsInDebug()
    {
        viewModel.setMoneyAmount("1.0");
        Assert.assertEquals("moneyAmount was set to: 1.0", logger.getLastLogMessage());
    }

    @Test
    public void moneyAmountSetNotLogsInRelease()
    {
        logger.setLogLevel(LoggingLevel.Release);
        viewModel.setMoneyAmount("1.0");
        Assert.assertNull(logger.getLastLogMessage());
    }

    @Test
    public void fromCurrencyIndexSetLogsInDebug()
    {
        viewModel.setFromCurrencyIndex(3);
        Assert.assertEquals("fromCurrencyIndex was set to: 3", logger.getLastLogMessage());
    }

    @Test
    public void fromCurrencyIndexSetNotLogsInRelease()
    {
        logger.setLogLevel(LoggingLevel.Release);
        viewModel.setFromCurrencyIndex(1);
        Assert.assertNull(logger.getLastLogMessage());
    }

    @Test
    public void toCurrencyIndexSetLogsInDebug()
    {
        viewModel.setToCurrencyIndex(2);
        Assert.assertEquals("toCurrencyIndex was set to: 2", logger.getLastLogMessage());
    }

    @Test
    public void toCurrencyIndexSetNotLogsInRelease()
    {
        logger.setLogLevel(LoggingLevel.Release);
        viewModel.setToCurrencyIndex(1);
        Assert.assertNull(logger.getLastLogMessage());
    }

    @Test
    public void correctBtnClickLogsInDebug()
    {
        viewModel.setMoneyAmount("3");
        viewModel.setToCurrencyIndex(1);
        viewModel.setFromCurrencyIndex(2);

        viewModel.convertBtnClick();

        Assert.assertEquals("Convert success with parameters. moneyAmount = 3, toCurrencyIndex = 1, fromCurrencyIndex = 2", logger.getLastLogMessage());
    }

    @Test
    public void correctBtnClickNotLogsInRelease()
    {
        logger.setLogLevel(LoggingLevel.Release);
        viewModel.setMoneyAmount("3");
        viewModel.setToCurrencyIndex(1);
        viewModel.setFromCurrencyIndex(2);

        viewModel.convertBtnClick();

        Assert.assertNull(logger.getLastLogMessage());
    }

    @Test
    public void incorrectBtnClickLogsInDebug()
    {
        viewModel.setMoneyAmount("3.1.1");
        viewModel.setToCurrencyIndex(1);
        viewModel.setFromCurrencyIndex(2);

        viewModel.convertBtnClick();

        Assert.assertEquals("ERROR: Convert error with parameters. moneyAmount = 3.1.1, toCurrencyIndex = 1, fromCurrencyIndex = 2", logger.getLastLogMessage());
    }

    @Test
    public void incorrectBtnClickLogsInRelease()
    {
        logger.setLogLevel(LoggingLevel.Release);
        viewModel.setMoneyAmount("3.1.1");
        viewModel.setToCurrencyIndex(1);
        viewModel.setFromCurrencyIndex(2);

        viewModel.convertBtnClick();

        Assert.assertEquals("ERROR: Convert error with parameters. moneyAmount = 3.1.1, toCurrencyIndex = 1, fromCurrencyIndex = 2", logger.getLastLogMessage());
    }
}
