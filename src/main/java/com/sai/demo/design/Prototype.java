package com.sai.demo.design;

public class Prototype {
}

class PrototypeClass implements Cloneable{
    @Override
    protected PrototypeClass clone() throws CloneNotSupportedException {
        PrototypeClass prototypeClass = null;
        try{
            prototypeClass = (PrototypeClass) super.clone();
        }catch (Exception e){

        }
        return prototypeClass;
    }
}
