package com.hiekn.knowledge.mining.handler;

import java.util.Map;
import java.util.function.BiFunction;

public class Handler {
    private Handler nextHandler;
    private BiFunction<Map<String, ?>, Map<String, ?>, Map<String, ?>> doHandle;
    private Map<String, ?> args;

    public Map<String, ?> getArgs() {
        return args;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler(
            BiFunction<Map<String, ?>, Map<String, ?>, Map<String, ?>> doHandle,
            Map<String, ?> args) {
        this.doHandle = doHandle;
        this.args = args;
    }

    public Map<String, ?> handle(Map<String, ?> request, Map<String, ?> args) {
        if (nextHandler == null) {
            return doHandle.apply(request, args);
        } else {
            return nextHandler.handle(doHandle.apply(request, args), nextHandler.getArgs());
        }

    }


}
