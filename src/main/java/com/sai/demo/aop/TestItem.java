package com.sai.demo.aop;

public class TestItem implements ITestItem {
    @Override
    public void beProxy() {
        System.out.println("我被代理了");
    }

    @Override
    public void noProxy() {
        System.out.println("我没有被代理");
    }

    @Override
    public void afterProxy() {
        System.out.println("我被代理后置");
    }
}
