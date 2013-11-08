package ru.unn.agile.currencyConverterTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.unn.agile.currencyConverter.*;

import static ru.unn.agile.currencyConverter.ConstantCurrencyProvider.*;
import static ru.unn.agile.currencyConverterTests.TestConstants.doubleEpsilon;

public class CurrencyTests {
    Currency[] actualCurrencies;

    @Before
    public void init(){
        ICurrencyProvider provider = new ConstantCurrencyProvider();
        actualCurrencies = provider.getActualCurrencyCourse();
    }

    @Test
    public void currencyIsEqualCompareSameCurrenciesTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Assert.assertTrue(rub.isEqual(rub));
    }

    @Test
    public void currencyIsEqualCompareDifferentCurrenciesTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];
        Currency usd = actualCurrencies[Indexes.USD.toInt()];

        Assert.assertFalse(rub.isEqual(usd));
    }

    @Test
    public void currencyIsEqualFalseOnNullArgumentTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Assert.assertFalse(rub.isEqual(null));
    }

    @Test
    public void currencyConstructingCorrectTest(){
        Currency usd = new Currency(840, "USD", "Доллар США", 1, 32.2133);

        Assert.assertEquals(840, usd.numCode);
        Assert.assertEquals("USD", usd.charCode);
        Assert.assertEquals("Доллар США", usd.name);
        Assert.assertEquals(1, usd.nominal);
        Assert.assertEquals(32.2133, usd.value, doubleEpsilon);
    }

    @Test
    public void currencyConstructingThrowsExceptionOnNullCharCodeTest(){
        try {
            new Currency(840, null, "Доллар США", 1, 32.2133);

            Assert.fail("Exception wasn't threw.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Currency's charCode can't be null.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void currencyConstructingThrowsExceptionOnNullNominalTest(){
        try {
            new Currency(840, "USD", "Доллар США", 0, 32.2133);

            Assert.fail("Exception wasn't threw.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Nominal must be positive integer.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void currencyConstructingThrowsExceptionOnNegativeNominalTest(){
        try {
            new Currency(840, "USD", "Доллар США", -1, 32.2133);

            Assert.fail("Exception wasn't threw.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Nominal must be positive integer.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void currencyConstructingThrowsExceptionOnNullValueTest(){
        try {
            new Currency(840, "USD", "Доллар США", 1, 0);

            Assert.fail("Exception wasn't threw.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Value must be positive integer.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void currencyConstructingThrowsExceptionOnNegativeValueTest(){
        try {
            new Currency(840, "USD", "Доллар США", 1, -1);

            Assert.fail("Exception wasn't threw.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Value must be positive integer.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

}
