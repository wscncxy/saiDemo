package com.sai.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;

public class AopFactory {

    private final Object proxyItem;
    private final Map<String, Map<String, AopInfo>> proxyServiceMap;

    public AopFactory(Map<String, Map<String, AopInfo>> proxyServiceMap,
                      Object proxyItem) {
        this.proxyServiceMap = proxyServiceMap;
        this.proxyItem = proxyItem;

    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                proxyItem.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String className = proxyItem.getClass().getName();
                        String methodName = method.getName();
                        //前置代理
                        Map<String, AopInfo> preProxy = proxyServiceMap.get("pre");
                        excProxyMethod(preProxy, className, methodName);

                        //执行目标对象方法
                        Object returnValue = method.invoke(proxyItem, args);

                        //后置代理
                        Map<String, AopInfo> afterProxy = proxyServiceMap.get("after");
                        excProxyMethod(afterProxy, className, methodName);

                        return returnValue;
                    }
                }
        );
    }

    private void excProxyMethod(Map<String, AopInfo> proxy, String className, String methodName) {
        if (proxy != null) {
            Iterator<Map.Entry<String, AopInfo>> iterator = proxy.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, AopInfo> preProxyEntry = iterator.next();
                String matchMethodPath = preProxyEntry.getKey();
                if (matchMethodPath.startsWith(className)) {
                    if (matchMethodPath.endsWith("*") || matchMethodPath.endsWith(methodName)) {
                        AopInfo aopInfo = preProxyEntry.getValue();
                        try {
                            aopInfo.getProxyMethod().invoke(aopInfo.getProxyClass(), aopInfo.getArgs());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
}
