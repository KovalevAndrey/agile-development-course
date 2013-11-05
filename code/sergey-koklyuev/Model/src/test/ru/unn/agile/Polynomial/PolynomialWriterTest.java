package ru.unn.agile.Polynomial;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PolynomialWriterTest {

    private PolynomialWriter writer = null;
    private Term term = null;

    @Before
    public void setUp() {
        writer = new PolynomialWriter();
        term = new Term(0, 0);
    }

    @Test
    public void canWriteConstant() {
        term.setValues(5, 0);
        assertEquals("5", writer.writeTerm(term));
    }

    @Test
    public void canWriteSingleConstant() {
        term.setValues(1, 0);
        assertEquals("1", writer.writeTerm(term));
    }

    @Test
    public void canWriteVariableWithSingleCoefficientAndDegree() {
        term.setValues(1, 1);
        assertEquals("x", writer.writeTerm(term));
    }

    @Test
    public void canWriteVariableWithSingleDegree() {
        term.setValues(5, 1);
        assertEquals("5x", writer.writeTerm(term));
    }

    @Test
    public void canWriteCoefficientWithVariableAndDegree() {
        term.setValues(-4, 13);
        assertEquals("-4x^13", writer.writeTerm(term));
    }

    @Test
    public void canWriteShortPolynomial() {
        term.setValues(5, 3);
        Term[] terms = new Term[1];
        terms[0] = term;
        assertEquals("5x^3", writer.writePolynomial(terms));
    }

    @Test
    public void canWriteLongPolynomial() {
        Term[] terms = new Term[3];
        Term term1 = new Term(5, 2);
        Term term2 = new Term(-1, 0);
        Term term3 = new Term(4, 1);

        terms[0] = term1;
        terms[1] = term2;
        terms[2] = term3;

        assertEquals("5x^2 + 4x + -1", writer.writePolynomial(terms));
    }
}
