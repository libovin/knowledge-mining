package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.vo.Config;
import com.hiekn.knowledge.mining.bean.vo.CounterConfig;
import com.hiekn.knowledge.mining.bean.vo.NlpConfig;
import com.hiekn.knowledge.mining.bean.vo.PatternConfig;
import com.hiekn.knowledge.mining.bean.vo.RelatedConfig;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.knowledge.mining.service.PatternService;
import com.hiekn.knowledge.mining.service.RelatedService;
import com.hiekn.knowledge.mining.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
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

    @Override
    public Map remote(String serverId, String context) {
        return null;
    }

    @Override
    public Map save() {
        return null;
    }

    @Override
    public Map preprocess(Map req) {
        return null;
    }
    private Map processOne(Map result, Config config){
        if(config instanceof NlpConfig){

        }else if(config instanceof PatternConfig){

        }else if(config instanceof RelatedConfig){

        }else if(config instanceof CounterConfig){

        }
        return null;
    }

    private Map processRelated(RelatedConfig config){



        return null;
    }

    @Override
    public Map getTask(String id) {
        return null;
    }

    @Override
    public List getList() {
        return null;
    }
}
