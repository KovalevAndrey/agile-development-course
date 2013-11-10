package ru.unn.agile.Polynomial.viewmodel;

import ru.unn.agile.Polynomial.Term;

import java.util.Arrays;

class PolynomialWriter {

    public String writeTerm(Term term) {
        if (term.coefficient == 0)
            return "";

        String coefficient = "";
        String variable = "";
        String degree = "";

        if (term.degree != 0)
            variable = "x";

        if (term.degree != 1 && term.degree != 0)
            degree = "^" + Integer.toString(term.degree);

        if (term.coefficient != 1)
            coefficient = Integer.toString(term.coefficient);

        if (term.coefficient == 1 && term.degree == 0)
            coefficient = "1";

        return coefficient + variable + degree;
    }

    public String writePolynomial(Term[] terms) {
        Arrays.sort(terms);

        String polynomial = "";

        for (int i = 0; i < terms.length; i++) {
            String term = writeTerm(terms[i]);

            if (!term.isEmpty())
                polynomial +=  term + " + ";
        }

        if (polynomial.isEmpty())
            return polynomial;

        return polynomial.substring(0, polynomial.length() - 3);
    }
}