package ru.unn.agile.deque.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ViewModelTests {
    public ViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetInitialState(){
        assertEquals("", viewModel.getMaximumSize());
        assertEquals("", viewModel.getDequeRepresentation());
        assertEquals("", viewModel.getPushedValue());
        assertFalse(viewModel.getPushTextFieldEnabled());
        assertFalse(viewModel.getActButtonEnabled());
        assertFalse(viewModel.getActionsComboBoxEnabled());
        assertEquals("Please, choose size and create the deque", viewModel.getStatus());
    }

    @Test
    public void isStatusBadParameterWhenCreateDequeWithEmptyField(){
        viewModel.setMaximumSize("");
        viewModel.createDeque();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }


    @Test
      public void isStatusBadParameterWhenCreateDequeWithInvalidField(){
        viewModel.setMaximumSize("fds3");
        viewModel.createDeque();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }

    @Test
    public void areElementsOfFormEnableWhenDequeIsCreated(){
        viewModel.setMaximumSize("10");
        viewModel.createDeque();
        assertTrue(viewModel.getActionsComboBoxEnabled());
        assertTrue(viewModel.getActButtonEnabled());
        assertTrue(viewModel.getActionsComboBoxEnabled());
    }

    @Test
    public void isStatusDoActionWhenDequeIsCreated(){
        viewModel.setMaximumSize("10");
        viewModel.createDeque();
        assertEquals(ViewModel.Status.DO_ACTION, viewModel.getStatus());
    }

    @Test
    public void whenMaximumSizeIsNegative(){
        viewModel.setMaximumSize("-1");
        viewModel.createDeque();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }

    @Test
    public void whenPushFrontEmptyElement(){
        viewModel.setPushedValue("");
        viewModel.setAction(ViewModel.Action.PUSH_FRONT);
        viewModel.pushFront();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }

    @Test
    public void whenPushBackEmptyElement(){
        viewModel.setPushedValue("");
        viewModel.setAction(ViewModel.Action.PUSH_BACK);
        viewModel.pushBack();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }

    @Test
    public void whenPushFrontInvalidElement(){
        viewModel.setPushedValue("423sfaa3aa");
        viewModel.setAction(ViewModel.Action.PUSH_FRONT);
        viewModel.pushFront();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }

    @Test
    public void whenPushBackInvalidElement(){
        viewModel.setPushedValue("423sfaa3aa");
        viewModel.setAction(ViewModel.Action.PUSH_BACK);
        viewModel.pushBack();
        assertEquals(ViewModel.Status.BAD_PARAMETER, viewModel.getStatus());
    }

    @Test
    public void whenPopFrontAndDequeIsEmpty(){
        viewModel.setMaximumSize("5");
        viewModel.setAction(ViewModel.Action.PUSH_FRONT);
        viewModel.createDeque();
        viewModel.popFront();
        assertEquals(ViewModel.Status.DEQUE_IS_EMPTY, viewModel.getStatus());
    }

    @Test
    public void whenPopBackAndDequeIsEmpty(){
        viewModel.setMaximumSize("5");
        viewModel.setAction(ViewModel.Action.POP_BACK);
        viewModel.createDeque();
        viewModel.popBack();
        assertEquals(ViewModel.Status.DEQUE_IS_EMPTY, viewModel.getStatus());
    }

    @Test
    public void whenPushFrontAndDequeIsFull(){
        viewModel.setMaximumSize("2");
        viewModel.setPushedValue("5");
        viewModel.setAction(ViewModel.Action.PUSH_FRONT);
        viewModel.createDeque();
        viewModel.pushFront();
        viewModel.pushFront();
        viewModel.pushFront();
        assertEquals(ViewModel.Status.DEQUE_IS_FULL, viewModel.getStatus());
    }

    @Test
    public void whenPushBackAndDequeIsFull(){
        viewModel.setMaximumSize("2");
        viewModel.setPushedValue("5");
        viewModel.setAction(ViewModel.Action.PUSH_BACK);
        viewModel.createDeque();
        viewModel.pushBack();
        viewModel.pushBack();
        viewModel.pushBack();
        assertEquals(ViewModel.Status.DEQUE_IS_FULL, viewModel.getStatus());
    }

    @Test
    public void canCreateDequeViewModelWithLogger(){
        ViewModel viewModel1Logged = new ViewModel(new FakeLogger());
        assertNotNull(viewModel1Logged);
    }

    @Test
    public void isLogEmptyWhenDequeCreated(){
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doesExceptionAppearWhenNullWasPassed(){
        try{
            new ViewModel(null);
            fail("Exception was not thrown");
        }
        catch (IllegalArgumentException e){
            assertEquals("Null-logger cannot be used", e.getMessage());
        }
        catch (Exception e){
            fail("Illegal argument exception was not caught");
        }
    }

    @Test
    public void isLogNotEmptyWhenElementPushedFront(){
        viewModel.pushFront();
        assertFalse(viewModel.getLog().isEmpty());
    }

    @Test
    public void isLogNotEmptyWhenElementPushedBack(){
        viewModel.pushBack();
        assertFalse(viewModel.getLog().isEmpty());
    }

    @Test
    public void isLogNotEmptyWhenElementPoppedBack(){
        viewModel.setMaximumSize("10");
        viewModel.createDeque();
        viewModel.popBack();
        assertFalse(viewModel.getLog().isEmpty());
    }

    @Test
    public void isLogNotEmptyWhenElementPoppedFront(){
        viewModel.setMaximumSize("10");
        viewModel.createDeque();
        viewModel.popFront();
        assertFalse(viewModel.getLog().isEmpty());
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToPushFrontWhenDequeIsFull(){
        viewModel.setMaximumSize("0");
        viewModel.setPushedValue("5");
        viewModel.createDeque();
        viewModel.pushFront();
        assertTruePatternMatches("Pushing front of ", viewModel.getPushedValue(),
                " is impossible: deque is full");
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToPushBackWhenDequeIsFull(){
        viewModel.setMaximumSize("0");
        viewModel.setPushedValue("5");
        viewModel.createDeque();
        viewModel.pushBack();
        assertTruePatternMatches("Pushing back of ", viewModel.getPushedValue(),
                " is impossible: deque is full");
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToPopFrontWhenDequeIsEmpty(){
        viewModel.setMaximumSize("0");
        viewModel.createDeque();
        viewModel.popFront();
        assertTruePatternMatches("Popping front ", "", "is impossible: deque is empty");
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToPopBackWhenDequeIsEmpty(){
        viewModel.setMaximumSize("0");
        viewModel.createDeque();
        viewModel.popBack();
        assertTruePatternMatches("Popping back ", "", "is impossible: deque is empty");
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToPushFrontWhenInvalidParameter(){
        viewModel.setMaximumSize("0");
        viewModel.setPushedValue("hfsdyuib");
        viewModel.createDeque();
        viewModel.pushFront();
        assertTruePatternMatches("Pushing front of ", viewModel.getPushedValue(),
                " is impossible: invalid parameter");
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToPushBackWhenInvalidParameter(){
        viewModel.setMaximumSize("0");
        viewModel.setPushedValue("hfsdyuib");
        viewModel.createDeque();
        viewModel.pushBack();
        assertTruePatternMatches("Pushing back of ", viewModel.getPushedValue(),
                " is impossible: invalid parameter");
    }

    @Test
    public void doesLogContainMessageOfPushedFrontParameter(){
        viewModel.setMaximumSize("4");
        viewModel.setPushedValue("666");
        viewModel.createDeque();
        viewModel.pushFront();
        assertTruePatternMatches("Pushing front of ", viewModel.getPushedValue(),
                " is completed successfully");
    }

    @Test
    public void doesLogContainMessageOfPushedBackParameter(){
        viewModel.setMaximumSize("4");
        viewModel.setPushedValue("666");
        viewModel.createDeque();
        viewModel.pushBack();
        assertTruePatternMatches("Pushing back of ", viewModel.getPushedValue(),
                " is completed successfully");
    }

    @Test
    public void doesLogContainMessageOfPoppedFrontParameter(){
        viewModel.setMaximumSize("4");
        viewModel.setPushedValue("666");
        viewModel.createDeque();
        viewModel.pushFront();
        viewModel.popFront();
        assertTruePatternMatches("Popping front of ",viewModel.getPushedValue(),
                " is completed successfully");
    }

    @Test
    public void doesLogContainMessageOfPoppedBackParameter(){
        viewModel.setMaximumSize("4");
        viewModel.setPushedValue("666");
        viewModel.createDeque();
        viewModel.pushFront();
        viewModel.popBack();
        assertTruePatternMatches("Popping back of ",viewModel.getPushedValue(),
                " is completed successfully");
    }

    @Test
    public void doesLogContainMessageOfImpossibilityToCreateDequeWhenParameterIsInvalid(){
        viewModel.setMaximumSize("ogfs");
        viewModel.createDeque();
        assertTruePatternMatches("Creating deque with maximum size equals to ", viewModel.getMaximumSize(),
                " is impossible: invalid parameter");
    }

    @Test
    public void doesLogContainMessageOfSuccessfulCreationOfDeque(){
        viewModel.setMaximumSize("5");
        viewModel.createDeque();
        assertTruePatternMatches("Creating deque with maximum size equals to ", viewModel.getMaximumSize(),
                " is completed successfully");
    }

    private void assertTruePatternMatches(String prefix, String valueOfParameter, String postfix){
        Pattern pattern = Pattern.compile(".*" + prefix
                +  valueOfParameter
                +  postfix  + "$");
        Matcher matcher = pattern.matcher(viewModel.getLog().get(viewModel.getLog().size() - 1));
        assertTrue(matcher.matches());
    }
}
