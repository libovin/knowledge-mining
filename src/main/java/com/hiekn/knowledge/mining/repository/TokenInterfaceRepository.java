package com.hiekn.knowledge.mining.repository;


import com.hiekn.knowledge.mining.bean.dao.TokenInterface;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TokenInterfaceRepository extends MongoRepository<TokenInterface, String> {

    void deleteByInterfaceIdAndTokenId(String interfaceId, String tokenId);

    List<TokenInterface> findByInterfaceId(String interfaceId);

    List<TokenInterface> findByTokenId(String tokenId);
}
