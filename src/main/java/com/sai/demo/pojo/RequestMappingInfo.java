package com.sai.demo.pojo;

import java.lang.reflect.Method;
import java.util.Map;

public class RequestMappingInfo {
    private String className;
    private Method method;
    private Class[] arguments;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class[] getArguments() {
        return arguments;
    }

    public void setArguments(Class[] arguments) {
        this.arguments = arguments;
    }
}
