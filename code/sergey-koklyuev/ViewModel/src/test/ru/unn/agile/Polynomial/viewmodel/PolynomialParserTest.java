package ru.unn.agile.Polynomial.viewmodel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Polynomial.Term;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PolynomialParserTest {
    private PolynomialParser parser = null;
    private Term term = null;

    @Before
    public void setUp() {
        parser = new PolynomialParser();
        term = new Term(0, 0);
    }

    @Test
    public void emptyStringGivesEmptyList() {
        term.setValues(0, 0);
        assertEquals(term, parser.parseTerm(""));
    }

    @Test
    public void constantGivesValidTerm() {
        term.setValues(5, 0);
        assertEquals(term, parser.parseTerm("5"));
    }

    @Test
    public void variableGivesValidTerm() {
        term.setValues(1, 1);
        assertEquals(term, parser.parseTerm("x"));
    }

    @Test
    public void constantWithVariableGivesValidTerm() {
        term.setValues(8, 1);
        assertEquals(term, parser.parseTerm("8x"));
    }

    @Test
    public void negativeConstantWithVariableGivesValidTerm() {
        term.setValues(-4, 1);
        assertEquals(term, parser.parseTerm("-4x"));
    }

    @Test
    public void variableWithDegreeGivesValidTerm() {
        term.setValues(1, 6);
        assertEquals(term, parser.parseTerm("x^6"));
    }

    @Test
    public void variableWithNegativeDegreeGivesValidTerm() {
        term.setValues(1, -5);
        assertEquals(term, parser.parseTerm("x^-5"));
    }

    @Test
    public void variableWithConstantAndDegreeGivesValidTerm() {
        term.setValues(3, 7);
        assertEquals(term, parser.parseTerm("3x^7"));
    }

    @Test
    public void canParseEmptyPolynomial() {
        assertEquals(0, parser.parsePolynomial("").length);
    }

    @Test
    public void canParsePolynomialWithOneTerm() {
        Term[] expected_terms = new Term[1];
        term.setValues(3, 4);
        expected_terms[0] = term;
        assertArrayEquals(expected_terms, parser.parsePolynomial("3x^4"));
    }

    @Test
    public void canParsePolynomialWithManyTerms() {
        Term[] expected_terms = new Term[3];
        Term term1 = new Term(3, 2);
        Term term2 = new Term(-5, 1);
        Term term3 = new Term(7, 0);
        expected_terms[0] = term1;
        expected_terms[1] = term2;
        expected_terms[2] = term3;
        assertArrayEquals(expected_terms, parser.parsePolynomial("3x^2 + -5x + 7"));
    }
}
