package ru.unn.agile.currencyConverter;


public class ConstantCurrencyProvider implements ICurrencyProvider {

    @Override
    public final Currency[] getActualCurrencyCourse(){
        Currency[] testCurrencies = new Currency[5];

        //For information about currency num codes look at http://www.cbr.ru/scripts/XML_daily.asp
        testCurrencies[0] = new Currency(840, "USD", "Доллар США", 1, 32.2133);
        testCurrencies[1] = new Currency(978, "EUR", "Евро", 1, 43.6039);
        testCurrencies[2] = new Currency(356, "INR", "Индийских рупий", 100, 52.5203);
        testCurrencies[3] = new Currency(156, "CNY", "Китайских юаней", 10, 52.6499);
        testCurrencies[4] = new Currency(1, "RUB", "Рубль", 1, 1);

        return testCurrencies;
    }

    public enum Indexes{
        USD(0), EUR(1), INR(2), CNY(3), RUB(4);

        private final int value;

        private Indexes(final int index){
            value = index;
        }
        public int toInt(){
            return value;
        }
    }
}
