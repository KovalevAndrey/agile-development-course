package ru.unn.agile.CreditCalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.creditcalculator.CreditCalculator;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CreditCalculatorViewModelTest
{
    public static final int ANY_KEY = 0;
    private CreditCalculatorViewModel creditCalculatorViewModel;

    @Before
    public void setUp() {
        creditCalculatorViewModel = new CreditCalculatorViewModel();
    }

    @After
    public void tearDown() {
        creditCalculatorViewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", creditCalculatorViewModel.creditAmountString);
        assertEquals("", creditCalculatorViewModel.monthsCountString);
        assertEquals("", creditCalculatorViewModel.percentString);
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, creditCalculatorViewModel.paymentType);
        assertEquals("", creditCalculatorViewModel.result);
        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void isStatusBadFormatInTheBeginning() {
        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void isStatusBadFormatWhenCalculateWithEmptyFields() {
        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        creditCalculatorViewModel.creditAmountString = "1";
        creditCalculatorViewModel.monthsCountString = "1";
        creditCalculatorViewModel.percentString = "3";

        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(CreditCalculatorViewModel.Status.READY, creditCalculatorViewModel.status);
    }

    @Test
    public void canReportBadFormat() {
        creditCalculatorViewModel.creditAmountString = "a";
        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        creditCalculatorViewModel.creditAmountString = "a";
        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);
        creditCalculatorViewModel.creditAmountString = "1.0";
        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, creditCalculatorViewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        creditCalculatorViewModel.isCalculateButtonEnabled = true;
        creditCalculatorViewModel.creditAmountString = "trash";

        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, creditCalculatorViewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        creditCalculatorViewModel.creditAmountString = "1";
        creditCalculatorViewModel.monthsCountString = "1";

        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(false, creditCalculatorViewModel.isCalculateButtonEnabled);
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
        creditCalculatorViewModel.creditAmountString = "1";
        creditCalculatorViewModel.monthsCountString = "1";
        creditCalculatorViewModel.percentString = "3";

        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(true, creditCalculatorViewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canSetAnnuityPaymentType() {
        creditCalculatorViewModel.paymentType = CreditCalculatorViewModel.PaymentType.ANNUITY;
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, creditCalculatorViewModel.paymentType);
    }

    @Test
    public void canSetDifferentiatedPaymentType() {
        creditCalculatorViewModel.paymentType = CreditCalculatorViewModel.PaymentType.DIFFERENTIATED;
        assertEquals(CreditCalculatorViewModel.PaymentType.DIFFERENTIATED, creditCalculatorViewModel.paymentType);
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
        assertEquals(CreditCalculatorViewModel.PaymentType.ANNUITY, creditCalculatorViewModel.paymentType);
    }

    @Test
    public void canPerformCalcAction() {
        creditCalculatorViewModel.creditAmountString = "300000";
        creditCalculatorViewModel.monthsCountString = "24";
        creditCalculatorViewModel.percentString = "12";
        creditCalculatorViewModel.paymentType = CreditCalculatorViewModel.PaymentType.ANNUITY;

        creditCalculatorViewModel.calculate();

        assertDoubles(338929, Double.parseDouble(creditCalculatorViewModel.result));
    }

    @Test
    public void canSetSuccessMessage() {
        creditCalculatorViewModel.creditAmountString = "25000";
        creditCalculatorViewModel.monthsCountString = "10";
        creditCalculatorViewModel.percentString = "15";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.SUCCESS, creditCalculatorViewModel.status);
    }

    @Test
    public void canSetBadFormatMessage() {
        creditCalculatorViewModel.creditAmountString = "a";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void isStatusReadyWhenKeyIsNotEnter() {
        creditCalculatorViewModel.creditAmountString = "125000";
        creditCalculatorViewModel.monthsCountString = "12";
        creditCalculatorViewModel.percentString = "9.5";

        creditCalculatorViewModel.processKeyInTextField(ANY_KEY);

        assertEquals(CreditCalculatorViewModel.Status.READY, creditCalculatorViewModel.status);
    }

    @Test
    public void isStatusSuccessWhenKeyIsEnter() {
        creditCalculatorViewModel.creditAmountString = "1000";
        creditCalculatorViewModel.monthsCountString = "6";
        creditCalculatorViewModel.percentString = "20";

        creditCalculatorViewModel.processKeyInTextField(CreditCalculatorViewModel.ENTER_CODE);

        assertEquals(CreditCalculatorViewModel.Status.SUCCESS, creditCalculatorViewModel.status);
    }

    @Test
    public void canCalculateAnnuityPayment() {
        creditCalculatorViewModel.creditAmountString = "60000";
        creditCalculatorViewModel.monthsCountString = "18";
        creditCalculatorViewModel.percentString = "12";
        creditCalculatorViewModel.paymentType = CreditCalculatorViewModel.PaymentType.ANNUITY;

        creditCalculatorViewModel.calculate();

        assertDoubles(65860.61, Double.parseDouble(creditCalculatorViewModel.result));
    }

    @Test
    public void canCalculateDifferentiatedPayment() {
        creditCalculatorViewModel.creditAmountString = "60000";
        creditCalculatorViewModel.monthsCountString = "18";
        creditCalculatorViewModel.percentString = "12";
        creditCalculatorViewModel.paymentType = CreditCalculatorViewModel.PaymentType.DIFFERENTIATED;

        creditCalculatorViewModel.calculate();

        assertDoubles(65700.00, Double.parseDouble(creditCalculatorViewModel.result));
    }

    @Test
    public void zeroAmountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.creditAmountString = "0";
        creditCalculatorViewModel.monthsCountString = "15";
        creditCalculatorViewModel.percentString = "5";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void negativeAmountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.creditAmountString = "-10000";
        creditCalculatorViewModel.monthsCountString = "15";
        creditCalculatorViewModel.percentString = "5";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void zeroMonthsCountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.creditAmountString = "145000";
        creditCalculatorViewModel.monthsCountString = "0";
        creditCalculatorViewModel.percentString = "14";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void negativeMonthsCountArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.creditAmountString = "125000";
        creditCalculatorViewModel.monthsCountString = "-12";
        creditCalculatorViewModel.percentString = "19";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    @Test
    public void negativePercentArgumentSetBadFormatStatus()
    {
        creditCalculatorViewModel.creditAmountString = "75000";
        creditCalculatorViewModel.monthsCountString = "36";
        creditCalculatorViewModel.percentString = "-10";

        creditCalculatorViewModel.calculate();

        assertEquals(CreditCalculatorViewModel.Status.BAD_FORMAT, creditCalculatorViewModel.status);
    }

    private void assertDoubles(double expected, double input)
    {
        assertEquals(expected, input, 1.0);
    }

}
