package com.sai.demo.design;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        BroadCaster<Event> broadCaster = new ConcreteBroadCaster();
        broadCaster.registerObserver(new LocalLogListener());
        broadCaster.registerObserver(new SendMsgListener());
    }
}

class Event{
    //业务流出
    String flow;
    //事件
    String eventType;
    //结果
    String result;
}

interface FlowEventListener<T>{

    //判断是否接收事件
    boolean canListener();
    //处理事件
    void processEvent(T event);

}

interface EventListener<T>{

    void processEvent(T event);
}

class LocalLogListener implements EventListener{

    @Override
    public void processEvent(Object event) {

    }
}

class SendMsgListener implements EventListener{

    @Override
    public void processEvent(Object event) {

    }
}

interface BroadCaster<T>{

    void registerObserver(EventListener<T> eventListen);
    void removerObserver(EventListener<T> eventListen);
    void notifyObservers(T Event);
}

class ConcreteBroadCaster implements BroadCaster<Event>{

    private List<EventListener<Event>> listenerList = new ArrayList<>();

    @Override
    public void registerObserver(EventListener eventListen){
        listenerList.add(eventListen);
    }

    @Override
    public void removerObserver(EventListener<Event> eventListen) {
        listenerList.remove(eventListen);
    }

    @Override
    public void notifyObservers(Event event){
//        for(EventListener eventListener: listenerList){
//            threadPool.submit(
//                eventListener.processEvent(event);
//            );
//        }
    }
}