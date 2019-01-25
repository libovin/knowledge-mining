package com.hiekn.knowledge.mining.service.strategy;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.method.MethodStrategy;
import com.hiekn.knowledge.mining.service.strategy.method.NlpMethod;
import com.hiekn.knowledge.mining.service.strategy.method.NullMethod;
import com.hiekn.knowledge.mining.service.strategy.method.RelatedMethod;
import com.hiekn.knowledge.mining.service.strategy.method.ToolMethod;

public enum ModelEnum {

    NLP(NlpMethod.NULL),

    TOOL(ToolMethod.NULL),

    RELATED(RelatedMethod.NULL),

    NULL(NullMethod.NULL);


    ModelEnum(MethodStrategy kind) {
        this.methods = kind;
    }

    private MethodStrategy methods;

    public ModelEnum getModelStrategy(ConfigReq configReq) {
        for (ModelEnum modelEnum : ModelEnum.values()) {
            if (modelEnum.name().equalsIgnoreCase(configReq.getModel())) {
                return modelEnum;
            }
        }
        return NULL;
    }

}
