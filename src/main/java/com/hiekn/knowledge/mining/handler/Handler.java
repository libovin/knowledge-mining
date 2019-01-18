package com.hiekn.knowledge.mining.handler;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;

import java.util.Map;
import java.util.function.BiFunction;

public class Handler {
    private Handler nextHandler;
    private BiFunction<Map<String, ?>, ConfigReq, Map<String, ?>> doHandle;
    private ConfigReq args;

    public ConfigReq getArgs() {
        return args;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler(
            BiFunction<Map<String, ?>, ConfigReq, Map<String, ?>> doHandle,
            ConfigReq args) {
        this.doHandle = doHandle;
        this.args = args;
    }

    public Map<String, ?> handle(Map<String, ?> request, ConfigReq args) {
        if (nextHandler == null) {
            return doHandle.apply(request, args);
        } else {
            return nextHandler.handle(doHandle.apply(request, args), nextHandler.getArgs());
        }

    }


}
