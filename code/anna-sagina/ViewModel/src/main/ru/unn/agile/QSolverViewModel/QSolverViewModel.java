package ru.unn.agile.QSolverViewModel;

import ru.unn.agile.quadsolver.QuadraticEquationSolver;

import java.security.InvalidParameterException;

public class QSolverViewModel {
    public boolean isSolveButtonEnabled = false;
    public String result = "";
    public String a;
    public String b;
    public String c;

    public QSolverViewModel() {
        a = "0";
        b = "0";
        c = "0";
    }

    public void setCoefficientValue() {
        try {
            Double.parseDouble(a);
            Double.parseDouble(b);
            Double.parseDouble(c);
            this.result = "";
            isSolveButtonEnabled = true;
        } catch (NumberFormatException e) {
            result = Message.INVALID_COEFFICIENT;
            isSolveButtonEnabled = false;
        }
    }

    public void RunSolver() {
        try {
            double[] roots;
            roots = QuadraticEquationSolver.solve(Double.parseDouble(a), Double.parseDouble(b), Double.parseDouble(c));
            switch (roots.length) {
                case 0:
                    result = Message.NO_ROOTS_RESULT;
                    break;
                case 1:
                    result = "x = " + roots[0];
                    break;
                case 2:
                    result = "x1 = " + roots[0] + "   x2 = " + roots[1];
                    break;
            }

        } catch (InvalidParameterException e) {
            result = "Oops.. something is wrong: " + e.getMessage();
        }
    }

    public class Message {
        public static final String INVALID_COEFFICIENT = "Invalid coefficient found! Please don't worry and check your input..";
        public static final String NO_ROOTS_RESULT = "I'm so sorry.. Your equal has no real roots at all!";
    }
}
