package com.hiekn.knowledge.mining.service;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.bean.dao.TokenInterface;

import java.util.List;

public interface TokenInterfaceService {


    RestData<TokenInterface> addToken(String id, String ids);

    void deleteTokenInterfaces(String interfaceId, String tokenIds);

    RestData<Token> findByInterfaceId(String interfaceId);

}
