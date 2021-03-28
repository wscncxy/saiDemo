package com.sai.demo.design;

import lombok.Builder;
import lombok.Getter;

public class Bridge {
    public static void main(String[] args) {
        Implementor imp = new ConcreteImplementor1();
        Abstraction abs = new ConcreteAbstraction(imp);
        abs.request();
    }
}

interface Implementor{
    void doSomething();
    void doAnything();
}

class ConcreteImplementor1 implements Implementor {
    @Override
    public void doSomething() {

    }

    @Override
    public void doAnything() {

    }
}

class ConcreteImplementor2 implements Implementor {
    @Override
    public void doSomething() {

    }

    @Override
    public void doAnything() {

    }
}

abstract class Abstraction{
    @Getter
    private Implementor implementor;

    public Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    public void request(){
        this.implementor.doSomething();
    }

}

class ConcreteAbstraction extends Abstraction{

    public ConcreteAbstraction(Implementor implementor){
        super(implementor);
    }

    @Override
    public void request(){
        super.request();
        super.getImplementor().doAnything();
    }
}
