package com.hiekn.knowledge.mining.service;

import java.util.Map;
import java.util.function.BiFunction;

public interface ConfigService {
    Map getProp();

    BiFunction getFunction(Map r);
}
