package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.vo.ConfigReq;

import java.util.Map;
import java.util.function.BiFunction;

public interface ConfigService {
    Map getProp();

    BiFunction getFunction(ConfigReq r);
}
