package ru.unn.agile.fraction;

public class Fraction {

    private int m_numerator;
    private int m_denominator;

    Fraction(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("Argument 'denominator' can not be zero!");

        m_numerator = numerator;
        m_denominator = denominator;

        checkDenominatorNegative();
        reduce();
    }

    private void checkDenominatorNegative() {
        if (m_denominator < 0) {
            m_numerator = -m_numerator;
            m_denominator = -m_denominator;
        }
    }

    private void reduce() {
        int greatestCommonDivisor = GreatestCommonDivisor.calculate(m_numerator, m_denominator);

        m_numerator /= greatestCommonDivisor;
        m_denominator /= greatestCommonDivisor;
    }

    public static Fraction parse(String input) {
        if (input == null || input.isEmpty())
            throwFractionParseException();

        int numerator = 0;
        int denominator = 1;

        String[] parts = input.split("/");

        if (parts.length == 0 || parts.length > 2)
            throwFractionParseException();

        try {
            numerator = Integer.parseInt(parts[0].trim());

            if (parts.length > 1)
                denominator = Integer.parseInt(parts[1].trim());
        }
        catch (Exception e) {
            throwFractionParseException();
        }

        return new Fraction(numerator, denominator);
    }

    private static void throwFractionParseException() {
        throw new IllegalArgumentException("Failed parse input string!");
    }

    public static Boolean tryParse(String input) {
        try {
            parse(input);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Fraction add(Fraction other) {
        return new Fraction(this.getNumerator() * other.getDenominator() + other.getNumerator() * this.getDenominator(), this.getDenominator() * other.getDenominator());
    }

    public Fraction subtract(Fraction other) {
        return new Fraction(this.getNumerator() * other.getDenominator() - other.getNumerator() * this.getDenominator(), this.getDenominator() * other.getDenominator());
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(this.getNumerator() * other.getNumerator(), this.getDenominator() * other.getDenominator());
    }

    public Fraction divide(Fraction other) {
        return new Fraction(this.getNumerator() * other.getDenominator(), this.getDenominator() * other.getNumerator());
    }

    public int getNumerator() {
        return m_numerator;
    }

    public int getDenominator() {
        return m_denominator;
    }

    public String toString() {
        if (getDenominator() == 1)
            return String.format("%d", getNumerator());

        return String.format("%d/%d", getNumerator(), getDenominator());
    }
}
