package com.hiekn.knowledge.mining.service;

import java.util.Map;

public interface PatternService {

    Object find(Map req, Map config);

    Object matches(Map req, Map config);
}
