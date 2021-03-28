package com.sai.demo.design;

import org.springframework.context.ApplicationContext;

public class Factory {
    public static void main(String[] args) {
        BizFactory bizFactory = new ConcreteBizFactory();
        Product product = bizFactory.createBean(ConcreteProduct.class);
        Product product1 = new MyFactory<Product>().creatFactoryByConfig("1", Product.class);
        Product product2 = (Product)creatFactoryByConfig("1", Product.class);
    }


    static ApplicationContext applicationContext = null;
    public Object creatFactoryByBeanName(String beanName, Class product){
        return applicationContext.getBean(beanName, product);
    }

    public static Object creatFactoryByConfig(String configKey, Class interfaceClass){
        String beanName = ConfigDao.getConfig(configKey);
        return applicationContext.getBean(beanName, interfaceClass);
    }


}

class MyFactory<T>{
    private ApplicationContext applicationContext = null;
    public final T creatFactoryByConfig(String configKey, Class<T> interfaceClass){
        String beanName = ConfigDao.getConfig(configKey);
        return applicationContext.getBean(beanName, interfaceClass);
    }
}

class ConfigDao{
    static String getConfig(String key){
        return null;
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


