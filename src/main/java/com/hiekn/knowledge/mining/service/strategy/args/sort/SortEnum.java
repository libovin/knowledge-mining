package com.hiekn.knowledge.mining.service.strategy.args.sort;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

public enum SortEnum implements ArgsStrategy {
    ASC,
    DESC,
    NULL;

    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        for (SortEnum argsEnum : SortEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(agrs.getSort())) {
                return argsEnum;
            }
        }
        return NULL;
    }
}
