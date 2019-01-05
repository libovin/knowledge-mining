package com.hiekn.knowledge.mining.service;

import com.hiekn.knowledge.mining.bean.bo.Counter;

import java.util.List;
import java.util.Map;

public interface CounterService {

    List<Counter> count(List list);
    Map getProp();
}
