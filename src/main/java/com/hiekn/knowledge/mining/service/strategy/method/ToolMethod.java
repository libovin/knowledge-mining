package com.hiekn.knowledge.mining.service.strategy.method;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.ToolService;
import com.hiekn.knowledge.mining.service.impl.ToolServiceImpl;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.regex.RegexEnum;
import com.hiekn.knowledge.mining.service.strategy.args.size.SizeEnum;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum ToolMethod implements MethodStrategy {
    COUNT(SizeEnum.SIZE) {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", toolService.count(x, args));
                return map;
            };
        }
    },

    FIND(RegexEnum.REGEX) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", toolService.find(x, args));
                return map;
            };
        }
    },

    MATCHES(RegexEnum.REGEX) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                if (x instanceof String) {
                    map.put("result", toolService.matches((String) x, args));
                } else if (x instanceof List) {
                    map.put("result", toolService.matches((List<String>) x, args));
                }
                return map;
            };
        }
    };

    protected ToolService toolService = new ToolServiceImpl();

    private ArgsStrategy args;

    public ArgsStrategy getArgs() {
        return args;
    }

    ToolMethod(ArgsStrategy kind) {
        this.args = kind;
    }

    public MethodStrategy getMethodStrategy(ConfigReq configReq) {
        for (ToolMethod methodEnum : ToolMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NullMethod.NULL;
    }

}
