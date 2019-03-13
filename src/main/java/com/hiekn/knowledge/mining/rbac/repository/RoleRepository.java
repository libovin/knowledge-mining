package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String string);
}
