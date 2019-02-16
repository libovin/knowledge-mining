package com.hiekn.knowledge.mining.service.strategy.args;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;

import java.util.Map;
import java.util.function.BiFunction;

public interface ArgsStrategy {

    BiFunction<? extends Object, ArgsReq, Map> getFun();

    ArgsStrategy getArgsStrategy(ArgsReq args);
}
