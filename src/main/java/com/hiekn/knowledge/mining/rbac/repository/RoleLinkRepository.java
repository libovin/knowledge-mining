package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.domain.RoleLinkDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleLinkRepository extends MongoRepository<RoleLinkDo, String> {

}
