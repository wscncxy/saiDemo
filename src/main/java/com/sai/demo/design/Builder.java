package com.sai.demo.design;

import lombok.Data;
import lombok.Setter;

public class Builder {
    public static void main(String[] args) {
        ConcreteProductBuilder builder = new ConcreteProductBuilder();
        builder.setProductName().build();
    }

}

@Data
class BizProduct{
    private String productName;
}

abstract class ProductBuilder{
    public abstract ProductBuilder setProductName();
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
    public BizProduct build() {
        return bizProduct;
    }
}
