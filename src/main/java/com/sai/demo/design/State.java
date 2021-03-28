package com.sai.demo.design;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.Op;

public class State {
    public static void main(String[] args) {
        LiftContext liftContext = new LiftContext();
        liftContext.setLiftState(new OpennigState());
        liftContext.open();
        liftContext.close();
    }
}

class LiftContext {
    public final static OpennigState openState = new OpennigState();
    public final static CloseState closeState = new CloseState();

    @Getter
    private LiftState liftState;

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }
}

abstract class LiftState {

    @Setter
    protected LiftContext liftContext;

    public void setContext(LiftContext liftContext) {
        this.liftContext = liftContext;
    }

    abstract void open();

    abstract void close();

}

class OpennigState extends LiftState {
    @Override
    void open() {
        //doSomething
    }

    @Override
    void close() {
        super.liftContext.setLiftState(LiftContext.closeState);
        super.liftContext.getLiftState().open();
    }
}

class CloseState extends LiftState {
    @Override
    void open() {
        super.liftContext.setLiftState(LiftContext.openState);
        super.liftContext.getLiftState().open();
    }

    @Override
    void close() {
        //doSomething
    }

}