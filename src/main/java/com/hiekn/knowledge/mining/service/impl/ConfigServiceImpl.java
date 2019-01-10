package com.hiekn.knowledge.mining.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hiekn.knowledge.mining.service.ConfigService;
import com.hiekn.knowledge.mining.service.CounterService;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.knowledge.mining.service.PatternService;
import com.hiekn.knowledge.mining.service.RelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

@Service
public class ConfigServiceImpl implements ConfigService {


    @Autowired
    private RelatedService relatedService;

    @Autowired
    private NlpService nlpService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private CounterService counterService;

    @Override
    public Map getProp() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String fileName = "json/config.json";
        Enumeration<URL> resources;
        JSONObject jsonObject = new JSONObject();
        try {
            resources = classLoader.getResources(fileName);
        } catch (IOException e) {
            return jsonObject;
        }
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                String json = Resources.toString(url, Charsets.UTF_8);
                jsonObject.putAll(JSON.parseObject(json));
            } catch (IOException e) {

            }
        }
        return jsonObject;
    }

    private Predicate<Map<String, ?>> segment = map -> "nlp".equals(map.get("model")) && "segment".equals(map.get("method"));
    private Predicate<Map<String, ?>> pos = map -> "nlp".equals(map.get("model")) && "pos".equals(map.get("method"));
    private Predicate<Map<String, ?>> ner = map -> "nlp".equals(map.get("model")) && "ner".equals(map.get("method"));
    private Predicate<Map<String, ?>> keyword = map -> "nlp".equals(map.get("model")) && "keyword".equals(map.get("method"));
    private Predicate<Map<String, ?>> summary = map -> "nlp".equals(map.get("model")) && "summary".equals(map.get("method"));
    private Predicate<Map<String, ?>> classifier = map -> "nlp".equals(map.get("model")) && "classifier".equals(map.get("method"));
    private Predicate<Map<String, ?>> count = map -> "counter".equals(map.get("model")) && "count".equals(map.get("method"));
    private Predicate<Map<String, ?>> find = map -> "pattern".equals(map.get("model")) && "find".equals(map.get("method"));
    private Predicate<Map<String, ?>> match = map -> "pattern".equals(map.get("model")) && "match".equals(map.get("method"));
    private Predicate<Map<String, ?>> baidu = map -> "related".equals(map.get("model")) && "baidu".equals(map.get("method"));
    private Predicate<Map<String, ?>> aminer = map -> "related".equals(map.get("model")) && "aminer".equals(map.get("method"));
    private Predicate<Map<String, ?>> journal = map -> "related".equals(map.get("model")) && "journal".equals(map.get("method"));
    private Predicate<Map<String, ?>> literature = map -> "related".equals(map.get("model")) && "literature".equals(map.get("method"));

    private BiFunction<Map, Map, Object> segmentFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.segment(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> posFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.pos(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> nerFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.ner(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> keywordFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.keyword(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> summaryFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.summary(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> classifierFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.classifier(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> countFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", counterService.count(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> findFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", patternService.find(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> matchFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", patternService.matches(req, args));
        return map;
    };

    private BiFunction<Map, Map, Object> baiduFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractAnalysis(kw));
        return map;
    };

    private BiFunction<Map, Map, Object> aminerFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractAminer(kw));
        return map;
    };

    private BiFunction<Map, Map, Object> journalFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractJournal(kw));
        return map;
    };

    private BiFunction<Map, Map, Object> literatureFun = (req, args) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractLiterature(kw));
        return map;
    };

    public BiFunction getFunction(Map r) {
        if (segment.test(r)) {
            return segmentFun;
        } else if (pos.test(r)) {
            return posFun;
        } else if (ner.test(r)) {
            return nerFun;
        } else if (keyword.test(r)) {
            return keywordFun;
        } else if (summary.test(r)) {
            return summaryFun;
        } else if (classifier.test(r)) {
            return classifierFun;
        } else if (count.test(r)) {
            return countFun;
        } else if (find.test(r)) {
            return findFun;
        } else if (match.test(r)) {
            return matchFun;
        } else if (baidu.test(r)) {
            return baiduFun;
        } else if (aminer.test(r)) {
            return aminerFun;
        } else if (journal.test(r)) {
            return journalFun;
        } else if (literature.test(r)) {
            return literatureFun;
        }
        return (x, y) -> x;
    }


}
