package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String userId);
}
