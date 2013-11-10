package ru.unn.agile.Polynomial;

import java.util.ArrayList;
import java.util.Arrays;

public class PolynomialCalculator {

    public PolynomialCalculator() {
    }

    public Term[] add(Term[] summand1, Term[] summand2) throws Exception {
        if (summand1 == null || summand2 == null) {
            throw new Exception("The polynomial is equal to null");
        }

        Term[] polynomial1 = summand1.clone();
        Term[] polynomial2 = summand2.clone();

        Term[] result = simplify(combineTerms(polynomial1, polynomial2));

        return result;
    }

    public Term[] sub(Term[] minuend, Term[] subtrahend) throws Exception {
        if (minuend == null || subtrahend == null) {
            throw new Exception("The polynomial is equal to null");
        }

        Term[] polynomial1 = minuend.clone();
        Term[] polynomial2 = subtrahend.clone();

        for (int i = 0; i < polynomial2.length; i++) {
            polynomial2[i].coefficient *= -1;
        }

        Term[] result = simplify(combineTerms(polynomial1, polynomial2));

        return result;
    }

    public Term[] mul(Term[] multiplier1, Term[] multiplier2) throws Exception {
        if (multiplier1 == null || multiplier2 == null) {
            throw new Exception("The polynomial is equal to null");
        }

        Term[] polynomial1 = multiplier1.clone();
        Term[] polynomial2 = multiplier2.clone();

        ArrayList<Term> product = new ArrayList<Term>();

        for (int i = 0; i < polynomial1.length; i++) {
            for (int j = 0; j < polynomial2.length; j++) {
                Term term = new Term(0, 0);
                term.setValues(polynomial1[i].coefficient * polynomial2[j].coefficient, polynomial1[i].degree + polynomial2[j].degree);
                product.add(term);
            }
        }

        Term[] result = simplify(product.toArray(new Term[product.size()]));

        return result;
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
        Term[] result = simplifiedTerms.toArray(new Term[simplifiedTerms.size()]);
        Arrays.sort(result);
        return result;
    }

    private Term[] combineTerms(Term[] terms1, Term[] terms2) {
        Term[] bothTerms = new Term[terms1.length + terms2.length];
        System.arraycopy(terms1, 0, bothTerms, 0, terms1.length);
        System.arraycopy(terms2, 0, bothTerms, terms1.length, terms2.length);
        return bothTerms;
    }

}
