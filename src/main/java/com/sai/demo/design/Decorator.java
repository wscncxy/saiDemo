package com.sai.demo.design;

public class Decorator {
    public static void main(String[] args) {
        PriceService priceService = new BasePriceService(null);
        priceService = new CashDiscountPriceService(priceService);
        priceService = new CashDiscountPriceService(priceService);
        priceService = new CouponiscountPriceService(priceService);
    System.out.println(priceService.getPrice());


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