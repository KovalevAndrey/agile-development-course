package ru.unn.agile.QSolverViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;


public class QSolverViewModelTest {

    private QSolverViewModel qSolverViewModel;

    @Before
    public void setUp()
    {
       qSolverViewModel= new QSolverViewModel();
    }

    @After
    public void tearDown()
    {
       qSolverViewModel = null;
    }

    @Test
    public void isSolveButtonDisablesAfterFirstFormLoading() {
       assertFalse(qSolverViewModel.isSolveButtonEnabled );
    }

    @Test
    public void isResultEmptyAfterFirstFormLoading() {
        assertEquals("",qSolverViewModel.result);
    }

    @Test
    public void isSolveButtonEnabledAfterCorrectDataInput() {
        qSolverViewModel.a = "1";
        qSolverViewModel.b = "1";
        qSolverViewModel.c = "1";
        qSolverViewModel.setCoefficientValue();

        assertTrue(qSolverViewModel.isSolveButtonEnabled);
    }

    @Test
    public void invalidCoefficientInputShowsErrMessage() {
        qSolverViewModel.a  = "1";
        qSolverViewModel.b  = "qq";
        qSolverViewModel.c  = "2";
        qSolverViewModel.setCoefficientValue();

        assertEquals("Invalid coefficient found! Please don't worry and check your input..", qSolverViewModel.result);
    }

    @Test
    public void whenAnyCoefficientIsInvalidSolveButtonIsDisabled() {
        qSolverViewModel.a  = "1";
        qSolverViewModel.b  = "qq";
        qSolverViewModel.c  = "2";
        qSolverViewModel.setCoefficientValue();

        assertFalse(qSolverViewModel.isSolveButtonEnabled);
    }

    @Test
    public void solveEquationWithoutRootsGivesNoRootsMessage() {
        qSolverViewModel.a  = "1";
        qSolverViewModel.b  = "1";
        qSolverViewModel.c  = "3";
        qSolverViewModel.setCoefficientValue();
        qSolverViewModel.RunSolver();

        assertEquals("I'm so sorry.. Your equal has no real roots at all!", qSolverViewModel.result);
    }

    @Test
    public void solveEquationWithOneRootGivesOneRootMessage() {
        qSolverViewModel.a  = "1";
        qSolverViewModel.b  = "-4";
        qSolverViewModel.c  = "4";
        qSolverViewModel.setCoefficientValue();
        qSolverViewModel.RunSolver();

        assertEquals("x = 2.0", qSolverViewModel.result);
    }

    @Test
    public void solveEquationWithTwoRootsGivesTwoRootMessage() {
        qSolverViewModel.a  = "1";
        qSolverViewModel.b  = "-3";
        qSolverViewModel.c  = "2";
        qSolverViewModel.setCoefficientValue();
        qSolverViewModel.RunSolver();

        assertEquals("x1 = 1.0   x2 = 2.0", qSolverViewModel.result);
    }

    @Test
   public void errorResultWhenAllCoefficientsAreZero() {
        qSolverViewModel.a  = "0";
        qSolverViewModel.b  = "0";
        qSolverViewModel.c  = "0";
        qSolverViewModel.setCoefficientValue();
        qSolverViewModel.RunSolver();

        assertEquals("Oops.. something is wrong: All arguments cannot be zero!", qSolverViewModel.result);
    }

    @Test
    public void errorResultWhenOnlyCisNonZero() {
        qSolverViewModel.a  = "0";
        qSolverViewModel.b  = "0";
        qSolverViewModel.c  = "3";
        qSolverViewModel.setCoefficientValue();
        qSolverViewModel.RunSolver();

        assertEquals("Oops.. something is wrong: Equation cannot be solved!", qSolverViewModel.result);
    }
}
