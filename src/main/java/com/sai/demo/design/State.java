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
    public final static RunState runState = new RunState();

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

    public void run() {
        this.liftState.run();
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

    abstract void run();

}

class OpennigState extends LiftState {
    @Override
    void open() {
        //doSomething
    }

    @Override
    void close() {
        super.liftContext.setLiftState(LiftContext.closeState);
        super.liftContext.getLiftState().close();
    }

    @Override
    void run() {
        //不能动
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

    @Override
    void run() {
        super.liftContext.setLiftState(LiftContext.runState);
        super.liftContext.getLiftState().run();
    }
}

class RunState extends LiftState {
    @Override
    void open() {

    }

    @Override
    void close() {

    }

    @Override
    void run() {
        //doSomething
    }
}

/**
 * OLD
 */
interface OldLift{
    public final static int OPENING_STATE=1;
    public final static int CLOSEING_STATE=2;
    public final static int RUNNING_STATE=3;

    void setState(int state);
    void open();
    void close();
    void run();
}

class ConcreteOldLift implements OldLift{
    private int state;
    @Override
    public void setState(int state) {
        this.state=state;
    }

    @Override
    public void open() {
        switch (this.state){
            case OPENING_STATE:
                //dosomething
                break;
            case CLOSEING_STATE:
                //开门
                break;
            case RUNNING_STATE:
                // 不能开
                break;
        }
    }

    @Override
    public void close() {
        switch (this.state){
            case OPENING_STATE:
                //关门
                break;
            case CLOSEING_STATE:
                // 不做事情
                break;
            case RUNNING_STATE:
                // 不做事情
                break;
        }
    }

    @Override
    public void run() {
        switch (this.state){
            case OPENING_STATE:
                // 开门时，不做事情
                break;
            case CLOSEING_STATE:
                // 动起来
                break;
            case RUNNING_STATE:
                // 不做事情
                break;
        }
    }
}

