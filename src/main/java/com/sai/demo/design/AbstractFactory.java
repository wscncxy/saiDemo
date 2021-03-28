package com.sai.demo.design;

public class AbstractFactory {
    public static void main(String[] args) {
        ConcreteBizAbstractFactory1 factory1 = new ConcreteBizAbstractFactory1();
        ConcreteBizAbstractFactory2 factory2 = new ConcreteBizAbstractFactory2();

        factory1.createBeanProductA(ProductA.class);
        factory1.createBeanProductB(ProductB.class);

        factory2.createBeanProductA(ProductA.class);
        factory2.createBeanProductB(ProductB.class);
    }
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


interface MyAbstractFactory{

    void createLoanService();

    void createRepayService();
}

class ZYAbstractFactory implements MyAbstractFactory{

    @Override
    public void createLoanService() {

    }

    @Override
    public void createRepayService() {

    }
}

class SupplierAbstractFactory implements MyAbstractFactory{

    @Override
    public void createLoanService() {

    }

    @Override
    public void createRepayService() {

    }
}