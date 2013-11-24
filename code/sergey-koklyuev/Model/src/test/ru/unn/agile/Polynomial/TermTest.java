package ru.unn.agile.Polynomial;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TermTest {

    private Term term = null;

    @Before
    public void setUp() {
        term = new Term(0, 0);
    }

    @Test
    public void canSetValues() {
        term.coefficient = 3;
        term.degree = 6;

        Term testTerm = new Term(0, 0);
        testTerm.setValues(3, 6);
        assertEquals(term, testTerm);
    }

    @Test
    public void canCreateWithParameters() {
        Term term1 = new Term(6, 7);
        term.setValues(6, 7);
        assertEquals(term, term1);
    }
}
