package ru.unn.agile.currencyConverterTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import ru.unn.agile.currencyConverter.*;

import static ru.unn.agile.currencyConverter.ConstantCurrencyProvider.*;
import static ru.unn.agile.currencyConverterTests.TestConstants.doubleEpsilon;

public class MoneyTests {
    private Currency[] actualCurrencies;

    @Before
    public void init(){
        ICurrencyProvider provider = new ConstantCurrencyProvider();
        actualCurrencies = provider.getActualCurrencyCourse();
    }

    @Test
    public void isConversionRubToUsdCorrect(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];
        Currency usd = actualCurrencies[Indexes.USD.toInt()];
        Money cash = new Money(rub, 32.2133);

        cash.convertToCurrency(usd);

        Assert.assertTrue(cash.isInCurrency(usd));
        Assert.assertEquals(1.0, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void isConversionRubToCnyCorrect(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];
        Currency cny = actualCurrencies[Indexes.CNY.toInt()];
        Money cash = new Money(rub, 52.6499);

        cash.convertToCurrency(cny);

        Assert.assertTrue(cash.isInCurrency(cny));
        Assert.assertEquals(10.0, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void isConversionEurToInrCorrect(){
        Currency eur = actualCurrencies[Indexes.EUR.toInt()];
        Currency inr = actualCurrencies[Indexes.INR.toInt()];
        Money cash = new Money(eur, 10);

        cash.convertToCurrency(inr);

        Assert.assertTrue(cash.isInCurrency(inr));
        double right_answer = 10 * (inr.nominal/inr.value) * (eur.value/eur.nominal);
        Assert.assertEquals(right_answer, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void isConversionInrToInrCorrect(){
        Currency inr = actualCurrencies[Indexes.INR.toInt()];
        Money cash = new Money(inr, 10);

        cash.convertToCurrency(inr);
        Assert.assertTrue(cash.isInCurrency(inr));
        Assert.assertEquals(10, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void isConversionToNullCurrencyThrowsExceptionCorrect(){
        Currency inr = actualCurrencies[Indexes.INR.toInt()];
        Money cash = new Money(inr, 10);


        try {
            cash.convertToCurrency(null);

            Assert.fail("Exception wasn't throwed.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Currency for converting can't be null.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void moneyIsInCurrencySameCurrencyTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);

        Assert.assertTrue(cash.isInCurrency(rub));
    }

    @Test
    public void moneyIsInCurrencyDifferentCurrenciesTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];
        Currency usd = actualCurrencies[Indexes.USD.toInt()];

        Money cash = new Money(rub, 1);

        Assert.assertFalse(cash.isInCurrency(usd));
    }

    @Test
    public void moneyIsInCurrencyNullArgumentTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);

        Assert.assertFalse(cash.isInCurrency(null));
    }

    @Test
    public void moneyGetCurrencyWorksCorrectTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);

        Assert.assertTrue(cash.getCurrency().isEqual(rub));
    }

    @Test
    public void moneyGetMoneyAmountWorksCorrectTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);

        Assert.assertEquals(1, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void moneySetMoneyAmountWorksCorrectTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);
        cash.setMoneyAmount(3.14);

        Assert.assertEquals(3.14, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void moneySetMoneyAmountWorksWithZeroCorrectTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);
        cash.setMoneyAmount(0);

        Assert.assertEquals(0, cash.getMoneyAmount(), doubleEpsilon);
    }

    @Test
    public void moneySetMoneyAmountThrowsExceptionOnNegativeValueTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];

        Money cash = new Money(rub, 1);

        try {
            cash.setMoneyAmount(-1);

            Assert.fail("Exception wasn't throwed.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("MoneyAmount must be non negative integer.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void moneyConstructorThrowsExceptionOnNullCurrencyTest(){
        try {
            new Money(null, 1);

            Assert.fail("Exception wasn't throwed.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("Currency can't be null.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }

    @Test
    public void moneyConstructorThrowsExceptionOnNegativeMoneyAmountTest(){
        Currency rub = actualCurrencies[Indexes.RUB.toInt()];
        try {
            new Money(rub, -1);

            Assert.fail("Exception wasn't throwed.");
        }
        catch (IllegalArgumentException ex){
            Assert.assertEquals("MoneyAmount must be non negative integer.", ex.getMessage());
        }
        catch (Exception e){
            Assert.fail("Invalid exception type");
        }
    }
}
