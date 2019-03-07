package com.sai.demo.aop;

import java.lang.reflect.Method;

public class AopInfo {
    private Object proxyClass;
    private Method proxyMethod;
    private Object[] args;

    public Object getProxyClass() {
        return proxyClass;
    }

    public void setProxyClass(Object proxyClass) {
        this.proxyClass = proxyClass;
    }

    public Method getProxyMethod() {
        return proxyMethod;
    }

    public void setProxyMethod(Method proxyMethod) {
        this.proxyMethod = proxyMethod;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}


