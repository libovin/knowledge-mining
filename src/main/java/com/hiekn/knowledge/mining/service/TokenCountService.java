package com.hiekn.knowledge.mining.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenCountService {

    Boolean recordToken(String serverId,String token) throws Exception;

    List countByServerId(String serverId);
    List countByToken(String token);
}
