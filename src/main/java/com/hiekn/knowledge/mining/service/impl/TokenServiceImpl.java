package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import com.hiekn.knowledge.mining.bean.dao.Token;
import com.hiekn.knowledge.mining.bean.dao.TokenInterface;
import com.hiekn.knowledge.mining.bean.vo.TokenQuery;
import com.hiekn.knowledge.mining.repository.TokenInterfaceRepository;
import com.hiekn.knowledge.mining.repository.TokenRepository;
import com.hiekn.knowledge.mining.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenInterfaceRepository tokenInterfaceRepository;

    @Override
    public RestData<Token> findAll(TokenQuery bean) {
        Pageable pageable = new PageRequest(bean.getPageNo() - 1, bean.getPageSize());
        Page<Token> p = tokenRepository.findAll(pageable);
        return new RestData<>(p.getContent(), p.getTotalElements());
    }

    @Override
    public Token findOne(String id) {
        return tokenRepository.findOne(id);
    }

    @Override
    public void delete(String id) throws Exception {
        List<TokenInterface> byTokenId = tokenInterfaceRepository.findByTokenId(id);
        if (!CollectionUtils.isEmpty(byTokenId)) {
            throw new Exception("该Token与接口关联不能删除");
        }
        tokenRepository.delete(id);
    }

    @Override
    public Token modify(String id, Token token) {
        Token targe = tokenRepository.findOne(id);
        BeanUtils.copyProperties(token, targe, "content");
        return tokenRepository.save(targe);
    }

    @Override
    public Token add(Token token) {
        //生成token值
        token.setToken("token|" + UUID.randomUUID().toString().replace("-", ""));
        return tokenRepository.insert(token);
    }
}
