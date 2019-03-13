package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.domain.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission,String> {

}
