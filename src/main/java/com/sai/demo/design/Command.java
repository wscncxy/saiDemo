package com.sai.demo.design;

public class Command {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        Receiver receiver = new ConcreteReceiver1();

        BizCommand command = new ConcreteBizCommand(receiver);

        invoker.setBizCommand(command);

        invoker.action();
    }
}

interface Receiver{

    void doSomething();
}

class ConcreteReceiver1 implements Receiver{
    @Override
    public void doSomething() {

    }
}

class ConcreteReceiver2 implements Receiver{
    @Override
    public void doSomething() {

    }
}


interface BizCommand{
    void execute();
}

class ConcreteBizCommand implements BizCommand{

    private Receiver receiver;

    public ConcreteBizCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }
}

class Invoker{
    private BizCommand command = null;

    public void setBizCommand(BizCommand command){
        this.command = command;
    }

    public void action(){
        command.execute();
    }
}
