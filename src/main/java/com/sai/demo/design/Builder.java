package com.sai.demo.design;

import lombok.Data;
import lombok.Setter;

public class Builder {
    public static void main(String[] args) {
        Director director = new Director();
        BizProduct product = director.getAProduct();

        MyDataBean myDataBean = MyDataBean.builder().name("sai").phone("1805").build();
    }
}

@Data
class BizProduct {
    private String productName;
    private String productCode;
}

abstract class ProductBuilder {
    public abstract ProductBuilder setProductName();

    public abstract ProductBuilder setProductCode();

    public abstract BizProduct build();
}

class ConcreteProductBuilder extends ProductBuilder {
    BizProduct bizProduct = new BizProduct();

    @Override
    public ProductBuilder setProductName() {
        // do something
        return this;
    }

    @Override
    public ProductBuilder setProductCode() {
        // do something
        return this;
    }

    @Override
    public BizProduct build() {
        return bizProduct;
    }
}

class Director {
    private ConcreteProductBuilder builder = new ConcreteProductBuilder();

    public BizProduct getAProduct() {
        return builder.setProductName().setProductCode().build();
    }
}

@lombok.Builder
class MyDataBean{

    private String name;
    private int age;
    private String phone;
}