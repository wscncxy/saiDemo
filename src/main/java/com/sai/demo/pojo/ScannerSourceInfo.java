package com.sai.demo.pojo;

import java.lang.reflect.Method;
import java.util.List;

public class ScannerSourceInfo {
    private Class cla;
    private Method[] method;

    public Class getCla() {
        return cla;
    }

    public void setCla(Class cla) {
        this.cla = cla;
    }

    public Method[] getMethod() {
        return method;
    }

    public void setMethod(Method[] method) {
        this.method = method;
    }
}
