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

    public CurrencyConverterViewModel(ICurrencyProvider provider, ILogger logger)
    {
        this.provider = provider;
        actualCurrencies = provider.getActualCurrencyCourse();
        comboBoxData = prepareComboboxData(actualCurrencies);
        toCurrencyMoneyAmount = "";
        moneyAmount = "";
        this.logger = logger;
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
    }

    private int fromCurrencyIndex;
    public int getFromCurrencyIndex()
    {
        return fromCurrencyIndex;
    }
    public void setFromCurrencyIndex(int fromCurrencyIndex)
    {
        this.fromCurrencyIndex = fromCurrencyIndex;
    }

    private int toCurrencyIndex;
    public int getToCurrencyIndex()
    {
        return toCurrencyIndex;
    }
    public void setToCurrencyIndex(int toCurrencyIndex)
    {
        this.toCurrencyIndex = toCurrencyIndex;
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
        }
        catch(Exception ex){
                toCurrencyMoneyAmount = "Your input number is incorrect, please correct it.";
        }
    }
}
