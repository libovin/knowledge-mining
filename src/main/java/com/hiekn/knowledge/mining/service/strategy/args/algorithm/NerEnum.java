package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

public enum NerEnum implements ArgsStrategy {
    F_NER,

    crf,
    hmm,

    L_NER,
    S_NER,
    NULL;

    NerEnum () {}

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        for (NerEnum argsEnum : NerEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(agrs.getAlgorithm())) {
                return argsEnum;
            }
        }
        return NULL;
    }
}
