package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

public enum  PosEnum implements ArgsStrategy {
    F_POS,

    crf,
    percept,

    L_POS,

    S_POS,
    NULL;

    PosEnum() {}

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        for (PosEnum argsEnum : PosEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(agrs.getAlgorithm())) {
                return argsEnum;
            }
        }
        return NULL;
    }
}
