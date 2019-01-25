package com.hiekn.knowledge.mining.service.strategy.args.algorithm;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

public enum SegmentEnum implements ArgsStrategy {
    F_SEG,

    shortest,
    crf,
    nlp,
    dict,
    index,
    standard,
    n_short,

    L_MME,

    N_SEG,

    S_SEG,

    NULL;

    SegmentEnum() {

    }

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        for (SegmentEnum argsEnum : SegmentEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(agrs.getAlgorithm())) {
                return argsEnum;
            }
        }
        return NULL;
    }
}
