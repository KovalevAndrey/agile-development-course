package ru.unn.agile.deque.viewmodel;
import  ru.unn.agile.deque.model.Deque;

import java.util.List;

public class ViewModel {
    private ILogger logger;
    private String maximumSize;
    private String pushedValue;
    private String dequeRepresentation;
    private String status;
    private Action action;
    private boolean isActionsComboBoxEnabled;
    private boolean isPushTextFieldEnabled;
    private boolean isActButtonEnabled;
    public Deque deque;

    public ViewModel(ILogger logger){
        if (logger == null) {
            throw new IllegalArgumentException("Null-logger cannot be used");
        }
        maximumSize = "";
        pushedValue = "";
        dequeRepresentation = "";
        status = Status.WAITING_FOR_CREATION;
        isActionsComboBoxEnabled = false;
        isPushTextFieldEnabled = false;
        isActButtonEnabled = false;
        action = Action.PUSH_FRONT;
        this.logger = logger;
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
            logger.log("Creating deque with maximum size equals to "
                    +  getMaximumSize()
                    +  " is completed successfully");
        }
        else {
            status = Status.BAD_PARAMETER;
            logger.log("Creating deque with maximum size equals to "
                    +  getMaximumSize()
                    +  " is impossible: invalid parameter");
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
                logger.log("Pushing front of "
                        + getPushedValue()
                        + " is completed successfully");
            }
            else{
                status = Status.DEQUE_IS_FULL;
                logger.log("Pushing front of "
                        + getPushedValue()
                        + " is impossible: deque is full");
            }
        }
        else {
            status = Status.BAD_PARAMETER;
            logger.log("Pushing front of "
                    + getPushedValue()
                    + " is impossible: invalid parameter");
        }
    }

    public void pushBack(){
        if (isPushedValueCorrect()) {
            if (!deque.isFull()){
                deque.pushBack(Integer.parseInt(pushedValue));
                logger.log("Pushing back of "
                        +  getPushedValue()
                        + " is completed successfully");
            }
            else{
                status = Status.DEQUE_IS_FULL;
                logger.log("Pushing back of "
                        +  getPushedValue()
                        + " is impossible: deque is full");
            }
        }
        else{
            status = Status.BAD_PARAMETER;
            logger.log("Pushing back of "
                    + getPushedValue()
                    + " is impossible: invalid parameter");
        }
    }

    public void popFront(){
        if (deque.isEmpty()){
            status = Status.DEQUE_IS_EMPTY;
            logger.log("Popping front is impossible: deque is empty");
        }
        else {
            int popped = deque.popFront();
            logger.log("Popping front of "
                    + popped
                    + " is completed successfully");
        }
    }

    public void popBack(){
        if (deque.isEmpty()){
            status = Status.DEQUE_IS_EMPTY;
            logger.log("Popping back is impossible: deque is empty");
        }
        else {
            int popped = deque.popBack();
            logger.log("Popping back of "
                    + popped
                    + " is completed successfully");
        }
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

    public String getMaximumSize(){
        return maximumSize;
    }

    public void setMaximumSize(String maximumSize){
        this.maximumSize = maximumSize;
    }

    public String getPushedValue(){
        return pushedValue;
    }

    public void setPushedValue(String pushedValue){
        this.pushedValue = pushedValue;
    }

    public String getDequeRepresentation(){
        return dequeRepresentation;
    }

    public void setDequeRepresentation(String dequeRepresentation){
        this.dequeRepresentation = dequeRepresentation;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Action getAction(){
        return action;
    }

    public void setAction(Action action){
        this.action = action;
    }

    public boolean getActionsComboBoxEnabled(){
        return isActionsComboBoxEnabled;
    }

    public void setActionsComboBoxEnabled(boolean isActionsComboBoxEnabled){
        this.isActionsComboBoxEnabled = isActionsComboBoxEnabled;
    }

    public boolean getPushTextFieldEnabled(){
        return isPushTextFieldEnabled;
    }

    public void setPushTextFieldEnabled(boolean isPushTextFieldEnabled){
        this.isPushTextFieldEnabled = isPushTextFieldEnabled;
    }

    public boolean getActButtonEnabled(){
        return isActButtonEnabled;
    }


    public void setActButtonEnabled(boolean isActButtonEnabled){
        this.isActButtonEnabled = isActButtonEnabled;
    }

    public List<String> getLog(){
        return logger.getLog();
    }

}
