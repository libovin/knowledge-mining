package com.hiekn.knowledge.mining.service.strategy.args.argsnull;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

public enum ArgsNullEnum implements ArgsStrategy {
    NULL;

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        return NULL;
    }
}
