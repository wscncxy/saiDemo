package com.sai.demo.design;


public class Singleton {
    private volatile static Singleton signature = new Singleton();

    private Singleton(){

    }

    public static Singleton getSingleton(){
        return signature;
    }
}

class Singleton2{
    private volatile static Singleton2 signature = null;
    private Singleton2(){

    }

    public static Singleton2 getInstance(){
        if(signature == null){
            synchronized (Singleton2.class){
                if(signature == null){
                    signature = new Singleton2();
                }
            }
        }
        return signature;
    }
}


class Singleton3{
    private Singleton3(){

    }

    private static class Instance{
        private volatile Singleton3 signature = null;
        public Instance(){
            signature = new Singleton3();
        }
        public Singleton3 getSingleton(){
            return getSingleton();
        }
    }

    private static final  Instance instance= new Instance();
    public static Singleton3 getInstance(){
        return instance.getSingleton();
    }
}


enum Singleton4{
    INSTANCE;
    public void method(){

    }
}