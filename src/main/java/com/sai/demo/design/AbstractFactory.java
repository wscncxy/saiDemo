package com.sai.demo.design;

public class AbstractFactory {
}

interface BizAbstractFactory {

    ProductA createBeanProductA(Class cls);

    ProductB createBeanProductB(Class cls);

}

class ConcreteBizAbstractFactory1 implements BizAbstractFactory {

    @Override
    public ProductA createBeanProductA(Class cls) {
        ProductA product = null;
        try {
            product = (ProductA) Class.forName(cls.getName()).newInstance();
        } catch (Exception e) {

        }
        return product;
    }

    @Override
    public ProductB createBeanProductB(Class cls) {
        ProductB product = null;
        try {
            product = (ProductB) Class.forName(cls.getName()).newInstance();
        } catch (Exception e) {

        }
        return product;
    }
}


class ConcreteBizAbstractFactory2 implements BizAbstractFactory {

    @Override
    public ProductA createBeanProductA(Class cls) {
        ProductA product = null;
        try {
            product = (ProductA) cls.newInstance();
        } catch (Exception e) {

        }
        return product;
    }

    @Override
    public ProductB createBeanProductB(Class cls) {
        ProductB product = null;
        try {
            product = (ProductB) cls.newInstance();
        } catch (Exception e) {

        }
        return product;
    }
}

interface ProductA {

}


interface ProductB {

}

class ConcreteProductA1 implements ProductA {

}

class ConcreteProductA2 implements ProductA {

}

class ConcreteProductB1 implements ProductB {

}

class ConcreteProductB2 implements ProductB {

}