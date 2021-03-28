package com.sai.demo.design;

public class Template {
    public static void main(String[] args) {
        LoanTemplate loanTemplate = new ConcreteLoan1();
        loanTemplate.templateMethod();
        loanTemplate = new ConcreteLoan2();
        loanTemplate.templateMethod();
    }
}


abstract class LoanTemplate{

    protected abstract void checkParam();

    protected abstract void saveLoan();

    protected abstract void loan();

    public final void templateMethod(){
        checkParam();

        saveLoan();

        loan();
    }
}

class ConcreteLoan1 extends LoanTemplate{
    @Override
    protected void checkParam() {

    }

    @Override
    protected void saveLoan() {

    }

    @Override
    protected void loan() {

    }
}

class ConcreteLoan2 extends LoanTemplate{
    @Override
    protected void checkParam() {

    }

    @Override
    protected void saveLoan() {

    }

    @Override
    protected void loan() {

    }
}