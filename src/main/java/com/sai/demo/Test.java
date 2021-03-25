package com.sai.demo;

import com.alibaba.fastjson.JSONObject;
import com.sai.demo.util.DataWorkshop;

import java.lang.instrument.Instrumentation;
import java.util.HashMap;

public class Test {

    static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation instP) {
        inst = instP;
    }

    public static void main(String[] args) {
        DataWorkshop dataWorkshop = new DataWorkshop("11");
        HashMap userInfo = dataWorkshop.getUserInfo(null);
        userInfo.put("name","1");
        inst.getObjectSize(userInfo);
        System.out.println(JSONObject.toJSON(userInfo));
    }
}