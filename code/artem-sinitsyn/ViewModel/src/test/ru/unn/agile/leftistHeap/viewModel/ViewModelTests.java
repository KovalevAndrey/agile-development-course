package ru.unn.agile.leftistHeap.viewModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import ru.unn.agile.leftistHeap.viewModel.ViewModel.Status;
import ru.unn.agile.leftistHeap.viewModel.ViewModel.LogMessages;

public class ViewModelTests {
    private FakeLogger fakeLogger;
    protected ViewModel viewModel;

    @Before
    public void setUp() {
        fakeLogger = new FakeLogger();
        viewModel = new ViewModel(fakeLogger);
    }

    @Test (expected = IllegalArgumentException.class)
    public void cantAcceptNullLogger() {
        viewModel = new ViewModel(null);
    }

    @Test
    public void canAcceptNotNullLogger() {
        assertNotNull(viewModel);
    }

    @Test
    public void areDefaultValuesCorrect() {
        assertEquals("0", viewModel.keyAdd);
        assertEquals("Zero", viewModel.valueAdd);
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
        assertEquals("", viewModel.status);
    }

    @Test
    public void cantConvertInvalidInput() {
        viewModel.keyAdd = "-1";
        viewModel.valueAdd = null;
        viewModel.addToLeftistHeap();
        assertEquals(Status.BAD_INPUT, viewModel.status);
    }

    @Test
    public void canConvertValidInput(){
        viewModel.addToLeftistHeap();
        assertEquals(Status.ADD_OK, viewModel.status);
    }

    @Test
    public void canAddToLeftistHeap() {
        viewModel.addToLeftistHeap();
        assertEquals(Status.ADD_OK, viewModel.status);
    }

    @Test
    public void areGetDelFieldsClearAfterAdd() {
        viewModel.addToLeftistHeap();
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
    }

    @Test
    public void cantGetMinFromEmptyHeap() {
        viewModel.getMinFromLeftistHeap();
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
        assertEquals(Status.GET_BAD, viewModel.status);
    }

    @Test
    public void canGetMinFromHeap() {
        viewModel.addToLeftistHeap();
        viewModel.getMinFromLeftistHeap();
        assertEquals("0", viewModel.keyGetDel);
        assertEquals("Zero", viewModel.valueGetDel);
        assertEquals(Status.GET_OK, viewModel.status);
    }

    @Test
    public void cantDeleteMinFromEmptyHeap() {
        viewModel.deleteMinFromLeftistHeap();
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
        assertEquals(Status.DEL_BAD, viewModel.status);
    }

    @Test
    public void canDeleteMinFromLeftistHeap() {
        viewModel.addToLeftistHeap();
        viewModel.deleteMinFromLeftistHeap();
        assertEquals("0", viewModel.keyGetDel);
        assertEquals("Zero", viewModel.valueGetDel);
        assertEquals(Status.DEL_OK, viewModel.status);
    }

    @Test
    public void areAddFieldsClearAfterGetOrDelete() {
        viewModel.getMinFromLeftistHeap();
        assertEquals("", viewModel.keyAdd);
        assertEquals("", viewModel.valueAdd);
        viewModel.deleteMinFromLeftistHeap();
        assertEquals("", viewModel.keyAdd);
        assertEquals("", viewModel.valueAdd);
    }

    @Test
    public void canGetViewModelLog() {
        List<String> log = viewModel.getLog();
        assertNotNull(log);
    }

    @Test
    public void isLogEmptyAtStart() {
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    private void assertLogSize() {
        List<String> log = viewModel.getLog();
        assertTrue(log.size() == 2);
    }

    private void assertLastTwoLogMessages(String first, String second) {
        List<String> log = viewModel.getLog();
        int size = log.size();
        String message = log.get(size - 2);
        assertEquals(message, first);
        message = log.get(size - 1);
        assertEquals(message, second);
    }

    @Test
    public void doesAddWriteAnythingToLog() {
        viewModel.addToLeftistHeap();
        assertLogSize();
    }

    @Test
     public void isProperMessageWrittenOnSuccessfulAdd() {
        viewModel.addToLeftistHeap();
        assertLastTwoLogMessages(LogMessages.ADD + "( 0, Zero )", LogMessages.SUCCESS);
    }

    @Test
    public void isProperMessageWrittenOnFailedAdd() {
        viewModel.keyAdd = "q";
        viewModel.addToLeftistHeap();
        assertLastTwoLogMessages(LogMessages.ADD + "( q, Zero )", LogMessages.FAIL);
    }

    @Test
    public void doesGetMinWriteAnythingToLog() {
        viewModel.getMinFromLeftistHeap();
        assertLogSize();
    }

    @Test
    public void isProperMessageWrittenOnSuccessfulGetMin() {
        viewModel.addToLeftistHeap();
        viewModel.getMinFromLeftistHeap();
        assertLastTwoLogMessages(LogMessages.GET, LogMessages.SUCCESS);
    }

    @Test
    public void isProperMessageWrittenOnFailedGetMin() {
        viewModel.getMinFromLeftistHeap();
        assertLastTwoLogMessages(LogMessages.GET, LogMessages.FAIL);
    }

    @Test
    public void doesDeleteMinWriteAnythingToLog() {
        viewModel.deleteMinFromLeftistHeap();
        assertLogSize();
    }

    @Test
    public void isProperMessageWrittenOnSuccessfulDeleteMin() {
        viewModel.addToLeftistHeap();
        viewModel.deleteMinFromLeftistHeap();
        assertLastTwoLogMessages(LogMessages.DELETE, LogMessages.SUCCESS);
    }

    @Test
    public void isProperMessageWrittenOnFailedDeleteMin() {
        viewModel.deleteMinFromLeftistHeap();
        assertLastTwoLogMessages(LogMessages.DELETE, LogMessages.FAIL);
    }
}