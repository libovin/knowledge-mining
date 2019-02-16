package com.hiekn.knowledge.mining.service.strategy.method;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;

import java.util.Map;
import java.util.function.BiFunction;

public interface MethodStrategy {
    MethodStrategy getMethodStrategy(ConfigReq configReq);

    ArgsStrategy getArgs();

    BiFunction<? extends Object, ArgsReq, Map> getFun();
}
