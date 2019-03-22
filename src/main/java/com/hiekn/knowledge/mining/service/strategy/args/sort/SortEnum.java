package com.hiekn.knowledge.mining.service.strategy.args.sort;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum SortEnum implements ArgsStrategy {
    ASC() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", input);
                return map;
            };
        }
    },
    DESC() {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", input);
                return map;
            };
        }
    };

    public ArgsStrategy getArgsStrategy(ArgsReq args) {
        for (SortEnum argsEnum : SortEnum.values()) {
            if (argsEnum.name().equalsIgnoreCase(args.getSort())) {
                return argsEnum;
            }
        }
        return ArgsNullEnum.NULL;
    }
}
