package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.knowledge.mining.service.PatternService;
import com.hiekn.knowledge.mining.service.RelatedService;
import com.hiekn.knowledge.mining.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private RelatedService relatedService;

    @Autowired
    private NlpService nlpService;

    @Autowired
    private PatternService patternService;

    @Override
    public Map getProp() {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("nlp",nlpService.getProp());
        map.put("related",relatedService.getProp());
        map.put("pattern",patternService.getProp());
        return map;
    }
}
