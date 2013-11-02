package ru.unn.agile.currencyConverter.viewmodel;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.unn.agile.currencyConverter.*;

public class CurrencyConverterViewModelTest {
    private CurrencyConverterViewModel viewModel;

    @Before
    public void init(){
        viewModel = new CurrencyConverterViewModel(new ConstantCurrencyProvider());
    }

    @Test
    public void TestConstructing(){
        Assert.assertNotNull(viewModel.provider);
        Assert.assertNotNull(viewModel.actualCurrencies);
        Assert.assertNotNull(viewModel.comboBoxData);
        Assert.assertEquals(viewModel.comboBoxData.length, viewModel.actualCurrencies.length);
        Assert.assertEquals(viewModel.fromCurrencyIndex, 0);
        Assert.assertEquals(viewModel.toCurrencyIndex, 0);
        Assert.assertEquals(viewModel.moneyAmount, "");
        Assert.assertEquals(viewModel.toCurrencyMoneyAmount, "");
    }

    @Test
    public void TestSimpleConversion(){
        viewModel.moneyAmount = "1";
        viewModel.toCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();
        viewModel.fromCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.toCurrencyMoneyAmount, "1.0");
    }

    @Test
    public void TestSimpleConversionWithPointNumber(){
        viewModel.moneyAmount = "1.0";
        viewModel.toCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();
        viewModel.fromCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.toCurrencyMoneyAmount, "1.0");
    }

    @Test
    public void TestSimpleConversionWithComaNumber(){
        viewModel.moneyAmount = "1,0";
        viewModel.toCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();
        viewModel.fromCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.toCurrencyMoneyAmount, "1.0");
    }

    @Test
    public void TestSimpleConversionWithDifferentCurrencies(){
        viewModel.moneyAmount = "1.0";
        viewModel.fromCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();
        viewModel.toCurrencyIndex = ConstantCurrencyProvider.Indexes.RUB.toInt();

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.toCurrencyMoneyAmount, "32.2133");
    }

    @Test
    public void TestMoneyAmountWithTwoPoints(){
        viewModel.moneyAmount = "1.1.0";
        viewModel.fromCurrencyIndex = ConstantCurrencyProvider.Indexes.USD.toInt();
        viewModel.toCurrencyIndex = ConstantCurrencyProvider.Indexes.RUB.toInt();

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.toCurrencyMoneyAmount, "Error");
    }
}
