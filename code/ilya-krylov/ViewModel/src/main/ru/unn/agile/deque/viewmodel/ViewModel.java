package ru.unn.agile.deque.viewmodel;
import  ru.unn.agile.deque.model.Deque;

public class ViewModel {
    public String maximumSize;
    public String pushedValue;
    public String dequeRepresentation;
    public String status;
    public Action action;
    public boolean isActionsComboBoxEnabled;
    public boolean isPushTextFieldEnabled;
    public boolean isActButtonEnabled;
    public Deque deque;

    public ViewModel(){
        maximumSize = "";
        pushedValue = "";
        dequeRepresentation = "";
        status = Status.WAITING_FOR_CREATION;
        isActionsComboBoxEnabled = false;
        isPushTextFieldEnabled = false;
        isActButtonEnabled = false;
        action = Action.PUSH_FRONT;
    }

    private boolean isMaximumSizeTextFieldCorrect() {
        try {
            if (!maximumSize.isEmpty()) {
                if (Integer.parseInt(maximumSize) >= 0) return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void createDeque() {
        if(isMaximumSizeTextFieldCorrect()){
            deque = new Deque(Integer.parseInt(maximumSize));
            isActButtonEnabled = true;
            isPushTextFieldEnabled = true;
            isActionsComboBoxEnabled = true;
            status = Status.DO_ACTION;
        }
        else {
            status = Status.BAD_PARAMETER;
        }
    }

    private boolean isPushedValueCorrect(){
        try {
            if (!pushedValue.isEmpty()) {
                Integer.parseInt(pushedValue);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void pushFront(){
        if (isPushedValueCorrect()) {
            if (!deque.isFull()){
                deque.pushFront(Integer.parseInt(pushedValue));
            }
            else{
                status = Status.DEQUE_IS_FULL;
            }
        }
        else {
            status = Status.BAD_PARAMETER;
        }
    }

    public void pushBack(){
        if (isPushedValueCorrect()) {
            if (!deque.isFull()){
                deque.pushBack(Integer.parseInt(pushedValue));
            }
            else{
                status = Status.DEQUE_IS_FULL;
            }
        }
        else{
            status = Status.BAD_PARAMETER;
        }
    }

    public void popFront(){
        if (deque.isEmpty()){
            status = Status.DEQUE_IS_EMPTY;
        }
        else deque.popFront();
    }

    public void popBack(){
        if (deque.isEmpty()){
            status = Status.DEQUE_IS_EMPTY;
        }
        else deque.popBack();
    }

    public void act(){
        switch (action){
            case POP_BACK:
                popBack();
                break;
            case POP_FRONT:
                popFront();
                break;
            case PUSH_BACK:
                pushBack();
                break;
            case PUSH_FRONT:
                pushFront();
                break;
        }
        dequeRepresentation = deque.toString();
    }

    public enum Action {
        PUSH_FRONT("Push Front"),
        PUSH_BACK("Push Back"),
        POP_FRONT("Pop Front"),
        POP_BACK("Pop Back");

        private final String name;

        private Action(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public class Status {
        public static final String WAITING_FOR_CREATION = "Please, choose size and create the deque";
        public static final String BAD_PARAMETER = "Check input parameters";
        public static final String DO_ACTION = "Please, perform action";
        public static final String DEQUE_IS_EMPTY = "Deque is empty. Popping is unavailable.";
        public static final String DEQUE_IS_FULL = "Deque is full. Pushing is unavailable.";
    }
}
