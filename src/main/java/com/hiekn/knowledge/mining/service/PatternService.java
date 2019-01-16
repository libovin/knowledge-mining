package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.vo.ConfigReq;

import java.util.Map;

public interface PatternService {

    Object find(Map req, ConfigReq config);

    Object matches(Map req, ConfigReq config);
}
