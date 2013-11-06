package ru.unn.agile.QSolverViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class QSolverViewModelTest {

    private QSolverViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new QSolverViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void isSolveButtonDisablesAfterFirstFormLoading() {
        assertFalse(viewModel.isSolveButtonEnabled);
    }

    @Test
    public void isResultEmptyAfterFirstFormLoading() {
        assertEquals("", viewModel.result);
    }

    @Test
    public void isSolveButtonEnabledAfterCorrectDataInput() {
        viewModel.a = "1";
        viewModel.b = "1";
        viewModel.c = "1";
        viewModel.setCoefficientValue();
        assertTrue(viewModel.isSolveButtonEnabled);
    }

    @Test
    public void invalidCoefficientInputShowsErrMessage() {
        viewModel.a = "1";
        viewModel.b = "qq";
        viewModel.c = "2";
        viewModel.setCoefficientValue();
        assertEquals(QSolverViewModel.Message.INVALID_COEFFICIENT, viewModel.result);
    }

    @Test
    public void whenAnyCoefficientIsInvalidSolveButtonIsDisabled() {
        viewModel.a = "1";
        viewModel.b = "qq";
        viewModel.c = "2";
        viewModel.setCoefficientValue();
        assertFalse(viewModel.isSolveButtonEnabled);
    }

    @Test
    public void solveEquationWithoutRootsGivesNoRootsMessage() {
        viewModel.a = "1";
        viewModel.b = "1";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        assertEquals(QSolverViewModel.Message.NO_ROOTS_RESULT, viewModel.result);
    }

    @Test
    public void solveEquationWithOneRootGivesOneRootMessage() {
        viewModel.a = "1";
        viewModel.b = "-4";
        viewModel.c = "4";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        assertEquals("x = 2.0", viewModel.result);
    }

    @Test
    public void solveEquationWithTwoRootsGivesTwoRootMessage() {
        viewModel.a = "1";
        viewModel.b = "-3";
        viewModel.c = "2";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        assertEquals("x1 = 1.0   x2 = 2.0", viewModel.result);
    }

    @Test
    public void errorResultWhenAllCoefficientsAreZero() {
        viewModel.a = "0";
        viewModel.b = "0";
        viewModel.c = "0";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        assertEquals("Oops.. something is wrong: All arguments cannot be zero!", viewModel.result);
    }

    @Test
    public void errorResultWhenOnlyCisNonZero() {
        viewModel.a = "0";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        assertEquals("Oops.. something is wrong: Equation cannot be solved!", viewModel.result);
    }
}
