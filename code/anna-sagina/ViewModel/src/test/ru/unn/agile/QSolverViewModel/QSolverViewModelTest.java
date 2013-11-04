package ru.unn.agile.QSolverViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;


public class QSolverViewModelTest {

    private  String errMessage = "Invalid coefficient found! Please don't worry and check your input..";
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
        qSolverViewModel.setA("1");
        qSolverViewModel.setB("1");
        qSolverViewModel.setC("1");

        assertTrue(qSolverViewModel.isSolveButtonEnabled);
    }

    @Test
    public void invalidCoefficientAShowsErrMessage() {
        qSolverViewModel.setA("qq");
        assertEquals(errMessage, qSolverViewModel.result);
    }


    @Test
    public void invalidCoefficientBShowsErrMessage() {
        qSolverViewModel.setB("qq");
        assertEquals(errMessage, qSolverViewModel.result);
    }

    @Test
    public void invalidCoefficientCShowsErrMessage() {
        qSolverViewModel.setC("q(");
        assertEquals(errMessage, qSolverViewModel.result);
     }

    @Test
    public void invalidCoefficientASolveButtonIsDisabled() {
        qSolverViewModel.setA("1");
        qSolverViewModel.setA("qq");
        assertFalse(qSolverViewModel.isSolveButtonEnabled);
    }

    @Test
    public void invalidCoefficientBSolveButtonIsDisabled() {
        qSolverViewModel.setA("1");
        qSolverViewModel.setB("qq");
        assertFalse(qSolverViewModel.isSolveButtonEnabled);
    }

    @Test
    public void invalidCoefficientCSolveButtonIsDisabled() {
        qSolverViewModel.setC("qq");
        assertFalse(qSolverViewModel.isSolveButtonEnabled);
    }

    @Test
    public void solveEquationWithoutRootsGivesNoRootsMessage() {
        qSolverViewModel.setA("1");
        qSolverViewModel.setB("1");
        qSolverViewModel.setC("3");
        qSolverViewModel.RunSolver();

        assertEquals("I'm so sorry.. Your equal has no real roots at all!", qSolverViewModel.result);
    }

    @Test
    public void solveEquationWithOneRootGivesOneRootMessage() {
        qSolverViewModel.setA("1");
        qSolverViewModel.setB("-4");
        qSolverViewModel.setC("4");
        qSolverViewModel.RunSolver();

        assertEquals("The equal has only one root:\n x = 2.0", qSolverViewModel.result);
    }

    @Test
    public void solveEquationWithTwoRootsGivesTwoRootMessage() {
        qSolverViewModel.setA("1");
        qSolverViewModel.setB("-3");
        qSolverViewModel.setC("2");
        qSolverViewModel.RunSolver();

        assertEquals("The equal has two real roots:\n x = 1.0   x = 2.0", qSolverViewModel.result);
    }

    @Test
   public void errorResultWhenAllCoefficientsAreZero() {
        qSolverViewModel.setA("0");
        qSolverViewModel.setB("0");
        qSolverViewModel.setC("0");
        qSolverViewModel.RunSolver();

        assertEquals("Oops.. something is wrong: All arguments cannot be zero!", qSolverViewModel.result);
    }

    @Test
    public void errorResultWhenOnlyCisNonZero() {
        qSolverViewModel.setA("0");
        qSolverViewModel.setB("0");
        qSolverViewModel.setC("2");
        qSolverViewModel.RunSolver();

        assertEquals("Oops.. something is wrong: Equation cannot be solve!", qSolverViewModel.result);
    }
}
