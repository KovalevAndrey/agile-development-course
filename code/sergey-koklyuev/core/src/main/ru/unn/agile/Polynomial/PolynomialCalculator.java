package ru.unn.agile.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class PolynomialCalculator {

    public PolynomialCalculator() {
        parser = new PolynomialParser();
        writer = new PolynomialWriter();
    }

    public String add(String summand1, String summand2) {
        Term[] terms1 = parsePolynomial(summand1);
        Term[] terms2 = parsePolynomial(summand2);

        if (terms1 == null || terms2 == null)
            return "Incorrect polynomials format";

        Term[] result = simplify(combineTerms(terms1, terms2));

        return writer.writePolynomial(result);
    }

    public String sub(String minuend, String subtrahend) {
        Term[] terms1 = parsePolynomial(minuend);
        Term[] terms2 = parsePolynomial(subtrahend);

        if (terms1 == null || terms2 == null)
            return "Incorrect polynomials format";

        for (int i = 0; i < terms2.length; i++) {
            terms2[i].coefficient *= -1;
        }

        Term[] result = simplify(combineTerms(terms1, terms2));

        return writer.writePolynomial(result);
    }

    public String mul(String multiplier1, String multiplier2) {
        Term[] terms1 = parsePolynomial(multiplier1);
        Term[] terms2 = parsePolynomial(multiplier2);

        if (terms1 == null || terms2 == null)
            return "Incorrect polynomials format";

        ArrayList<Term> product = new ArrayList<Term>();

        for (int i = 0; i < terms1.length; i++) {
            for (int j = 0; j < terms2.length; j++) {
                Term term = new Term(0, 0);
                term.setValues(terms1[i].coefficient * terms2[j].coefficient, terms1[i].degree + terms2[j].degree);
                product.add(term);
            }
        }

        Term[] result = simplify(product.toArray(new Term[product.size()]));

        return writer.writePolynomial(result);
    }

    private Term[] parsePolynomial(String polynomial) {
        Term[] terms = null;

        try {
            terms = parser.parsePolynomial(polynomial);
        } catch (NumberFormatException e) {
        }
        return terms;
    }

    private Term[] simplify(Term[] terms) {
        ArrayList<Term> simplifiedTerms = new ArrayList<Term>();

        for (int i = 0; i < terms.length; i++) {
            boolean isFound = false;
            for (int j = 0; j < simplifiedTerms.size(); j++) {
                if (simplifiedTerms.get(j).degree == terms[i].degree) {
                    simplifiedTerms.get(j).coefficient += terms[i].coefficient;
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                simplifiedTerms.add(terms[i]);
            }
        }

        return simplifiedTerms.toArray(new Term[simplifiedTerms.size()]);
    }

    private Term[] combineTerms(Term[] terms1, Term[] terms2) {
        Term[] bothTerms = new Term[terms1.length + terms2.length];
        System.arraycopy(terms1, 0, bothTerms, 0, terms1.length);
        System.arraycopy(terms2, 0, bothTerms, terms1.length, terms2.length);
        return bothTerms;
    }

    private PolynomialParser parser = null;
    private PolynomialWriter writer = null;
}
