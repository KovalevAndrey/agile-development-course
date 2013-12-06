package ru.unn.agile.determinant.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeterminantViewModelTest {

    private DeterminantViewModel viewModel;

    @Before
    public void setUp() {
        MockLogger logger = new MockLogger();
        viewModel = new DeterminantViewModel(new MockLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.matrix);
        assertEquals("", viewModel.matrixSize);
        assertEquals(DeterminantViewModel.Status.WAITING, viewModel.status);
        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isStatusWaitingInTheBeginning() {
        assertEquals(DeterminantViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusWaitingWhenCalculateWithEmptyFields() {
        viewModel.Calculate();

        assertEquals(DeterminantViewModel.Status.WAITING, viewModel.status);
    }

    @Test
    public void isStatusReadyWhenFieldsAreFill() {
        viewModel.matrixSize = "2";
        viewModel.matrix = "1 2\n3 4";

        viewModel.handleKey();

        assertEquals(DeterminantViewModel.Status.READY, viewModel.status);
    }

    @Test
    public void canReportBadFormat() {
        viewModel.matrixSize = "1";
        viewModel.matrix = "a";
        viewModel.handleKey();
        assertTrue(viewModel.status.contains(DeterminantViewModel.Status.INCORRECT_ITEM));
    }

    @Test
    public void canReportLessRows() {
        viewModel.matrixSize = "2";
        viewModel.matrix = "2\n1\n2";
        viewModel.handleKey();
        assertEquals(DeterminantViewModel.Status.LESS_ROWS, viewModel.status);
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.matrixSize = "1";
        viewModel.matrix = "a";
        viewModel.handleKey();
        viewModel.matrix = "1.0";
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
        viewModel.matrixSize = "1";
        viewModel.matrix = "a";

        viewModel.handleKey();

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonDisabledWithIncompleteInput() {
        viewModel.matrixSize = "2";
        viewModel.matrix = "10";

        viewModel.handleKey();

        assertEquals(false, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void isCalculateButtonEnabledWithCorrectInput() {
        viewModel.matrixSize = "1";
        viewModel.matrix = "10";

        viewModel.handleKey();

        assertEquals(true, viewModel.isCalculateButtonEnabled);
    }

    @Test
    public void canCalculateDeterminant1x1()
    {
        viewModel.matrixSize = "1";
        viewModel.matrix = "10";

        viewModel.Calculate();

        assertEquals("Determinant = 10.0", viewModel.status);
    }

    @Test
    public void canCalculateDeterminant3x3() {
        viewModel.matrixSize = "3";
        viewModel.matrix = "10 -7 0\n-3 6 2\n5 -1 5";

        viewModel.Calculate();

        assertEquals("Determinant = 145.0", viewModel.status);
    }


    @Test
    public void canCreateModelWithLogger() {
        ILogger logger = new MockLogger();
        DeterminantViewModel determinantViewModel = new DeterminantViewModel(logger);
        assertNotNull(determinantViewModel);
    }
}
