package com.hiekn.knowledge.mining.repository;

import com.hiekn.knowledge.mining.bean.dao.Dict;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DictRepository extends MongoRepository<Dict, String> {
}
