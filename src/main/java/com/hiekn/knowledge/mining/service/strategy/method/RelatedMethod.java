package com.hiekn.knowledge.mining.service.strategy.method;


import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.RelatedService;
import com.hiekn.knowledge.mining.service.impl.RelatedServiceImpl;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.BiFunction;

public enum RelatedMethod implements MethodStrategy {
    BAIDU(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", relatedService.extractAnalysis(x));
                return map;
            };
        }
    },
    AMINER(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", relatedService.extractAminer(x));
                return map;
            };
        }
    },
    JOURNAL(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", relatedService.extractJournal(x));
                return map;
            };
        }
    },
    LITERATURE(ArgsNullEnum.NULL) {
        public BiFunction<String, ArgsReq, Map> getFun() {
            return (String x, ArgsReq args) -> {
                Map map = Maps.newHashMap();
                map.put("result", relatedService.extractLiterature(x));
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
