package com.hiekn.knowledge.mining.service.strategy.method;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.ToolService;
import com.hiekn.knowledge.mining.service.impl.ToolServiceImpl;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.knowledge.mining.service.strategy.args.regex.RegexEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum ToolMethod implements MethodStrategy {
    COUNT(ArgsNullEnum.NULL) {
        public BiFunction<List<String>, ArgsReq, Map> getFun() {
            return (List<String> input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", toolService.count(input, args));
                return map;
            };
        }
    },

    FIND(RegexEnum.REGEX) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", toolService.find(input, args));
                return map;
            };
        }
    },

    MATCHES(RegexEnum.REGEX) {
        public BiFunction<Object, ArgsReq, Map> getFun() {
            return (Object input, ArgsReq args) -> {
                Map map = new HashMap();
                if (input instanceof String) {
                    map.put("result", toolService.matches((String) input, args));
                } else if (input instanceof List) {
                    map.put("result", toolService.matches((List<String>) input, args));
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
