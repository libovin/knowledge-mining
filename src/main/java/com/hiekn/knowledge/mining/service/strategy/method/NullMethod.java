package com.hiekn.knowledge.mining.service.strategy.method;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;

public enum NullMethod implements MethodStrategy {
    NULL;

    @Override
    public MethodStrategy getMethodStrategy(ConfigReq configReq) {
        return NULL;
    }
}
