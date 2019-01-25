package com.hiekn.knowledge.mining.service.strategy.method;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import com.hiekn.knowledge.mining.service.strategy.args.argsnull.ArgsNullEnum;
import com.hiekn.knowledge.mining.service.strategy.args.regex.RegexEnum;
import com.hiekn.knowledge.mining.service.strategy.args.sort.SortEnum;

public enum ToolMethod implements MethodStrategy {
    COUNT(SortEnum.class) {},
    FIND(RegexEnum.class) {},
    MATCHES(RegexEnum.class) {},
    NULL(ArgsNullEnum.class);


    private ArgsStrategy[] args;

    public ArgsStrategy[] getArgs() {
        return args;
    }

    ToolMethod(Class<? extends ArgsStrategy> kind) {
        this.args = kind.getEnumConstants();
    }

    public ToolMethod getMethodStrategy(ConfigReq configReq) {
        for (ToolMethod methodEnum : ToolMethod.values()) {
            if (methodEnum.name().equalsIgnoreCase(configReq.getMethod())) {
                return methodEnum;
            }
        }
        return NULL;
    }

}
