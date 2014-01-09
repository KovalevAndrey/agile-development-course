package ru.unn.agile.QSolverViewModel;

import ru.unn.agile.quadsolver.QuadraticEquationSolver;

import java.security.InvalidParameterException;
import java.util.List;

public class QSolverViewModel {
    public boolean isSolveButtonEnabled = false;
    public String result = "";
    public String a;
    public String b;
    public String c;
    private ILogger logger;

    public QSolverViewModel(ILogger logger) {
        if (logger == null)
            throw new NullPointerException("Logger cannot be null!");
        a = "0";
        b = "0";
        c = "0";
        this.logger = logger;
    }

    public void setCoefficientValue() {
        try {
            this.logger.addInfo(Message.START_VALIDATION);
            this.logger.addInfo(Message.INPUT_IS + "a = " + a + "; b = " +b + "; c = " + c);
            Double.parseDouble(a);
            Double.parseDouble(b);
            Double.parseDouble(c);
            this.result = "";
            isSolveButtonEnabled = true;
        } catch (NumberFormatException e) {
            this.logger.addError(Message.VALIDATION_FAILED);
            result = Message.INVALID_COEFFICIENT;
            isSolveButtonEnabled = false;
        }finally {
            this.logger.addInfo(Message.VALIDATION_FINISHED);
        }

    }

    public void RunSolver() {
        try {
            logger.addInfo(Message.START_SOLVER);
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

            logger.addInfo(result);

        } catch (InvalidParameterException e) {
            result = "Oops.. something is wrong: " + e.getMessage();
            logger.addError(Message.SOLVER_FAILED + result);
        } finally {
            logger.addInfo(Message.SOLVER_FINISHED);
        }
    }

    public List<String> getLog() {
            return this.logger.getAllLog();
    }

    public class Message {
        public static final String INVALID_COEFFICIENT = "Invalid coefficient found! Please don't worry and check your input..";
        public static final String NO_ROOTS_RESULT = "I'm so sorry.. Your equal has no real roots at all!";
        public static final String START_VALIDATION = "Validating parameters...";
        public static final String VALIDATION_FAILED = "Validation Failed! Incorrect Input.";
        public static final String INPUT_IS = "User input was: ";
        public static final String VALIDATION_FINISHED = "Validation finished...";
        public static final String START_SOLVER = "Solver started...";
        public static final String SOLVER_FAILED = "Solver Failed!";
        public static final String SOLVER_FINISHED = "Solver finished.";
    }
}
