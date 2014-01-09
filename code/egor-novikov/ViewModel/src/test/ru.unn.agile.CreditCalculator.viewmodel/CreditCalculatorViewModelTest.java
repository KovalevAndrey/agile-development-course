package ru.unn.agile.CreditCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.creditcalculator.CreditCalculator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CreditCalculatorViewModelTest{
    protected CreditCalculatorViewModel creditCalculatorViewModel;

    @Before
    public void setUp() {
        FakeLogger testLogger = new FakeLogger();
        creditCalculatorViewModel = new CreditCalculatorViewModel(testLogger);
    }

    @After
    public void tearDown() {
        creditCalculatorViewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", creditCalculatorViewModel.getCreditAmountString());
        assertEquals("", creditCalculatorViewModel.getMonthsCountString());
        assertEquals("", creditCalculatorViewModel.getPercentString());
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, creditCalculatorViewModel.getPaymentType());
        assertEquals("", creditCalculatorViewModel.getResult());
        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatInTheBeginning() {
        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void isStatusBadFormatWhenCalculateWithEmptyFields() {
        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        creditCalculatorViewModel.setCreditAmountString("1");
        creditCalculatorViewModel.setMonthsCountString("1");
        creditCalculatorViewModel.setPercentString("3");

        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(CreditCalculatorViewModel.Status.READY, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        creditCalculatorViewModel.setCreditAmountString("a");
        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        creditCalculatorViewModel.setCreditAmountString("a");
        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);
        creditCalculatorViewModel.setCreditAmountString("1.0");
        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, creditCalculatorViewModel.getIsCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        creditCalculatorViewModel.setCreditAmountString("trash");

        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(false, creditCalculatorViewModel.getIsCalculateButtonEnabled());
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        creditCalculatorViewModel.setCreditAmountString("1");
        creditCalculatorViewModel.setMonthsCountString("1");

        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(false, creditCalculatorViewModel.getIsCalculateButtonEnabled());
    }

    @Test
    public void canGetPaymentTypeName() {
        String annuityName = CreditCalculatorViewModel.PaymentType.ANNUITY.toString();
        assertEquals("Аннуитентный", annuityName);
    }

    @Test
    public void canGetNumberOfPaymentTypes() {
        int paymentTypesCount = CreditCalculatorViewModel.PaymentType.values().length;
        assertEquals(2, paymentTypesCount);
    }

    @Test
    public void canGetListOfPaymentTypes() {
        CreditCalculatorViewModel.PaymentType[] paymentTypes = CreditCalculatorViewModel.PaymentType.values();
        CreditCalculatorViewModel.PaymentType[] currentPaymentTypes = new CreditCalculatorViewModel.PaymentType[]{
                CreditCalculatorViewModel.PaymentType.ANNUITY,
                CreditCalculatorViewModel.PaymentType.DIFFERENTIATED};

        assertArrayEquals(currentPaymentTypes, paymentTypes);
    }

    @Test
    public void canComparePaymentTypesByName() {
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, CreditCalculatorViewModel.PaymentType.ANNUITY);
        assertNotEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, CreditCalculatorViewModel.PaymentType.DIFFERENTIATED);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        creditCalculatorViewModel.setCreditAmountString("1");
        creditCalculatorViewModel.setMonthsCountString("1");
        creditCalculatorViewModel.setPercentString("3");

        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(true, creditCalculatorViewModel.getIsCalculateButtonEnabled());
    }

    @Test
    public void canSetAnnuityPaymentType() {
        creditCalculatorViewModel.setPaymentType(CreditCalculatorViewModel.PaymentType.ANNUITY);
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, creditCalculatorViewModel.getPaymentType());
    }

    @Test
    public void canSetDifferentiatedPaymentType() {
        creditCalculatorViewModel.setPaymentType(CreditCalculatorViewModel.PaymentType.DIFFERENTIATED);
        assertEquals(CreditCalculatorViewModel.PaymentType.DIFFERENTIATED, creditCalculatorViewModel.getPaymentType());
    }

    @Test
    public void canConvertStringToCreditCalculator() {
        double creditAmount = 10000;
        int monthCount = 24;
        double percent = 10;

        CreditCalculator creditCalculator1 = creditCalculatorViewModel.convertToCreditCalculator(String.valueOf(creditAmount), String.valueOf(monthCount), String.valueOf(percent));
        CreditCalculator creditCalculator2 = new CreditCalculator.Builder().amount(creditAmount).monthsCount(monthCount).percent(percent).build();

        assertEquals(creditCalculator2.getAmount(), creditCalculator1.getAmount(), 1.0);
        assertEquals(creditCalculator2.getMonthsCount(), creditCalculator1.getMonthsCount());
        assertEquals(creditCalculator2.getPercent(), creditCalculator1.getPercent(), 1.0);
    }

    @Test
    public void isDefaultPaymentTypeAnnuity() {
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, creditCalculatorViewModel.getPaymentType());
    }

    @Test
    public void canPerformCalcAction() {
        creditCalculatorViewModel.setCreditAmountString("300000");
        creditCalculatorViewModel.setMonthsCountString("24");
        creditCalculatorViewModel.setPercentString("12");
        creditCalculatorViewModel.setPaymentType(CreditCalculatorViewModel.PaymentType.ANNUITY);

        creditCalculatorViewModel.calculate();

        assertDoubles(338929, Double.parseDouble(creditCalculatorViewModel.getResult()));
    }

    @Test
    public void canSetSuccessMessage() {
        creditCalculatorViewModel.setCreditAmountString("25000");
        creditCalculatorViewModel.setMonthsCountString("10");
        creditCalculatorViewModel.setPercentString("15");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.SUCCESS, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessage() {
        creditCalculatorViewModel.setCreditAmountString("a");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        creditCalculatorViewModel.setCreditAmountString("125000");
        creditCalculatorViewModel.setMonthsCountString("12");
        creditCalculatorViewModel.setPercentString("9.5");

        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ANY);

        assertEquals(CreditCalculatorViewModel.Status.READY, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        creditCalculatorViewModel.setCreditAmountString("1000");
        creditCalculatorViewModel.setMonthsCountString("6");
        creditCalculatorViewModel.setPercentString("20");

        creditCalculatorViewModel.processKeyInTextField(SpecialKeys.ENTER);

        assertEquals(CreditCalculatorViewModel.Status.SUCCESS, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void canCalculateAnnuityPayment() {
        creditCalculatorViewModel.setCreditAmountString("60000");
        creditCalculatorViewModel.setMonthsCountString("18");
        creditCalculatorViewModel.setPercentString("12");
        creditCalculatorViewModel.setPaymentType(CreditCalculatorViewModel.PaymentType.ANNUITY);

        creditCalculatorViewModel.calculate();

        assertDoubles(65860.61, Double.parseDouble(creditCalculatorViewModel.getResult()));
    }

    @Test
    public void canCalculateDifferentiatedPayment() {
        creditCalculatorViewModel.setCreditAmountString("60000");
        creditCalculatorViewModel.setMonthsCountString("18");
        creditCalculatorViewModel.setPercentString("12");
        creditCalculatorViewModel.setPaymentType(CreditCalculatorViewModel.PaymentType.DIFFERENTIATED);

        creditCalculatorViewModel.calculate();

        assertDoubles(65700.00, Double.parseDouble(creditCalculatorViewModel.getResult()));
    }

    @Test
    public void zeroAmountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.setCreditAmountString("0");
        creditCalculatorViewModel.setMonthsCountString("15");
        creditCalculatorViewModel.setPercentString("5");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void negativeAmountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.setCreditAmountString("-10000");
        creditCalculatorViewModel.setMonthsCountString("15");
        creditCalculatorViewModel.setPercentString("5");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void zeroMonthsCountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.setCreditAmountString("145000");
        creditCalculatorViewModel.setMonthsCountString("0");
        creditCalculatorViewModel.setPercentString("14");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void negativeMonthsCountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.setCreditAmountString("125000");
        creditCalculatorViewModel.setMonthsCountString("-12");
        creditCalculatorViewModel.setPercentString("19");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }

    @Test
    public void negativePercentArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.setCreditAmountString("75000");
        creditCalculatorViewModel.setMonthsCountString("36");
        creditCalculatorViewModel.setPercentString("-10");

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.getStatus());
    }


    private void assertDoubles(double expected, double input)
    {
        assertEquals(expected, input, 1.0);
    }

}
