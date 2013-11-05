package ru.unn.agile.currencyConverter.viewmodel;

import ru.unn.agile.currencyConverter.*;

public class CurrencyConverterViewModel {
    ICurrencyProvider provider;
    Currency[] actualCurrencies;

    private static String[] prepareComboboxData(Currency[] currencies){
        String[] currencyCharCodes = new String[currencies.length];
        for(int i = 0; i < currencies.length; ++i){
            currencyCharCodes[i] = currencies[i].charCode;
        }
        return currencyCharCodes;
    }

    public CurrencyConverterViewModel() {
        this(new ConstantCurrencyProvider());
    }

    public CurrencyConverterViewModel(ICurrencyProvider provider)
    {
        this.provider = provider;
        actualCurrencies = provider.getActualCurrencyCourse();
        comboBoxData = prepareComboboxData(actualCurrencies);
        toCurrencyMoneyAmount = "";
        moneyAmount = "";
    }

    public String[] comboBoxData;

    public String moneyAmount;
    public int fromCurrencyIndex;
    public int toCurrencyIndex;
    public String toCurrencyMoneyAmount;

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
