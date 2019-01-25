package com.hiekn.knowledge.mining.service.strategy.args.size;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

public enum SizeEnum implements ArgsStrategy {
    SIZE, NULL;

    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        if (agrs.getSize() > 0) {
            return SIZE;
        }
        return NULL;
    }
}
