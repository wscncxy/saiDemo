package com.sai.demo.proxy;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;

public class ProxyFactory {

    private final Object proxyItem;
    private final Map<String, Map<String, ProxyInfo>> proxyServiceMap;

    public ProxyFactory(Map<String, Map<String, ProxyInfo>> proxyServiceMap,
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
                        Map<String, ProxyInfo> preProxy = proxyServiceMap.get("pre");
                        excProxyMethod(preProxy, className, methodName);

                        //执行目标对象方法
                        Object returnValue = method.invoke(proxyItem, args);

                        //后置代理
                        Map<String, ProxyInfo> afterProxy = proxyServiceMap.get("after");
                        excProxyMethod(afterProxy, className, methodName);

                        return returnValue;
                    }
                }
        );
    }

    private void excProxyMethod(Map<String, ProxyInfo> proxy, String className, String methodName) {
        if (proxy != null) {
            Iterator<Map.Entry<String, ProxyInfo>> iterator = proxy.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ProxyInfo> preProxyEntry = iterator.next();
                String matchMethodPath = preProxyEntry.getKey();
                if (matchMethodPath.startsWith(className)) {
                    if (matchMethodPath.endsWith("*") || matchMethodPath.endsWith(methodName)) {
                        ProxyInfo proxyInfo = preProxyEntry.getValue();
                        try {
                            proxyInfo.getProxyMethod().invoke(proxyInfo.getProxyClass(), proxyInfo.getArgs());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
}
