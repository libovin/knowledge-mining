package com.hiekn.knowledge.mining.service.strategy.args.regex;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum RegexEnum implements ArgsStrategy {
    REGEX() {
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
