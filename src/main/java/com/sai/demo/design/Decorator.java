package com.sai.demo.design;

import java.io.*;

public class Decorator {
    public static void main(String[] args) {
        DecoratorComponent component = new ConcreteDecoratorComponent();
        component = new ConcreteDecorator1(component);
        component = new ConcreteDecorator2(component);
        component.operate();
    }
}


interface DecoratorComponent{
    void operate();
}

class ConcreteDecoratorComponent implements DecoratorComponent{
    @Override
    public void operate() {

    }
}

abstract class BizDecorator implements DecoratorComponent{

    private DecoratorComponent component;

    public BizDecorator(DecoratorComponent component){
        this.component = component;
    }

    @Override
    public void operate() {
        this.component.operate();
    }
}

class ConcreteDecorator1 extends BizDecorator{

    public ConcreteDecorator1(DecoratorComponent component){
        super(component);
    }

    private void method1(){

    }

    @Override
    public void operate() {
        method1();
        super.operate();
    }
}

class ConcreteDecorator2 extends BizDecorator{

    public ConcreteDecorator2(DecoratorComponent component){
        super(component);
    }

    private void method2(){

    }

    @Override
    public void operate() {
        super.operate();
        method2();
    }
}

abstract class PriceService {

    protected PriceService prePriceService;
    public PriceService(PriceService prePriceService){
        this.prePriceService = prePriceService;
    }

    boolean canProcessPrice(){
        return true;
    }

    abstract String getPrice();
}


class BasePriceService extends PriceService {
    public BasePriceService(PriceService prePriceService){
        super(prePriceService);
    }
    @Override
    public String getPrice() {
        return "BasePrice";
    }
}


class CashDiscountPriceService  extends PriceService {
    public CashDiscountPriceService(PriceService prePriceService){
        super(prePriceService);
    }

    @Override
    public String getPrice() {
        return prePriceService.getPrice()+",cashDiscount";
    }
}

class CouponiscountPriceService  extends PriceService {
    public CouponiscountPriceService(PriceService prePriceService){
        super(prePriceService);
    }

    @Override
    public String getPrice() {
        return prePriceService.getPrice()+",couponDiscount";
    }
}


