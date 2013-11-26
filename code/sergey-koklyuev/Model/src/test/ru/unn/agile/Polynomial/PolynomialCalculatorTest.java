package ru.unn.agile.Polynomial;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PolynomialCalculatorTest {

    private PolynomialCalculator calculator = null;
    private Term[] polynomial1 = null;
    private Term[] polynomial2 = null;
    private Term[] result = null;

    @Before
    public void setUp() {
        calculator = new PolynomialCalculator();
    }

    @Test
    public void twoEmptyTermsGivesEmptyTerms() throws Exception {
        polynomial1 = new Term[0];
        polynomial2 = new Term[0];
        assertEquals(0, calculator.add(polynomial1, polynomial2).length);
    }

    @Test
    public void polynomialAndEmptyStringGivesTheSamePolynomial() throws  Exception {
        polynomial1 = new Term[2];
        polynomial1[0] = new Term(2, 1);
        polynomial1[1] = new Term(1, 0);

        polynomial2 = new Term[0];

        result = new Term[2];
        result[0] = new Term(2, 1);
        result[1] = new Term(1, 0);

        assertArrayEquals(result, calculator.add(polynomial1, polynomial2));
    }

    @Test
    public void emptyStringAndPolynomialGivesTheSamePolynomial() throws  Exception {
        polynomial1 = new Term[0];

        polynomial2 = new Term[2];
        polynomial2[0] = new Term(2, 1);
        polynomial2[1] = new Term(1, 0)
        ;
        result = new Term[2];
        result[0] = new Term(2, 1);
        result[1] = new Term(1, 0);

        assertArrayEquals(result, calculator.add(polynomial1, polynomial2));
    }

    @Test
    public void twoPolynomialsGivesSum() throws Exception {
        polynomial1 = new Term[2];
        polynomial1[0] = new Term(2, 1);
        polynomial1[1] = new Term(2, 0);

        polynomial2 = new Term[2];
        polynomial2[0] = new Term(4, 1);
        polynomial2[1] = new Term(1, 0);

        result = new Term[2];
        result[0] = new Term(6, 1);
        result[1] = new Term(3, 0);

        assertArrayEquals(result, calculator.add(polynomial1, polynomial2));
    }

    @Test
    public void twoBigPolynomialGivesSum() throws Exception {
        polynomial1 = new Term[3];
        polynomial1[0] = new Term(4, 2);
        polynomial1[1] = new Term(2, 1);
        polynomial1[2] = new Term(1, 0);

        polynomial2 = new Term[2];
        polynomial2[0] = new Term(4, 2);
        polynomial2[1] = new Term(2, 1);

        result = new Term[3];
        result[0] = new Term(8, 2);
        result[1] = new Term(4, 1);
        result[2] = new Term(1, 0);

        assertArrayEquals(result, calculator.add(polynomial1, polynomial2));
    }

    @Test
    public void canSubtractTwoPolynomials() throws Exception {
        polynomial1 = new Term[2];
        polynomial1[0] = new Term(10, 3);
        polynomial1[1] = new Term(13, 0);

        polynomial2 = new Term[3];
        polynomial2[0] = new Term(5, 3);
        polynomial2[1] = new Term(7, 1);
        polynomial2[2] = new Term(3, 0);

        result = new Term[3];
        result[0] = new Term(5, 3);
        result[1] = new Term(-7, 1);
        result[2] = new Term(10, 0);

        assertArrayEquals(result, calculator.sub(polynomial1, polynomial2));
    }

    @Test
    public void canMultiplyTwoPolynomials() throws Exception {
        polynomial1 = new Term[2];
        polynomial1[0] = new Term(1, 1);
        polynomial1[1] = new Term(1, 0);

        polynomial2 = new Term[2];
        polynomial2[0] = new Term(1, 1);
        polynomial2[1] = new Term(1, 0);

        result = new Term[3];
        result[0] = new Term(1, 2);
        result[1] = new Term(2, 1);
        result[2] = new Term(1, 0);

        assertArrayEquals(result, calculator.mul(polynomial1, polynomial2));
    }

    @Test
    public void multiplyPolynomialAndEmptyStringGivesEmptyString() throws Exception {
        polynomial1 = new Term[2];
        polynomial1[0] = new Term(1, 1);
        polynomial1[1] = new Term(1, 0);

        polynomial2 = new Term[0];

        assertEquals(0, calculator.mul(polynomial1, polynomial2).length);
    }

    @Test(expected=Exception.class)
    public void sumIncorrectPolynomialsGivesErrorMessage() throws Exception {
        calculator.add(polynomial1, polynomial2);
    }

    @Test(expected=Exception.class)
    public void mulIncorrectPolynomialsGivesErrorMessage() throws Exception {
        calculator.mul(polynomial1, polynomial2);
    }

    @Test(expected=Exception.class)
    public void subIncorrectPolynomialGivesErrorMessage() throws Exception {
        calculator.sub(polynomial1, polynomial2);
    }

}
