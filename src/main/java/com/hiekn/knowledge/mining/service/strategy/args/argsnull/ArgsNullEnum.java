package com.hiekn.knowledge.mining.service.strategy.args.argsnull;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

import java.util.Map;
import java.util.function.BiFunction;

public enum ArgsNullEnum implements ArgsStrategy {
    NULL() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", x);
                return map;
            };
        }
    };

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq args) {
        return ArgsNullEnum.NULL;
    }
}
