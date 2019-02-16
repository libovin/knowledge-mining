package com.hiekn.knowledge.mining.service.strategy;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.method.MethodStrategy;
import com.hiekn.knowledge.mining.service.strategy.method.NlpMethod;
import com.hiekn.knowledge.mining.service.strategy.method.NullMethod;
import com.hiekn.knowledge.mining.service.strategy.method.RelatedMethod;
import com.hiekn.knowledge.mining.service.strategy.method.ToolMethod;

public enum ModelEnum {

    NLP(NlpMethod.SEGMENT),

    TOOL(ToolMethod.COUNT),

    RELATED(RelatedMethod.BAIDU),

    NULL(NullMethod.NULL);


    ModelEnum(MethodStrategy kind) {
        this.methods = kind;
    }

    private MethodStrategy methods;

    public MethodStrategy getMethods() {
        return methods;
    }

    public ModelEnum getModelStrategy(ConfigReq configReq) {
        for (ModelEnum modelEnum : ModelEnum.values()) {
            if (modelEnum.name().equalsIgnoreCase(configReq.getModel())) {
                return modelEnum;
            }
        }
        return NULL;
    }

}
