package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Lists;
import com.hiekn.boot.autoconfigure.base.exception.ServiceException;
import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.bean.dao.TokenInterface;
import com.hiekn.knowledge.mining.exception.ErrorCodes;
import com.hiekn.knowledge.mining.repository.TokenInterfaceRepository;
import com.hiekn.knowledge.mining.service.TokenInterfaceService;
import com.hiekn.knowledge.mining.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TokenInterfaceServiceImpl implements TokenInterfaceService {

    @Autowired
    private TokenInterfaceRepository tokenInterfaceRepository;

    @Autowired
    private TokenService tokenService;


    @Override
    public RestData<TokenInterface> addToken(String id, String ids) {

        List<String> list = verifyTokenIds(ids);

        List<TokenInterface> tokenInterfaces = Lists.newArrayList();
        list.forEach(s -> {
            tokenInterfaces.add(new TokenInterface(id, s));
        });
        List<TokenInterface> lists = tokenInterfaceRepository.insert(tokenInterfaces);
        return new RestData<>(lists, lists.size());
    }

    @Override
    public void deleteTokenInterfaces(String interfaceId, String tokenIds) {

        List<String> list = verifyTokenIds(tokenIds);

        list.forEach(s -> tokenInterfaceRepository.deleteByInterfaceIdAndTokenId(interfaceId, s));

    }

    @Override
    public RestData<Token> findByInterfaceId(String interfaceId) {
        List<Token> tokenList = Lists.newArrayList();
        List<TokenInterface> list =
                tokenInterfaceRepository.findByInterfaceId(interfaceId);
        list.forEach(t ->{
            Token tt= tokenService.findOne(t.getTokenId());
            tt.setTokenInterface(t);
            tokenList.add(tt);
        });
        return new RestData<>(tokenList,tokenList.size());
    }

    private List<String> verifyTokenIds(String tokenIds) {
        if (tokenIds == null) {
            throw ServiceException.newInstance(ErrorCodes.PARAM_IS_NULL);
        }
        List<String> list = Arrays.asList(tokenIds.split(","));
        for (String s : list) {
            Token one = tokenService.findOne(s);
            if (one == null)
                throw ServiceException.newInstance(ErrorCodes.TOKEN_IS_NULL);
        }
        return list;
    }


}
