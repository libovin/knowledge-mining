package com.hiekn.knowledge.mining.repository;


import com.hiekn.knowledge.mining.bean.dao.TokenInterface;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TokenInterfaceRepository extends MongoRepository<TokenInterface,String> {

    TokenInterface deleteTokenInterfaceByInterfaceIdAndTokenId(String interfaceId, String tokenId);
    TokenInterface findTokenInterfaceByInterfaceIdAndTokenId(String interfaceId, String tokenId);
    List<TokenInterface> findTokenInterfaceByInterfaceId(String interfaceId);
    List<TokenInterface> findByTokenId(String tokenId);
}
