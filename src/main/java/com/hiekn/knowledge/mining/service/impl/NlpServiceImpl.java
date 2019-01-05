package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import com.hiekn.nlp.tool.ConfigurableNLPService;
import com.hiekn.nlp.tool.support.FdNLPService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NlpServiceImpl implements NlpService {


    @Override
    public List<String> segment(String input) {
        ConfigurableNLPService nlpService = new FdNLPService();
        return nlpService.segment(input);
    }

    @Override
    public List<PartOfSpeech> pos(String input) {
        ConfigurableNLPService nlpService = new FdNLPService();
        return nlpService.pos(input);
    }

    @Override
    public List<TermBean> ner(String input) {
        ConfigurableNLPService nlpService = new FdNLPService();
        return nlpService.ner(input);
    }

    @Override
    public List<String> keyword(String input) {
        ConfigurableNLPService nlpService = new FdNLPService();
        return nlpService.extractKeyword(input);
    }

    @Override
    public List<String> summary(String input) {
        ConfigurableNLPService nlpService = new FdNLPService();
        return nlpService.autoSummary(input);
    }

    @Override
    public String classifier(String input) {
        ConfigurableNLPService nlpService = new FdNLPService();
        return nlpService.textClassification(input);
    }

    @Override
    public Map getProp() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("segment", getSegmentArgs());
        map.put("pos", getPosArgs());
        map.put("ner", getNerArgs());
        map.put("keyword", getKeywordArgs());
        map.put("summary", getSummaryArgs());
        map.put("classifier", getTextClassArgs());
        return map;
    }

    private Map getSegmentArgs() {
        Map<String, Object> map = Maps.newHashMap();

        return map;
    }

    private Map getPosArgs() {
        Map<String, Object> map = Maps.newHashMap();
        return map;
    }


    private Map getNerArgs(){
        Map<String, Object> map = Maps.newHashMap();
        return map;
    }

    private Map getKeywordArgs(){
        Map<String, Object> map = Maps.newHashMap();
        return map;
    }

    private Map getSummaryArgs(){
        Map<String, Object> map = Maps.newHashMap();
        return map;
    }

    private Map getTextClassArgs(){
        Map<String, Object> map = Maps.newHashMap();
        return map;
    }
}
