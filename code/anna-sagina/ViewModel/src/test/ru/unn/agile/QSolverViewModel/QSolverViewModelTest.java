package ru.unn.agile.QSolverViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class QSolverViewModelTest {

    private QSolverViewModel viewModel;

    @Before
    public void setUp() {
       ILogger logger = new FakeLogger();
        viewModel = new QSolverViewModel(logger);
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

    @Test
    public void canCreateViewModelWithLogger(){
        assertNotNull(viewModel);
    }

    @Test(expected = NullPointerException.class)
    public void whenCreateViewModelWithNullLoggerExceptionIsThrown(){
        QSolverViewModel viewModelLogged = new QSolverViewModel(null);
    }

    @Test
    public void isLogEmptyInBeginning(){
        assertEquals(0,viewModel.getLog().size());
    }

    @Test
    public void doesStartValidationMessageLogged(){
        viewModel.a = "0";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        int position  = viewModel.getLog().get(0).indexOf(QSolverViewModel.Message.START_VALIDATION);
        assertTrue(position>=0);
    }

    @Test
    public void doesFinishValidationMessageLogged(){
        viewModel.a = "0";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        List<String> log = viewModel.getLog();
        String message =  QSolverViewModel.Message.VALIDATION_FINISHED;
        int position  = log.get(log.size()-1).indexOf(message);
        assertTrue(position>=0);
    }

    @Test
    public void isAllCoefficientInputLogged(){
        viewModel.a = "0";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        List<String> log = viewModel.getLog();
        String message =  QSolverViewModel.Message.INPUT_IS +"a = " + 0 + "; b = " + 0 + "; c = " + 3;
        int position  = log.get(1).indexOf(message);
        assertTrue(position>=0);
    }

    @Test
    public void badCoefficientAreLogged(){
        viewModel.a = "asd";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        List<String> log = viewModel.getLog();
        String message =  QSolverViewModel.Message.VALIDATION_FAILED;
        int position  = log.get(log.size()- 2).indexOf(message);
        assertTrue(position>=0);
    }

    @Test
    public void isSolvingStartLogged(){
        viewModel.a = "9";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        List<String> log = viewModel.getLog();
        String message =  QSolverViewModel.Message.START_SOLVER;
        int position  = log.get(3).indexOf(message);
        assertTrue(position>=0);
    }

    @Test
    public void isSolvingEndLogged(){
        viewModel.a = "9";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        List<String> log = viewModel.getLog();
        String message =  QSolverViewModel.Message.SOLVER_FINISHED;
        int position  = log.get(log.size()-1).indexOf(message);
        assertTrue(position>=0);
    }

    @Test
    public void resultIsLogged(){
        viewModel.a = "9";
        viewModel.b = "0";
        viewModel.c = "3";
        viewModel.setCoefficientValue();
        viewModel.RunSolver();
        List<String> log = viewModel.getLog();
        String message =  viewModel.result;
        int position  = log.get(log.size()- 2).indexOf(message);
        assertTrue(position>=0);
    }


}
