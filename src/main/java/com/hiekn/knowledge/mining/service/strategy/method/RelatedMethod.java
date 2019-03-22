package com.hiekn.knowledge.mining.service.strategy.method;


import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.RelatedService;
import com.hiekn.knowledge.mining.service.impl.RelatedServiceImpl;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum RelatedMethod implements MethodStrategy {
    BAIDU(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractAnalysis(input));
                return map;
            };
        }
    },
    AMINER(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractAminer(input));
                return map;
            };
        }
    },
    JOURNAL(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractJournal(input));
                return map;
            };
        }
    },
    LITERATURE(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String input, ArgsReq args) -> {
                Map map = new HashMap();
                map.put("result", relatedService.extractLiterature(input));
                return map;
            };
        }
    };

    protected RelatedService relatedService = new RelatedServiceImpl(new RestTemplate());

    private ArgsStrategy args;

    public ArgsStrategy getArgs() {
        return args;
    }

    RelatedMethod(ArgsStrategy kind) {
        this.args = kind;
    }

    public MethodStrategy getMethodStrategy(ConfigReq configReq) {
        for (RelatedMethod methodEnum : RelatedMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NullMethod.NULL;
    }

}
