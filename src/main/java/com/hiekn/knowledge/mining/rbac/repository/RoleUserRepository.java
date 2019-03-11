package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.domain.RoleUserDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleUserRepository extends MongoRepository<RoleUserDo, String> {

}
