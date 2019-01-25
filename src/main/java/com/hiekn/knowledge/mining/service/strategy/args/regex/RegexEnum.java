package com.hiekn.knowledge.mining.service.strategy.args.regex;

import com.hiekn.knowledge.mining.bean.dao.ArgsReq;
import com.hiekn.knowledge.mining.service.strategy.args.ArgsStrategy;
import org.springframework.util.StringUtils;

public enum RegexEnum implements ArgsStrategy {
    REGEX,NULL;


    @Override
    public ArgsStrategy getArgsStrategy(ArgsReq agrs) {
        if (StringUtils.hasText(agrs.getRuleId())){
            return REGEX;
        }
        return NULL;
    }
}
