package com.sai.demo.design;

public class Template {
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