package com.sai.demo.design;

public class Facade {
    public static void main(String[] args) {
        BizFacada facade = new BizFacada();
        facade.methodA();
        facade.methodB();
    }
}

class ServiceA{

    public void doSomething(){

    }
}

class ServiceB{

    public void doSomething(){

    }
}

class BizFacada{
    private ServiceA serviceA = new ServiceA();
    private ServiceB serviceB = new ServiceB();

    public void  methodA(){
        this.serviceA.doSomething();
    }

    public void  methodB(){
        this.serviceB.doSomething();
    }
}
