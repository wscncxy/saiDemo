package com.sai.demo.proxy;

import com.sai.demo.annotation.proxy.ProxyAnnotation;
import com.sai.demo.annotation.proxy.ProxyMethodAnnotation;

@ProxyAnnotation
public class ProxyService {

    @ProxyMethodAnnotation(proxyMethod="com.sai.demo.proxy.TestItem.beProxy")
    public void preProxyText(){
        System.out.println("代理前置内容");
    }

    @ProxyMethodAnnotation(proxyMethod="com.sai.demo.proxy.TestItem.beProxy", position = "after")
    public void afterProxyText(){
        System.out.println("代理后置内容");
    }

    @ProxyMethodAnnotation(proxyMethod="com.sai.demo.proxy.TestItem.afterProxy", position = "after")
    public void after1ProxyText(){
        System.out.println("代理前置内容啦啦啦");
    }
}
