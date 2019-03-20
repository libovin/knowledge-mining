package com.hiekn.knowledge.mining.repository;


import com.hiekn.knowledge.mining.bean.dao.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token,String> {
    Token findByToken(String token);
}
