package com.sai.demo.proxy;

import java.lang.reflect.Method;

public class ProxyMain {

    public static void main(String[] args) {
        String name = "testCompiler";
        String code = "public class testCompiler{" +
                "public testCompiler(){}" +
                "public void test(){System.out.println(\"this is testCompiler\");}" +
                "}";

        //开始编译
        boolean compilerOk = CompilerUtil.compilterStringCode(name, code);
        if (compilerOk) {
            try {
                //自定义类加载器
                SaiClassLoad saiClassLoad=new SaiClassLoad(CompilerUtil.getClassList());
                //反射
                Class testCompiler = Class.forName("testCompiler",true,saiClassLoad);
                Object testObj = testCompiler.newInstance();
                Method method = testCompiler.getMethod("test");
                method.invoke(testObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("编译错误");
        }
    }
}
