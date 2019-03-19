package com.hiekn.knowledge.mining.service;

import com.hiekn.boot.autoconfigure.base.model.result.RestData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TokenCountService {

    void recordToken(String serverId,String token);

    RestData<Map<String,String>> countByServerId();
}
