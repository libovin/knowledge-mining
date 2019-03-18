package com.hiekn.knowledge.mining.service;


import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.bean.vo.TokenQuery;

public interface TokenService {

    RestData<Token> findAll(TokenQuery bean);

    Token findOne(String id);

    void delete(String id);

    Token modify(String id, Token token);

    Token add(Token token);

}
