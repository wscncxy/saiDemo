package com.sai.demo.design;

public class Factory {

}

interface BizFactory {

    Product createBean(Class cls);

}

class ConcreteBizFactory implements BizFactory {

    @Override
    public Product createBean(Class cls) {
        Product product = null;
        try {
            product = (Product) Class.forName(cls.getName()).newInstance();
        } catch (Exception e) {

        }
        return product;
    }
}

interface Product {

}

class ConcreteProduct implements Product {

}