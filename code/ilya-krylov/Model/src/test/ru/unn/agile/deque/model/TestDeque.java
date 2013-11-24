package ru.unn.agile.deque.model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDeque {
    private Deque deque;

    private void fillCompletelyFromBack(){
        while(!deque.isFull()){
            deque.pushBack(0);
        }
    }

    private void fillCompletelyFromFront(){
        while(!deque.isFull()){
            deque.pushFront(0);
        }
    }

    @Before
    public void setUp(){
        deque = new Deque(10);
    }

    @Test
    public void isEmptyWhenCreate(){
       assertTrue(deque.isEmpty());
    }

    @Test
    public void isNotEmptyWhenPushBackNewElement(){
        deque.pushBack(10);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isNotFullWhenCreate(){
        assertFalse(deque.isFull());
    }

    @Test
    public void isFullWhenAmountOfElementsEqualsToMaximum(){
        fillCompletelyFromBack();
        assertTrue(deque.isFull());
    }

    @Test
    public void isPushedBackElementEqualsToBack(){
        deque.pushBack(555);
        assertEquals(555, deque.getBack());
    }

    @Test
    public void ExpectedExceptionWhenPushBackToFullDeque(){
        try{
            fillCompletelyFromBack();
            deque.pushBack(100);
            fail("Expected exception");
        }
        catch (Exception e){
            assertEquals("Deque is full, my lord", e.getMessage());
        }
    }

    @Test
    public void isNotEmptyWhenPushFrontNewElement(){
        deque.pushFront(10);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isPushedFrontElementEqualsToFront(){
        deque.pushFront(555);
        assertEquals(555, deque.getFront());
    }

    @Test
    public void ExpectedExceptionWhenPushFrontToFullDeque(){
        try{
            fillCompletelyFromFront();
            deque.pushFront(100);
            fail("Expected exception");
        }
        catch (Exception e){
            assertEquals("Deque is full, my lord", e.getMessage());
        }
    }

    @Test
    public void ExpectedExceptionWhenPopBackFromEmptyDeque(){
        try{
            deque.popBack();
            fail("Expected exception");
        }
        catch (Exception e){
             assertEquals("Deque is empty, my lord", e.getMessage());
        }
    }

    @Test
    public void ArePushedBackAndPoppedBackElementsEqual(){
        deque.pushBack(666);
        assertEquals(666, deque.popBack());
    }

    @Test
    public void expectedExceptionWhenPopFrontFromEmptyDeque(){
        try{
            deque.popFront();
            fail("Expected exception");
        }
        catch (Exception e){
            assertEquals("Deque is empty, my lord", e.getMessage());
        }
    }

    @Test
    public void arePushedFrontAndPoppedFrontElementsEqual(){
        deque.pushFront(666);
        assertEquals(666, deque.popFront());
    }

    @Test
    public void arePushedBackAndPoppedFrontEqualInEmptyDeque(){
        deque.pushBack(666);
        assertEquals(666, deque.popFront());
    }

    @Test
    public void arePushedFrontAndPoppedBackEqualInEmptyDeque(){
        deque.pushFront(666);
        assertEquals(666, deque.popBack());
    }

    @Test
    public void isFrontElementCorrectWhenPushedBackTwoAndPoppedFrontOneElement(){
        deque.pushBack(1);
        deque.pushBack(2);
        deque.popFront();
        assertEquals(2, deque.getFront());
    }

    @Test
    public void isBackElementCorrectWhenPushedBackTwoAndPoppedFrontOneElement(){
        deque.pushBack(1);
        deque.pushBack(2);
        deque.popFront();
        assertEquals(2, deque.getBack());
    }

    @Test
    public void isFrontElementCorrectWhenPushedFrontTwoAndPoppedBackOneElement(){
        deque.pushFront(1);
        deque.pushFront(2);
        deque.popFront();
        assertEquals(1, deque.getFront());
    }

    @Test
    public void isBackElementCorrectWhenPushedFrontTwoAndPoppedBackOneElement(){
        deque.pushFront(1);
        deque.pushFront(2);
        deque.popBack();
        assertEquals(2, deque.getBack());
    }

    @Test
    public void isCorrectMiddleElementWhenPushedFrontTwoAndPushedBackOneElement(){
        deque.pushFront(1);
        deque.pushFront(2);
        deque.pushBack(3);
        deque.popFront();
        deque.popBack();
        assertEquals(1, deque.getBack());
    }

    @Test
    public void isCorrectMiddleElementWhenPushedFrontOneAndPushedBackTwoElements(){
        deque.pushFront(1);
        deque.pushBack(2);
        deque.pushBack(3);
        deque.popFront();
        deque.popBack();
        assertEquals(2, deque.getBack());
    }

    @Test
    public void isToStringCorrectWhenDequeIsEmpty(){
        assertEquals("", deque.toString());
    }

    @Test
    public void isToStringCorrectAfterSeveralActions(){
        deque.pushBack(5);
        deque.pushBack(4);
        deque.pushBack(3);
        deque.pushFront(6);
        deque.pushBack(2);
        assertEquals("6 5 4 3 2 ",deque.toString());
    }
}
