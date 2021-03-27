package com.sai.demo.design;

public class Factory {
    public static void main(String[] args) {
        BizFactory bizFactory = new ConcreteBizFactory();
        Product product = bizFactory.createBean(ConcreteProduct.class);
    }
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

    public String getName();
}

class ConcreteProduct implements Product {

    @Override
    public String getName() {
        return "1";
    }
}