package com.sai.demo.design;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    public static void main(String[] args) {
        ConcreteComponent root = new ConcreteComponent();
        ConcreteComponent point = new ConcreteComponent();
        root.add(point);
        point.add(new ConcreteComponent());

        display(root);
    }

    private static void display(Component component) {
        for (Component child : component.getChildComponent()) {
            if (CollectionUtils.isEmpty(child.getChildComponent())) {
                // leaf
            } else {
                display(child);
            }
        }
    }
}

abstract class Component {

    protected List<Component> list = new ArrayList<>();

    abstract void doSomething();

    abstract void add(Component component);

    abstract void remove(Component component);

    List<Component> getChildComponent() {
        return this.list;
    }

}

class ConcreteComponent extends Component {

    @Override
    void doSomething() {

    }

    @Override
    void add(Component component) {

    }

    @Override
    void remove(Component component) {

    }
}


