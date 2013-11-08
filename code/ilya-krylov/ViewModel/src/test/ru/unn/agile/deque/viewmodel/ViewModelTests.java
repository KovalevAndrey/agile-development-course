package ru.unn.agile.deque.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetInitialState(){
        assertEquals("", viewModel.maximumSize);
        assertEquals("", viewModel.dequeRepresentation);
        assertEquals("", viewModel.pushedValue);
        assertFalse(viewModel.isPushTextFieldEnabled);
        assertFalse(viewModel.isActButtonEnabled);
        assertFalse(viewModel.isActionsComboBoxEnabled);
        assertEquals("Please, choose size and create the deque", viewModel.status);
    }

    @Test
    public void isStatusBadParameterWhenCreateDequeWithEmptyField(){
        viewModel.maximumSize = "";
        viewModel.createDeque();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
      public void isStatusBadParameterWhenCreateDequeWithInvalidField(){
        viewModel.maximumSize = "fds3";
        viewModel.createDeque();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
    public void areElementsOfFormEnableWhenDequeIsCreated(){
        viewModel.maximumSize = "10";
        viewModel.createDeque();
        assertTrue(viewModel.isActionsComboBoxEnabled);
        assertTrue(viewModel.isActButtonEnabled);
        assertTrue(viewModel.isPushTextFieldEnabled);
    }

    @Test
    public void isStatusDoActionWhenDequeIsCreated(){
        viewModel.maximumSize = "10";
        viewModel.createDeque();
        assertEquals(ViewModel.Status.DO_ACTION, viewModel.status);
    }

    @Test
    public void whenMaximumSizeIsNegative(){
        viewModel.maximumSize = "-1";
        viewModel.createDeque();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
    public void whenPushFrontEmptyElement(){
        viewModel.pushedValue = "";
        viewModel.action = ViewModel.Action.PUSH_FRONT;
        viewModel.pushFront();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
    public void whenPushBackEmptyElement(){
        viewModel.pushedValue = "";
        viewModel.action = ViewModel.Action.PUSH_BACK;
        viewModel.pushBack();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
    public void whenPushFrontInvalidElement(){
        viewModel.pushedValue = "423sfaa3aa";
        viewModel.action = ViewModel.Action.PUSH_FRONT;
        viewModel.pushFront();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
    public void whenPushBackInvalidElement(){
        viewModel.pushedValue = "423sfaa3aa";
        viewModel.action = ViewModel.Action.PUSH_BACK;
        viewModel.pushBack();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.status);
    }

    @Test
    public void whenPopFrontAndDequeIsEmpty(){
        viewModel.maximumSize = "5";
        viewModel.action = ViewModel.Action.PUSH_FRONT;
        viewModel.createDeque();
        viewModel.popFront();
        assertEquals(ViewModel.Status.DEQUE_IS_EMPTY, viewModel.status);
    }

    @Test
    public void whenPopBackAndDequeIsEmpty(){
        viewModel.maximumSize = "5";
        viewModel.action = ViewModel.Action.POP_BACK;
        viewModel.createDeque();
        viewModel.popBack();
        assertEquals(ViewModel.Status.DEQUE_IS_EMPTY, viewModel.status);
    }

    @Test
    public void whenPushFrontAndDequeIsFull(){
        viewModel.maximumSize = "2";
        viewModel.pushedValue = "5";
        viewModel.action = ViewModel.Action.PUSH_FRONT;
        viewModel.createDeque();
        viewModel.pushFront();
        viewModel.pushFront();
        viewModel.pushFront();
        assertEquals(ViewModel.Status.DEQUE_IS_FULL, viewModel.status);
    }

    @Test
    public void whenPushBackAndDequeIsFull(){
        viewModel.maximumSize = "2";
        viewModel.pushedValue = "5";
        viewModel.action = ViewModel.Action.PUSH_BACK;
        viewModel.createDeque();
        viewModel.pushBack();
        viewModel.pushBack();
        viewModel.pushBack();
        assertEquals(ViewModel.Status.DEQUE_IS_FULL, viewModel.status);
    }
}
