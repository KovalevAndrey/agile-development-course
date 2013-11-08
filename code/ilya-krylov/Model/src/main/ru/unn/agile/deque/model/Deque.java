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

    public Deque(Deque deq){
        deque = new int[deq.getMaxCount()];
        for (int i = 0; i < deque.length; i++){
            deque[i] = deq.deque[i];
        }
        count = deq.count;
        back = deq.back;
        front = deq.front;
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
        count--;
        return backElement;
    }

    public int popFront(){
        if (isEmpty()){
            throw new RuntimeException("Deque is empty, my lord");
        }
        int frontElement = getFront();
        front = (front + 1) % getMaxCount();
        count--;
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

    @Override
    public String toString(){
        String result = "";
        Deque temporaryCopy = new Deque(this);
        while(!temporaryCopy.isEmpty()){
            result += temporaryCopy.popFront() + " ";
        }
        return result;
    }
}
