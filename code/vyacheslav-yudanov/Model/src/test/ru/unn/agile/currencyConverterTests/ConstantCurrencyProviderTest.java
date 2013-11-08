package ru.unn.agile.currencyConverterTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.unn.agile.currencyConverter.*;

public class ConstantCurrencyProviderTest {
    private ICurrencyProvider provider;
    @Before
    public void init()
    {
        provider = new ConstantCurrencyProvider();
    }

    @Test
    public void actualCurrencyListIsNotEmpty(){
        Currency[] currencies = provider.getActualCurrencyCourse();
        Assert.assertTrue(currencies.length > 0);
    }

    @Test
    public void actualCurrencyListIsNotSmall(){
        Currency[] currencies = provider.getActualCurrencyCourse();
        Assert.assertTrue(currencies.length > 3);
    }

    @Test
    public void actualCurrencyListHasDifferentCurrencies(){
        Currency[] currencies = provider.getActualCurrencyCourse();
        for(int i = 0; i < currencies.length; ++i){
            for(int j = 0; j < currencies.length; ++j){
                if(i != j){
                    Assert.assertFalse(currencies[i].isEqual(currencies[j]));
                }
            }
        }
    }

}
