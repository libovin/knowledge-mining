package com.hiekn.knowledge.mining.service.strategy.args.regex;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.BiFunction;

public enum RegexEnum implements ArgsStrategy {
    REGEX() {
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
        if (StringUtils.hasText(args.getRuleId())) {
            return REGEX;
        }
        return ArgsNullEnum.NULL;
    }
}
