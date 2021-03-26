package com.sai.demo.design;

import lombok.Getter;
import lombok.Setter;

public class Memento {

    public static void main(String[] args) {
        Originator originator = new Originator();
        MementoManager mementoManager = new MementoManager();
        mementoManager.setMemento(originator.createMemento());
        originator.restore(mementoManager.getMemento());

        //2
        OriginatorClone originatorClone = new OriginatorClone();
        mementoManager.setMemento2(originatorClone.createMemento());
        originatorClone.restore(mementoManager.getMemento2());
    }
}

class BizMemento {

    @Getter
    private String state = "";

    public BizMemento(String state) {
        this.state = state;
    }
}

class Originator {

    @Setter
    @Getter
    private String state = "";

    public BizMemento createMemento() {
        return new BizMemento(this.state);
    }

    public void restore(BizMemento bizMemento) {
        this.setState(bizMemento.getState());
    }
}

class MementoManager{

    @Setter
    @Getter
    private BizMemento memento;

    @Setter
    @Getter
    private OriginatorClone memento2;

}

/**
 *
 * */
class OriginatorClone implements Cloneable{

    @Setter
    @Getter
    private String state = "";

    public OriginatorClone createMemento() {
        try{
            return (OriginatorClone)this.clone();
        }catch (Exception e){

        }
        return null;
    }

    public void restore(OriginatorClone bizMemento) {
        this.setState(bizMemento.getState());
    }
}

