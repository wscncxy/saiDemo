package com.sai.demo.design;

import lombok.Getter;
import lombok.Setter;

public class Mediator {
    public static void main(String[] args) {
        ConcreteColleague1 colleague1 = new ConcreteColleague1(new ConcreteMediator());
        colleague1.depMethod1();
    }
}

abstract class BizMediator {

    @Setter
    @Getter
    protected ConcreteColleague1 c1;

    @Setter
    @Getter
    protected ConcreteColleague2 c2;

    abstract void doSomething1();

    abstract void doSomething2();

}

class ConcreteMediator extends BizMediator {
    @Override
    void doSomething1() {
        super.c1.slefMethod1();
        super.c2.slefMethod1();
    }

    @Override
    void doSomething2() {
        super.c1.depMethod1();
        super.c2.depMethod2();
    }
}

abstract class Colleague {
    protected BizMediator mediator;

    public Colleague(BizMediator mediator) {
        this.mediator = mediator;
    }
}

class ConcreteColleague1 extends Colleague{
    public ConcreteColleague1(BizMediator mediator){
        super(mediator);
    }

    public void slefMethod1(){

    }

    public void depMethod1(){
        super.mediator.doSomething1();
    }
}


class ConcreteColleague2 extends Colleague{
    public ConcreteColleague2(BizMediator mediator){
        super(mediator);
    }

    public void slefMethod1(){

    }

    public void depMethod2(){
        super.mediator.doSomething2();
    }
}