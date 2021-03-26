package com.sai.demo.design;

public class Visitor {
}

interface Element{
    void doSomething();

    void accept(BizVisitor visitor);
}

class ConcreteEleement1 implements Element{
    @Override
    public void doSomething() {

    }

    @Override
    public void accept(BizVisitor visitor) {
        visitor.visit(this);
    }
}

class ConcreteEleement2 implements Element{
    @Override
    public void doSomething() {

    }

    @Override
    public void accept(BizVisitor visitor) {
        visitor.visit(this);
    }
}

interface BizVisitor{

    void visit(ConcreteEleement1 eleement);
    void visit(ConcreteEleement2 eleement);
}

class ConcreteBizVisitor implements BizVisitor{
    @Override
    public void visit(ConcreteEleement1 eleement) {
        eleement.doSomething();
    }

    @Override
    public void visit(ConcreteEleement2 eleement) {
        eleement.doSomething();
    }
}
