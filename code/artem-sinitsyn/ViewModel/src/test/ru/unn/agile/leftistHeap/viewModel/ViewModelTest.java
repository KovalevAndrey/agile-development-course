package ru.unn.agile.leftistHeap.viewModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;
    private String key;
    private String value;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
        key = "1";
        value = "First";
    }

    @Test
    public void areDefaultValuesCorrect() {
        assertEquals("0", viewModel.keyAdd);
        assertEquals("Zero", viewModel.valueAdd);
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
        assertEquals("OK", viewModel.status);
    }

    @Test
    public void cannotConvertInvalidInput(){
        key = "-1";
        value = null;
        viewModel.addToLeftistHeap(key ,value);
        assertEquals("Invalid input", viewModel.status);
    }

    @Test
    public void canConvertValidInput(){
        viewModel.addToLeftistHeap(key ,value);
        assertEquals("New element is successfully added", viewModel.status);
    }

    @Test
    public void canAddToLeftistHeap() {
        viewModel.addToLeftistHeap(key, value);
        assertEquals("New element is successfully added", viewModel.status);
    }

    @Test
    public void cannotGetMinFromEmptyHeap() {
        viewModel.getMinFromLeftistHeap();
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
        assertEquals("Cant get min from empty heap", viewModel.status);
    }

    @Test
    public void canGetMinFromHeap() {
        viewModel.addToLeftistHeap(key ,value);
        viewModel.getMinFromLeftistHeap();
        assertEquals(key, viewModel.keyGetDel);
        assertEquals(value, viewModel.valueGetDel);
        assertEquals("Min is successfully got", viewModel.status);
    }

    @Test
    public void cannotDeleteMinFromEmptyHeap() {
        viewModel.deleteMinFromLeftistHeap();
        assertEquals("", viewModel.keyGetDel);
        assertEquals("", viewModel.valueGetDel);
        assertEquals("Cant delete min from empty heap", viewModel.status);
    }

    @Test
    public void canDeleteMinFromLeftistHeap() {
        viewModel.addToLeftistHeap(key ,value);
        viewModel.deleteMinFromLeftistHeap();
        assertEquals(key, viewModel.keyGetDel);
        assertEquals(value, viewModel.valueGetDel);
        assertEquals("Min is successfully deleted", viewModel.status);
    }
}
