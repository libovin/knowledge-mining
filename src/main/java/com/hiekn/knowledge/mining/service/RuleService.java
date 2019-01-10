package com.hiekn.knowledge.mining.service;


import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Rule;
import com.hiekn.knowledge.mining.bean.vo.CheckRule;
import com.hiekn.knowledge.mining.bean.vo.RuleQuery;

import java.util.Map;

public interface RuleService {

    RestData<Rule> findAll(RuleQuery bean);

    Rule findOne(String id);

    void delete(String id);

    Rule modify(String id, Rule rule);

    Rule add(Rule rule);

    Map<String, Object> check(CheckRule checkRule);
}
