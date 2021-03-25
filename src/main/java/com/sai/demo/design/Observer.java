package com.sai.demo.design;

import java.util.ArrayList;
import java.util.List;

public class Observer {
}

class Event{
    String flow;
    String flowPoint;
}

interface EventListener<T>{

    Integer order();

    boolean canListen(T event);

    void processEvent(T event);
}

interface BroadCaster<T>{

    void addListener(EventListener<T> eventListen);
    void broadcast(T Event);
}

class BroadCaster1 implements BroadCaster<Event>{

    private List<EventListener<Event>> listenerList = new ArrayList<>();

    @Override
    public void addListener(EventListener eventListen){
        listenerList.add(eventListen);
        listenerList.stream().sorted((o1,o2)->{
            return o1.order().compareTo(o2.order());
        });
    }

    @Override
    public void broadcast(Event event){
        for(EventListener eventListener: listenerList){
            if(eventListener.canListen(event)){
                //do something
            }
        }
    }
}