package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.pojo.Item;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NlpServiceImpl implements NlpService {


    @Override
    public List<String> segment(String input) {
        return null;
    }

    @Override
    public List<PartOfSpeech> pos(String input) {
        return null;
    }

    @Override
    public List<TermBean> ner(String input) {
        return null;
    }

    @Override
    public List<String> extractKeyword(String input) {
        return null;
    }

    @Override
    public List<String> autoSummary(String input) {
        return null;
    }

    @Override
    public String textClassification(String input) {
        return null;
    }

    @Override
    public Map getProp() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("HanNLP",getHanNLPProp());
        map.put("LTP",getMethod());
        map.put("FdNLP",getMethod());
        map.put("NLPIR",getMethod());
        map.put("StanfordNLP",getMethod());
        return map;
    }

    private Map getHanNLPProp(){
        Map<String,Object> map =Maps.newHashMap();
        map.put("customDict","");
        map.put("segment","");
        return map;
    }

    private List getMethod(){
        List<Item> list = new ArrayList<>();
        list.add(Item.of("分词","segment"));
        list.add(Item.of("词性","pos"));
        list.add(Item.of("实体识别","ner"));
        list.add(Item.of("关键词提取","keyword"));
        list.add(Item.of("自动摘要","summary"));
        list.add(Item.of("文本分类","textClassification"));
        return list;
    }


}
