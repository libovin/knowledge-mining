package com.hiekn.knowledge.mining.service.strategy.method;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.NerEnum;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.PosEnum;
import com.hiekn.knowledge.mining.service.strategy.args.algorithm.SegmentEnum;
import com.hiekn.knowledge.mining.service.strategy.args.size.SizeEnum;

import java.util.Map;
import java.util.function.BiFunction;

public enum NlpMethod implements MethodStrategy {
    SEGMENT(SegmentEnum.class) {
        public BiFunction getFun() {
            return (x, y) -> {
                Map map = Maps.newHashMap();
                return map;
            };
        }
    },
    POS(PosEnum.class),
    NER(NerEnum.class),
    KEYWORD(SizeEnum.class),
    SUMMARY(SizeEnum.class),
    CLASSIFIER(ArgsNullEnum.class),
    DENPENDENCY(ArgsNullEnum.class),
    NULL(ArgsNullEnum.class);

    private ArgsStrategy[] args;

    public ArgsStrategy[] getArgs() {
        return args;
    }

    NlpMethod(Class<? extends ArgsStrategy> kind) {
        this.args = kind.getEnumConstants();
    }

    public BiFunction args(ConfigReq configReq) {
        for (NlpMethod modelEnum : NlpMethod.values()) {
            if (modelEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                for (ArgsStrategy argsStrategy : modelEnum.getArgs()) {
                    // return argsStrategy.getFun(configReq.getAgrs());
                }
            }
        }
        return (x, y) -> x;
    }

    public NlpMethod getMethodStrategy(ConfigReq configReq) {
        for (NlpMethod methodEnum : NlpMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NULL;
    }
}
