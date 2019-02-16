package com.hiekn.knowledge.mining.service.strategy.method;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;

import java.util.Map;
import java.util.function.BiFunction;

public enum NullMethod implements MethodStrategy {
    NULL(ArgsNullEnum.NULL) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq y) -> {
                Map map = Maps.newHashMap();
                map.put("result", x);
                return map;
            };
        }
    };

    private ArgsStrategy args;

    public ArgsStrategy getArgs() {
        return args;
    }

    NullMethod(ArgsStrategy kind) {
        this.args = kind;
    }

    @Override
    public MethodStrategy getMethodStrategy(ConfigReq configReq) {
        return NULL;
    }
}
