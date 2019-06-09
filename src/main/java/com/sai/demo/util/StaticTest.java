package com.sai.demo.util;

public class StaticTest extends de{
        public static void main(String[] args)
        {
            staticFunction();
        }

        static StaticTest st = new StaticTest();

        static
        {
            System.out.println("1");
        }

        {
            System.out.println("2");
        }

        StaticTest()
        {
            System.out.println("3");
            System.out.println("a="+a+",b="+b+",dddd="+dddd);
        }

        public static void staticFunction(){
            System.out.println("4");
        }

        int a=110;
        static int b =112;
}

class de{
    protected int dddd= 1988;
    static {
        System.out.println("pre1");
    }
}