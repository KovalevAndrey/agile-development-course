package ru.unn.agile.creditcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreditCalculatorTests
{
    @Test(expected=IllegalArgumentException.class)
    public void zeroAmountArgumentThrowExceptionInBuilder()
    {
        new CreditCalculator.Builder().amount(0).monthsCount(15).percent(5).build();
    }

    @Test(expected=IllegalArgumentException.class)
    public void negativeAmountArgumentThrowExceptionInBuilder()
    {
        new CreditCalculator.Builder().amount(-10000).monthsCount(15).percent(5).build();
    }

    @Test(expected=IllegalArgumentException.class)
    public void zeroMonthsCountArgumentThrowExceptionInBuilder()
    {
        new CreditCalculator.Builder().amount(145000).monthsCount(0).percent(14).build();
    }

    @Test(expected=IllegalArgumentException.class)
    public void negativeMonthsCountArgumentThrowExceptionInConstructor()
    {
        new CreditCalculator.Builder().amount(125000).monthsCount(-12).percent(19).build();
    }

    @Test(expected=IllegalArgumentException.class)
    public void negativePercentArgumentThrowExceptionInConstructor()
    {
        new CreditCalculator.Builder().amount(75000).monthsCount(36).percent(-10).build();
    }

    @Test(expected=IllegalStateException.class)
    public void BuilderThrowExceptionIfNotAllRequiredFieldsAreFilled1()
    {
        new CreditCalculator.Builder().amount(20000).build();
    }

    @Test(expected=IllegalStateException.class)
    public void BuilderThrowExceptionIfNotAllRequiredFieldsAreFilled2()
    {
        new CreditCalculator.Builder().monthsCount(10).build();
    }

    @Test(expected=IllegalStateException.class)
    public void BuilderThrowExceptionIfNotAllRequiredFieldsAreFilled3()
    {
        new CreditCalculator.Builder().percent(11).build();
    }

    @Test(expected=IllegalStateException.class)
    public void BuilderThrowExceptionIfNotAllRequiredFieldsAreFilled4()
    {
        new CreditCalculator.Builder().amount(20000).monthsCount(12).build();
    }

    @Test(expected=IllegalStateException.class)
    public void BuilderThrowExceptionIfNotAllRequiredFieldsAreFilled5()
    {
        new CreditCalculator.Builder().amount(20000).percent(6).build();
    }

    @Test(expected=IllegalStateException.class)
    public void BuilderThrowExceptionIfNotAllRequiredFieldsAreFilled6()
    {
        new CreditCalculator.Builder().monthsCount(12).percent(7).build();
    }

    @Test
    public void correctData1WorksProperlyInGetAnnuityMonthPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(120000).monthsCount(12).percent(24).build();
        assertDoubles(11347.15, creditCalculator.getAnnuityMonthPayment());
    }

    @Test
    public void correctData2WorksProperlyInGetAnnuityMonthPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(100000).monthsCount(36).percent(10).build();
        assertDoubles(3226.72, creditCalculator.getAnnuityMonthPayment());
    }

    @Test
    public void correctData3WorksProperlyInGetAnnuityMonthPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(400000).monthsCount(11).percent(7).build();
        assertDoubles(37648.70, creditCalculator.getAnnuityMonthPayment());
    }

    @Test
    public void correctData1WorksProperlyInGetDifferentiatedMonthPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(120000).monthsCount(12).percent(24).build();
        assertDoubles(11600.00, creditCalculator.getDifferentiatedMonthPayment(5));
    }

    @Test
    public void correctData2WorksProperlyInGetDifferentiatedMonthPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(140000).monthsCount(17).percent(18.5).build();
        assertDoubles(8616.18 , creditCalculator.getDifferentiatedMonthPayment(15));
    }

    @Test
    public void correctData3WorksProperlyInGetDifferentiatedMonthPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(170000).monthsCount(6).percent(5).build();
        assertDoubles(28805.56, creditCalculator.getDifferentiatedMonthPayment(3));
    }

    @Test
    public void incorrectMonthNumberArgument1InGetDifferentiatedMonthPaymentThrowException()
    {
        try
        {
            CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(180000).monthsCount(24).percent(16).build();
            creditCalculator.getDifferentiatedMonthPayment(0);
            fail();
        }
        catch (IllegalArgumentException ex)
        {
            assertEquals("monthNumber argument value exception", ex.getMessage());
        }
    }

    @Test
    public void incorrectMonthNumberArgument2InGetDifferentiatedMonthPaymentThrowException()
    {
        try
        {
            CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(160000).monthsCount(24).percent(11).build();
            creditCalculator.getDifferentiatedMonthPayment(24+1);
            fail();
        }
        catch (IllegalArgumentException ex)
        {
            assertEquals("monthNumber argument value exception", ex.getMessage());
        }
    }

    @Test
    public void correctData1WorksProperlyInGetAnnuityTotalPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(120000).monthsCount(12).percent(24).build();
        assertDoubles(136165.82, creditCalculator.getAnnuityTotalPayment());
    }

    @Test
    public void correctData2WorksProperlyInGetAnnuityTotalPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(180000).monthsCount(18).percent(10).build();
        assertDoubles(194584.94, creditCalculator.getAnnuityTotalPayment());
    }

    @Test
    public void correctData3WorksProperlyInGetAnnuityTotalPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(35000).monthsCount(10).percent(15).build();
        assertDoubles(37451.08, creditCalculator.getAnnuityTotalPayment());
    }

    @Test
    public void correctData1WorksProperlyInGetDifferentiatedTotalPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(180000).monthsCount(18).percent(10).build();
        assertDoubles(194250.00, creditCalculator.getDifferentiatedTotalPayment());
    }

    @Test
    public void correctData2WorksProperlyInGetDifferentiatedTotalPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(200000).monthsCount(6).percent(5).build();
        assertDoubles(202916.67, creditCalculator.getDifferentiatedTotalPayment());
    }

    @Test
    public void correctData3WorksProperlyInGetDifferentiatedTotalPaymentMethod()
    {
        CreditCalculator creditCalculator = new CreditCalculator.Builder().amount(150000).monthsCount(9).percent(15).build();
        assertDoubles(159375.00, creditCalculator.getDifferentiatedTotalPayment());
    }

    private void assertDoubles(double expected, double input)
    {
        assertEquals(expected, input, 1.0);
    }
}
