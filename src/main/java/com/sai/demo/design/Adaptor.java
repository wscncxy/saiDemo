package com.sai.demo.design;

public class Adaptor {
    public static void main(String[] args) {
        Target target = new BizClassAdaptor();
        target.doSomething();

        Target target1 = new BizObjAdaptor(new OrgBiz());
        target.doSomething();
    }
}

interface Target {
    void doSomething();
}

class ConcreteTarget implements Target{
    @Override
    public void doSomething() {

    }
}

class OrgBiz{

    public void doSomething(){

    }
}

class BizClassAdaptor extends OrgBiz implements Target{

    @Override
    public void doSomething() {
        super.doSomething();
    }
}

class BizObjAdaptor implements Target{

    private OrgBiz target;

    public BizObjAdaptor(OrgBiz target){
    this.target = target;
    }

    @Override
    public void doSomething() {
        target.doSomething();
    }
}