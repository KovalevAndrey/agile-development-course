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
        Assert.assertNotNull(viewModel.logger);
        Assert.assertNotNull(viewModel.actualCurrencies);
        Assert.assertNotNull(viewModel.getComboBoxData());
        Assert.assertEquals(viewModel.getComboBoxData().length, viewModel.actualCurrencies.length);
        Assert.assertEquals(viewModel.getFromCurrencyIndex(), 0);
        Assert.assertEquals(viewModel.getToCurrencyIndex(), 0);
        Assert.assertEquals(viewModel.getMoneyAmount(), "");
        Assert.assertEquals(viewModel.getToCurrencyMoneyAmount(), "");
    }

    @Test
    public void TestSimpleConversion(){
        viewModel.setMoneyAmount("1");
        viewModel.setToCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());
        viewModel.setFromCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.getToCurrencyMoneyAmount(), "1.0");
    }

    @Test
    public void TestSimpleConversionWithPointNumber(){
        viewModel.setMoneyAmount("1.0");
        viewModel.setToCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());
        viewModel.setFromCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.getToCurrencyMoneyAmount(), "1.0");
    }

    @Test
    public void TestSimpleConversionWithCommaNumber(){
        viewModel.setMoneyAmount("1,0");
        viewModel.setToCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());
        viewModel.setFromCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.getToCurrencyMoneyAmount(), "1.0");
    }

    @Test
    public void TestSimpleConversionWithDifferentCurrencies(){
        viewModel.setMoneyAmount("1.0");
        viewModel.setFromCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());
        viewModel.setToCurrencyIndex(ConstantCurrencyProvider.Indexes.RUB.toInt());

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.getToCurrencyMoneyAmount(), "32.2133");
    }

    @Test
    public void TestMoneyAmountWithTwoPoints(){
        viewModel.setMoneyAmount("1.1.0");
        viewModel.setFromCurrencyIndex(ConstantCurrencyProvider.Indexes.USD.toInt());
        viewModel.setToCurrencyIndex(ConstantCurrencyProvider.Indexes.RUB.toInt());

        viewModel.convertBtnClick();

        Assert.assertEquals(viewModel.getToCurrencyMoneyAmount(), "Your input number is incorrect, please correct it.");
    }
}
