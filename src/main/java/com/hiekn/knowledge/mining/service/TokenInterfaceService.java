package com.hiekn.knowledge.mining.service;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.TokenInterface;

public interface TokenInterfaceService {


    RestData<TokenInterface> addToken(String id, String ids);

    void deleteTokenInterfaces(String interfaceId, String tokenIds);

}
