package com.sai.demo.design;

import lombok.Setter;

/**
 *
 */
public class ChainOfResponsibility {

    public static void main(String[] args) {
        MsgHandler handler1 = new ConcreteMsgHandler1();
        MsgHandler handler2 = new ConcreteMsgHandler2();
        MsgHandler handler3 = new ConcreteMsgHandler3();
        handler1.setNextMsgHandler(handler2);
        handler2.setNextMsgHandler(handler3);

        handler1.handlerMessage(new HandlerRequest());
    }
}

class Level {

}

class HandlerRequest {

    public Level getRequestLevel() {
        return null;
    }

    ;
}

class HandlerResponse {

}

abstract class MsgHandler {

    @Setter
    private MsgHandler nextMsgHandler;

    public final HandlerResponse handlerMessage(HandlerRequest request) {
        HandlerResponse respon = null;
        if (this.getHandlerLevel().equals(request.getRequestLevel())) {
            this.process(request);
        } else {
            if (this.nextMsgHandler != null) {
                this.nextMsgHandler.handlerMessage(request);
            } else {

            }
        }
        return respon;
    }

    protected abstract Level getHandlerLevel();

    protected abstract HandlerResponse process(HandlerRequest request);
}

class ConcreteMsgHandler1 extends MsgHandler {
    @Override
    protected Level getHandlerLevel() {
        return null;
    }

    @Override
    protected HandlerResponse process(HandlerRequest request) {
        return null;
    }
}

class ConcreteMsgHandler2 extends MsgHandler {
    @Override
    protected Level getHandlerLevel() {
        return null;
    }

    @Override
    protected HandlerResponse process(HandlerRequest request) {
        return null;
    }
}

class ConcreteMsgHandler3 extends MsgHandler {
    @Override
    protected Level getHandlerLevel() {
        return null;
    }

    @Override
    protected HandlerResponse process(HandlerRequest request) {
        return null;
    }
}

/**
 *
 * */