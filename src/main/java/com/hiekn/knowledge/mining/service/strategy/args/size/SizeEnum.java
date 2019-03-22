package com.hiekn.knowledge.mining.service.strategy.args.size;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum SizeEnum implements ArgsStrategy {
    SIZE() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", input);
                return map;
            };
        }
    };

    public ArgsStrategy getArgsStrategy(ArgsReq args) {
        if (args.getSize() > 0) {
            return SIZE;
        }
        return ArgsNullEnum.NULL;
    }
}
