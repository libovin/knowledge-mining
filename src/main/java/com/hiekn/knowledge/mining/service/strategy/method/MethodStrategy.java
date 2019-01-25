package com.hiekn.knowledge.mining.service.strategy.method;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;

public interface MethodStrategy {
    //BiFunction args(ConfigReq configReq);

    MethodStrategy getMethodStrategy(ConfigReq configReq);
}
