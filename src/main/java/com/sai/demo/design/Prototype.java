package com.sai.demo.design;

public class Prototype {
    public static void main(String[] args) {
        PrototypeClass prototypeClass = new PrototypeClass();
        try{
            PrototypeClass newPrototype = prototypeClass.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class PrototypeClass implements Cloneable{
    @Override
    protected PrototypeClass clone() throws CloneNotSupportedException {
        PrototypeClass prototypeClass = null;
        prototypeClass = (PrototypeClass) super.clone();
        return prototypeClass;
    }
}
