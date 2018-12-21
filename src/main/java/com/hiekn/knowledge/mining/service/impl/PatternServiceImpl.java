package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.Item;
import com.hiekn.knowledge.mining.service.PatternService;
import org.springframework.stereotype.Service;

@Service
public class PatternServiceImpl implements PatternService {

    @Override
    public Item getProp() {
        return Item.of("匹配模式","type");
    }
}
