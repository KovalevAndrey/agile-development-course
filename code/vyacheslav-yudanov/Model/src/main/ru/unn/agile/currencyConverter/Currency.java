package ru.unn.agile.currencyConverter;

//used standart format.  Look at http://www.cbr.ru/scripts/XML_daily.asp
public class Currency {
    public final int numCode;
    public final String charCode;
    public final String name;
    public final int nominal;
    public final double value;

    public Currency(final int numCode, final String charCode, final String name, final int nominal, final double value){
        constructorArgumentChecks(charCode, nominal, value);

        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
        this.nominal = nominal;
        this.value = value;
    }

    private void constructorArgumentChecks(final String charCode, final int nominal, final double value){
        if(charCode == null){
            throw new IllegalArgumentException("Currency's charCode can't be null.");
        }

        if(nominal <= 0){
            throw new IllegalArgumentException("Nominal must be positive integer.");
        }

        if(value <= 0){
            throw new IllegalArgumentException("Value must be positive integer.");
        }
    }

    public final boolean isEqual(Currency compareCurrency){
        return compareCurrency != null
               && this.numCode == compareCurrency.numCode
               && this.charCode.equals(compareCurrency.charCode);
    }
}
