package ru.unn.agile.currencyConverter.viewmodel;

import ru.unn.agile.currencyConverter.*;

public class CurrencyConverterViewModel {
    ICurrencyProvider provider;
    Currency[] actualCurrencies;
    ILogger logger;

    private static String[] prepareComboboxData(Currency[] currencies){
        String[] currencyCharCodes = new String[currencies.length];
        for(int i = 0; i < currencies.length; ++i){
            currencyCharCodes[i] = currencies[i].charCode;
        }
        return currencyCharCodes;
    }

    public CurrencyConverterViewModel() {
        this(new ConstantCurrencyProvider(), null);
    }

    public CurrencyConverterViewModel(ICurrencyProvider provider)
    {
        this(provider, null);
    }

    public CurrencyConverterViewModel(ILogger logger)
    {
        this(new ConstantCurrencyProvider(), logger);
    }

    public CurrencyConverterViewModel(ICurrencyProvider provider, ILogger logger)
    {
        this.provider = provider;
        actualCurrencies = provider.getActualCurrencyCourse();
        comboBoxData = prepareComboboxData(actualCurrencies);
        toCurrencyMoneyAmount = "";
        moneyAmount = "";
        this.logger = new NullSafeLoggerWrapper(logger);
    }

    private String[] comboBoxData;
    public String[] getComboBoxData()
    {
        return comboBoxData;
    }

    private String moneyAmount;
    public String getMoneyAmount()
    {
        return  moneyAmount;
    }
    public void setMoneyAmount(String moneyAmount)
    {
        this.moneyAmount = moneyAmount;

        logger.logMessage("moneyAmount was set to: " + moneyAmount);
    }

    private int fromCurrencyIndex;
    public int getFromCurrencyIndex()
    {
        return fromCurrencyIndex;
    }
    public void setFromCurrencyIndex(int fromCurrencyIndex)
    {
        this.fromCurrencyIndex = fromCurrencyIndex;

        logger.logMessage("fromCurrencyIndex was set to: " + fromCurrencyIndex);
    }

    private int toCurrencyIndex;
    public int getToCurrencyIndex()
    {
        return toCurrencyIndex;
    }
    public void setToCurrencyIndex(int toCurrencyIndex)
    {
        this.toCurrencyIndex = toCurrencyIndex;

        logger.logMessage("toCurrencyIndex was set to: " + toCurrencyIndex);
    }

    private String toCurrencyMoneyAmount;
    public String getToCurrencyMoneyAmount()
    {
        return  toCurrencyMoneyAmount;
    }

    public void convertBtnClick(){
        try
        {
            moneyAmount = moneyAmount.replace(',', '.');
            double moneyAmountNum = Double.parseDouble(moneyAmount);
            Money originalMoney = new Money(actualCurrencies[fromCurrencyIndex], moneyAmountNum);
            originalMoney.convertToCurrency(actualCurrencies[toCurrencyIndex]);
            toCurrencyMoneyAmount = Double.toString(originalMoney.getMoneyAmount());

            String errorMessageFormat = "Convert success with parameters. moneyAmount = %s, toCurrencyIndex = %d, fromCurrencyIndex = %d";
            logger.logMessage(String.format(errorMessageFormat, moneyAmount, toCurrencyIndex, fromCurrencyIndex));
        }
        catch(Exception ex){
                toCurrencyMoneyAmount = "Your input number is incorrect, please correct it.";

                String errorMessageFormat = "Convert error with parameters. moneyAmount = %s, toCurrencyIndex = %d, fromCurrencyIndex = %d";
                logger.logError(String.format(errorMessageFormat, moneyAmount, toCurrencyIndex, fromCurrencyIndex));
        }
    }
}
