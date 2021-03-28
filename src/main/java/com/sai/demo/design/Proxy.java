package com.sai.demo.design;

public class Proxy {
    public static void main(String[] args) {
        ConcreteProxy proxy = new ConcreteProxy(new RealSubject());
        proxy.request();
    }
}

interface Subject{
    void request();
}

class RealSubject implements Subject{
    @Override
    public void request() {

    }
}

class ConcreteProxy implements Subject{

    private Subject subject = null;

    public ConcreteProxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public void request() {
        this.before();
        subject.request();
        this.after();
    }

    private void before(){

    }

    private void after(){

    }
}