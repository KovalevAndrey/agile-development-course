package ru.unn.agile.deque.model;

public class Deque {
    int [] deque;
    int count;
    int back;
    int front;

    public Deque(int maxCount){
        deque = new int[maxCount];
        count = 0;
        back = front = -1;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == deque.length;
    }

    public void pushBack(int element){
        if (isEmpty()){
            front = 0;
            back  = 0;
        }
        else if (!isFull()){
            back = (++back) % getMaxCount();
        }
        else{
            throw new RuntimeException("Deque is full, my lord");
        }
        deque[back] = element;
        count++;
    }

    public void pushFront(int element){
        if (isEmpty()){
            back = 0;
            front = 0;
        }
        else if(!isFull()){
            front--;
            if (front < 0) {
                front += getMaxCount();
            }
        }
        else{
            throw new RuntimeException("Deque is full, my lord");
        }
        deque[front] = element;
        count++;
    }

    public int popBack(){
        if (isEmpty()){
            throw new RuntimeException("Deque is empty, my lord");
        }
        int backElement = getBack();
        back--;
        if (back < 0){
            back += getMaxCount();
        }
        return backElement;
    }

    public int popFront(){
        if (isEmpty()){
            throw new RuntimeException("Deque is empty, my lord");
        }
        int frontElement = getFront();
        front = (front + 1) % getMaxCount();
        return frontElement;
    }

    public int getFront(){
        return deque[front];
    }

    public int getBack(){
        return deque[back];
    }

    public int getMaxCount(){
        return deque.length;
    }
}
