package ru.unn.agile.determinant.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

public class DeterminantViewModelTest {

    private DeterminantViewModel viewModel;

    @Before
    public void setUp() {
        MockLogger logger = new MockLogger();
        viewModel = new DeterminantViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getMatrix());
        assertEquals("", viewModel.getMatrixSize());
        assertEquals(DeterminantViewModel.Status.WAITING, viewModel.getStatus());
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(DeterminantViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.Calculate();

        assertEquals(DeterminantViewModel.Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        viewModel.setMatrixSize("2");
        viewModel.setMatrix("1 2\n3 4");

        viewModel.handleKey();

        assertEquals(DeterminantViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("a");
        viewModel.handleKey();
        assertTrue(viewModel.status.contains(DeterminantViewModel.Status.INCORRECT_ITEM));
    }

    @Test
    public void canReportLessRows() {
        viewModel.setMatrixSize("2");
        viewModel.setMatrix("2\n1\n2");
        viewModel.handleKey();
        assertEquals(DeterminantViewModel.Status.LESS_ROWS, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("a");
        viewModel.handleKey();
        viewModel.setMatrix("1.0");
        viewModel.handleKey();

        assertEquals(DeterminantViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void isCalculateButtonDisabledInitially() {
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWhenFormatIsBad() {
        viewModel.isCalculateButtonEnabled = true;
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("a");

        viewModel.handleKey();

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.setMatrixSize("2");
        viewModel.setMatrix("10");

        viewModel.handleKey();

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("10");

        viewModel.handleKey();

        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canCalculateDeterminant1x1()
    {
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("10");

        viewModel.Calculate();

        assertEquals("Determinant = 10.0", viewModel.status);
    }

    @Test
    public void canCalculateDeterminant3x3() {
        viewModel.setMatrixSize("3");
        viewModel.setMatrix("10 -7 0\n-3 6 2\n5 -1 5");

        viewModel.Calculate();

        assertEquals("Determinant = 145.0", viewModel.status);
    }


    @Test
    public void canCreateModelWithLogger() {
        ILogger logger = new MockLogger();
        DeterminantViewModel determinantViewModel = new DeterminantViewModel(logger);
        assertNotNull(determinantViewModel);
    }

    @Test
    public void canCreateModelWithNullLogger() {
        try
        {
            new DeterminantViewModel(null);
            fail("Exception wasn't thrown");
        }
        catch (IllegalArgumentException ex)
        {
            assertEquals("Logger object is null", ex.getMessage());
        }
        catch (Exception ex)
        {
            fail("Invalid exception type");
        }
    }

    @Test
    public void isLogEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isLogSizeCorrect()  {
        viewModel.clearLog();
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("10");
        viewModel.Calculate();
        viewModel.Calculate();
        viewModel.Calculate();
        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void isNotLoggedWhenDataNotChanged() {
        viewModel.clearLog();
        viewModel.setMatrixSize("1");
        viewModel.setMatrix("10");
        viewModel.handleKey();

        viewModel.setMatrixSize("1");
        viewModel.setMatrix("10");
        viewModel.handleKey();
        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void isLoggedFinishEditing() {
        viewModel.clearLog();
        viewModel.setMatrixSize("2");
        viewModel.setMatrix("1 1\n1 1");
        viewModel.handleKey();
        assertTrue(viewModel.getLog().get(0).indexOf(DeterminantViewModel.LogMessages.READY_MESSAGE) >= 0);
    }

    @Test
    public void isLoggedCalculating() {
        viewModel.clearLog();
        viewModel.setMatrixSize("2");
        viewModel.setMatrix("1 1\n1 1");
        viewModel.Calculate();
        assertTrue(viewModel.getLog().get(0).indexOf(DeterminantViewModel.LogMessages.CALCULATE_MESSAGE) >= 0);
    }

    @Test
    public void isNotLoggedPartialInput() {
        viewModel.clearLog();
        viewModel.setMatrixSize("2");
        viewModel.setMatrix("1 1");
        viewModel.handleKey();
        assertEquals(0, viewModel.getLog().size());
    }
}
