package com.hiekn.knowledge.mining.service.strategy.args;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;

public interface ArgsStrategy {
   // BiFunction getFun(ArgsReq agrs);


    ArgsStrategy getArgsStrategy(ArgsReq agrs);
}
