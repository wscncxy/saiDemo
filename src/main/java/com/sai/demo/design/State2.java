package com.sai.demo.design;

import lombok.Getter;
import lombok.Setter;


public class State2 {
    public static void main(String[] args) {
        LiftContext liftContext = new LiftContext();
        liftContext.setLiftState(new OpennigState());
        liftContext.open();
        liftContext.close();
    }
}

class OrderInfo{
    public String state;

}

class OrderContext {
    public final static InitState initState = new InitState();
    public final static PayingState payingState = new PayingState();
    public final static OrderCloseState runState = new OrderCloseState();

    @Getter
    private OrderState orderState;

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        this.orderState.setOrderContext(this);
    }


    public void refund(OrderInfo orderInfo) {
        this.orderState.refund(orderInfo);
    }

    public void pay(OrderInfo orderInfo) {
        this.orderState.pay(orderInfo);
    }

    public void close(OrderInfo orderInfo) {
        this.orderState.close(orderInfo);
    }

}

abstract class OrderState {

    @Setter
    protected OrderContext orderContext;

    public void setContext(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    abstract void refund(OrderInfo orderInfo);

    abstract void pay(OrderInfo orderInfo);

    public void close(OrderInfo orderInfo){
        //默认不行
    }

}

class InitState extends OrderState {
    @Override
    void refund(OrderInfo orderInfo) {
        //不行
    }

    @Override
    void pay(OrderInfo orderInfo) {
        super.orderContext.setOrderState(OrderContext.payingState);
        super.orderContext.getOrderState().pay(orderInfo);
    }

}

class PayingState extends OrderState {
    @Override
    public void refund(OrderInfo orderInfo) {
        //不行
    }

    @Override
    public void pay(OrderInfo orderInfo) {
        //一定条件下可以再支付
    }

    @Override
    public void close(OrderInfo orderInfo) {
        //一定条件下可以
    }
}

class OrderCloseState extends OrderState {
    @Override
    public void refund(OrderInfo orderInfo) {

    }

    @Override
    public void pay(OrderInfo orderInfo) {

    }

}

/**
 * OLD
 */
interface OldOrder{
    public final static int INIT_STATE=1;
    public final static int PAYING_STATE=2;
    public final static int ORDERCLOSE_STATE=3;

    void setState(int state);
    void refund(OrderInfo orderInfo);
    void pay(OrderInfo orderInfo);
    void close(OrderInfo orderInfo);
}

class ConcreteOldOrder implements OldOrder{
    private int state;
    @Override
    public void setState(int state) {
        this.state=state;
    }

    @Override
    public void refund(OrderInfo orderInfo) {
        switch (this.state){
            case INIT_STATE:
                //不能
                break;
            case PAYING_STATE:
                //不能
                break;
            case ORDERCLOSE_STATE:
                //不能
                break;
        }
    }

    @Override
    public void pay(OrderInfo orderInfo) {
        switch (this.state){
            case INIT_STATE:
                //可以
                break;
            case PAYING_STATE:
                //可以
                break;
            case ORDERCLOSE_STATE:
                //不行
                break;
        }
    }

    @Override
    public void close(OrderInfo orderInfo) {
        switch (this.state){
            case INIT_STATE:
                //可以
                break;
            case PAYING_STATE:
                //一定条件下可以
                break;
            case ORDERCLOSE_STATE:
                // 不处理
                break;
        }
    }
}

