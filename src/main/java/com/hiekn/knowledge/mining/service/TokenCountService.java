package com.hiekn.knowledge.mining.service;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TokenCountService {

    void recordToken(String serverId,String token) throws Exception;

    List countByServerId(String serverId);
    List countByToken(String token);
}
