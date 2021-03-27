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
    String flow;
    String flowPoint;
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
        for(EventListener eventListener: listenerList){
            eventListener.processEvent(event);
        }
    }
}