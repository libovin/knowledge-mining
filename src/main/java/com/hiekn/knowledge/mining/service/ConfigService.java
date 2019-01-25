package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.dao.ConfigReq;

import java.util.Map;
import java.util.function.BiFunction;

public interface ConfigService {
    Map getProp();

    BiFunction<Map<String ,?>, ConfigReq, Map<String ,?>> getFunction(ConfigReq r);
}
