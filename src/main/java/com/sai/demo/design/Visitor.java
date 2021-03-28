package com.sai.demo.design;

import java.util.Random;

public class Visitor {

    public static void main(String[] args) {
        Element element=ObjectStruture.createElement();
        element.accept(new ConcreteBizVisitor());
    }
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

class ObjectStruture {
    public static Element createElement(){
        Random random = new Random();
        if(random.nextInt(100)>50){
            return new ConcreteEleement1();
        }else{
            return new ConcreteEleement2();
        }
    }
}