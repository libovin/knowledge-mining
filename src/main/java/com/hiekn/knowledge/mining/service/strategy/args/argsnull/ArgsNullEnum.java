package com.hiekn.knowledge.mining.service.strategy.args.argsnull;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum ArgsNullEnum implements ArgsStrategy {
    NULL() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", input);
                return map;
            };
        }
    };

    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq args) {
        return ArgsNullEnum.NULL;
    }
}
