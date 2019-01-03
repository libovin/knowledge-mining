package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.vo.CounterConfig;
import com.hiekn.knowledge.mining.bean.vo.NlpConfig;
import com.hiekn.knowledge.mining.bean.vo.PatternConfig;
import com.hiekn.knowledge.mining.bean.vo.RelatedConfig;

import java.util.Map;

public interface ConfigService {
    Map handler(Map result, CounterConfig counter);

    Map handler(Map result, NlpConfig counter);

    Map handler(Map result, PatternConfig counter);

    Map handler(Map result, RelatedConfig counter);

    Map getProp();
}
