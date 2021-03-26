package com.sai.demo.design;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class Flyweight {

    public static void main(String[] args) {
        FlyweightFactory.getFlyWeight("test");
    }
}

abstract class BizFlyweight {

    @Setter
    @Getter
    private String intrinsic;

    protected final String extrinsic;

    public BizFlyweight(String extrinsic) {
        this.extrinsic = extrinsic;
    }

    public abstract void operate();
}

class ConcreteFlyweight1 extends BizFlyweight {
    public ConcreteFlyweight1(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate() {

    }
}

class ConcreteFlyweight2 extends BizFlyweight {
    public ConcreteFlyweight2(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate() {

    }
}

class FlyweightFactory {
    public static final HashMap<String, BizFlyweight> pool = new HashMap<>();

    public static BizFlyweight getFlyWeight(String extrinsic) {

        if (pool.containsKey(extrinsic)) {
            return pool.get(extrinsic);
        } else {
            BizFlyweight flyweight = new ConcreteFlyweight1(extrinsic);
            pool.put(extrinsic, flyweight);
            return flyweight;
        }
    }
}