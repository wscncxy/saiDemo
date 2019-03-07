package com.sai.demo.aop;

import com.alibaba.fastjson.JSONObject;
import com.sai.demo.annotation.proxy.ProxyAnnotation;
import com.sai.demo.annotation.proxy.ProxyMethodAnnotation;
import com.sai.demo.pojo.ScannerSourceInfo;
import com.sai.demo.util.ScannerSourceUtils;

import java.lang.reflect.Method;
import java.util.*;

public class AopMain {

    public static void main(String[] args) throws Exception{
        List<ScannerSourceInfo> scannerSourceInfoList = new ScannerSourceUtils().doScannerSource("com.sai.demo.aop");

        System.out.println("扫描到的包");
        System.out.println(JSONObject.toJSONString(scannerSourceInfoList));

        Set<String> proxyClassNameList = new HashSet<>();
        Map<String, Map<String, AopInfo>> proxyServiceMap = new HashMap<>();
        Map<String, Object> proxySerivceMap = new HashMap<>();
        for(ScannerSourceInfo scannerSourceInfo:scannerSourceInfoList){
            Class cla= scannerSourceInfo.getCla();

            System.out.println("className"+cla.getName()+"   "+cla.isAnnotationPresent(ProxyAnnotation.class));
            ProxyAnnotation proxyAnnotation = (ProxyAnnotation)cla.getAnnotation(ProxyAnnotation.class);
            if(proxyAnnotation !=null){
                Method[] methods = scannerSourceInfo.getMethod();
                if(methods!=null){
                    for(Method method:methods){
                        ProxyMethodAnnotation proxyMethodAnnotation=(ProxyMethodAnnotation)method.getAnnotation(ProxyMethodAnnotation.class);
                        if(proxyMethodAnnotation==null){
                            continue;
                        }
                        String targetMethodName = proxyMethodAnnotation.proxyMethod();
                        String[] tmpArr=targetMethodName.split("\\.");
                        String proxyClass = "";
                        if(tmpArr.length<3){
                            proxyClass+="."+tmpArr[0];
                        }else{
                            for(int i=0;i<tmpArr.length-2;i++){
                                proxyClass+="."+tmpArr[i];
                            }
                        }
                        proxyClassNameList.add(proxyClass.replaceFirst("\\.",""));

                        String position=proxyMethodAnnotation.position();
                        Map<String, AopInfo> proxyMap = proxyServiceMap.get(position);
                        if(proxyMap==null){
                            proxyMap = new HashMap();
                        }
                        AopInfo aopInfo = new AopInfo();
                        Object claObj = proxySerivceMap.get(cla.getName());
                        if(claObj==null){
                            claObj = cla.newInstance();
                            proxySerivceMap.put(cla.getName(),claObj);
                        }

                        aopInfo.setProxyClass(claObj);
                        aopInfo.setProxyMethod(method);

                        proxyMap.remove(targetMethodName);
                        proxyMap.put(targetMethodName, aopInfo);

                        proxyServiceMap.remove(position);
                        proxyServiceMap.put(position,proxyMap);
                    }
                }
            }
        }

        System.out.println("需要代理的类");
        System.out.println(JSONObject.toJSONString(proxyClassNameList));


        System.out.println("代理MAP");
        System.out.println(JSONObject.toJSONString(proxyClassNameList));

        ITestItem testItem=(ITestItem)(new AopFactory(proxyServiceMap,new TestItem()).getProxyInstance());

        System.out.println("");
        System.out.println("");
        System.out.println("");
        testItem.beProxy();

        System.out.println("======================");
        System.out.println("");
        System.out.println("");
        testItem.noProxy();


        System.out.println("======================");
        System.out.println("");
        System.out.println("");
        testItem.afterProxy();
    }
}
