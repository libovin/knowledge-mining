
package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.domain.UserDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDo, String> {

    UserDo findByUsername(String username);
}
