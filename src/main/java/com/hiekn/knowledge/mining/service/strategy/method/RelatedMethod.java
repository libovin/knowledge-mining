package com.hiekn.knowledge.mining.service.strategy.method;


import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;

public enum RelatedMethod implements MethodStrategy {
    BAIDU(ArgsNullEnum.NULL),
    AMINER(ArgsNullEnum.NULL),
    JOURNAL(ArgsNullEnum.NULL),
    LITERATURE(ArgsNullEnum.NULL),
    NULL(ArgsNullEnum.NULL);

    private ArgsStrategy args;

    public ArgsStrategy getArgs() {
        return args;
    }

    RelatedMethod(ArgsStrategy kind) {
        this.args = kind;
    }

    public RelatedMethod getMethodStrategy(ConfigReq configReq) {
        for (RelatedMethod methodEnum : RelatedMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NULL;
    }

}
