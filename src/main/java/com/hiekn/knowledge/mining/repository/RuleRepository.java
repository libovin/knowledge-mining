package com.hiekn.knowledge.mining.repository;

import com.hiekn.knowledge.mining.bean.dao.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rule,String> {
}
