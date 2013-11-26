package ru.unn.agile.Polynomial.viewmodel;

import ru.unn.agile.Polynomial.Term;

import java.util.ArrayList;

class PolynomialParser {
    public Term parseTerm(String input) {
        Term term = new Term(0, 0);

        if (input.isEmpty()) {
            term.setValues(0, 0);
        } else {
            if (input.contains("x")) {
                String[] tokens = input.split("x");

                if (tokens.length == 0) {
                    term.setValues(1, 1);
                } else {
                    if (tokens.length == 1) {
                        term.setValues(Integer.parseInt(tokens[0]), 1);
                    } else {
                        if (tokens[0].isEmpty()) {
                            term.setValues(1, Integer.parseInt(tokens[1].substring(1)));
                        } else {
                            if (tokens[1].isEmpty()) {
                                term.setValues(Integer.parseInt(tokens[0]), 1);
                            } else {
                                term.setValues(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1].substring(1)));
                            }

                        }
                    }
                }
            } else {
                term.setValues(Integer.parseInt(input), 0);
            }
        }

        return term;
    }

    public Term[] parsePolynomial(String polynomial) {
        if (polynomial.isEmpty())
            return new Term[0];

        String[] tokens = polynomial.split("[ \\+]");
        ArrayList<Term> terms = new ArrayList<Term>();


        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].isEmpty()) {
                terms.add(parseTerm(tokens[i]));
            }
        }

        return terms.toArray(new Term[terms.size()]);
    }
}
