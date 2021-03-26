package com.sai.demo.design;

public class Strategy {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategy1());
        context.doSomething();
    }
}

interface BizStrategy{
    void doSomething();
}

class ConcreteStrategy1 implements BizStrategy{
    @Override
    public void doSomething() {

    }
}

class ConcreteStrategy2 implements BizStrategy{
    @Override
    public void doSomething() {

    }
}

class Context {

    private BizStrategy strategy=null;

    public Context(BizStrategy strategy){
        this.strategy = strategy;
    }

    public void doSomething(){
        this.strategy.doSomething();
    }
}