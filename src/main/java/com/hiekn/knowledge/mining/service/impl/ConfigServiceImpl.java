package com.hiekn.knowledge.mining.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import com.hiekn.knowledge.mining.bean.dao.ConfigReq;
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

    private Predicate<ConfigReq> segment = map -> "nlp".equals(map.getModel()) && "segment".equals(map.getMethod());
    private Predicate<ConfigReq> pos = map -> "nlp".equals(map.getModel()) && "pos".equals(map.getMethod());
    private Predicate<ConfigReq> ner = map -> "nlp".equals(map.getModel()) && "ner".equals(map.getMethod());
    private Predicate<ConfigReq> keyword = map -> "nlp".equals(map.getModel()) && "keyword".equals(map.getMethod());
    private Predicate<ConfigReq> summary = map -> "nlp".equals(map.getModel()) && "summary".equals(map.getMethod());
    private Predicate<ConfigReq> classifier = map -> "nlp".equals(map.getModel()) && "classifier".equals(map.getMethod());
    private Predicate<ConfigReq> denpendency = map -> "nlp".equals(map.getMethod()) && "denpendency".equals(map.getMethod());
    private Predicate<ConfigReq> count = map -> "counter".equals(map.getModel()) && "count".equals(map.getMethod());
    private Predicate<ConfigReq> find = map -> "pattern".equals(map.getModel()) && "find".equals(map.getMethod());
    private Predicate<ConfigReq> match = map -> "pattern".equals(map.getModel()) && "match".equals(map.getMethod());
    private Predicate<ConfigReq> baidu = map -> "related".equals(map.getModel()) && "baidu".equals(map.getMethod());
    private Predicate<ConfigReq> aminer = map -> "related".equals(map.getModel()) && "aminer".equals(map.getMethod());
    private Predicate<ConfigReq> journal = map -> "related".equals(map.getModel()) && "journal".equals(map.getMethod());
    private Predicate<ConfigReq> literature = map -> "related".equals(map.getModel()) && "literature".equals(map.getMethod());

    private BiFunction<Map, ConfigReq, Object> segmentFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.segment(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> posFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.pos(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> nerFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.ner(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> keywordFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.keyword(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> summaryFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.summary(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> classifierFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", nlpService.classifier(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> denpendencyFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", nlpService.denpendency(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> countFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();

        map.put("result", counterService.count(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> findFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", patternService.find(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> matchFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        map.put("result", patternService.matches(req, configReq));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> baiduFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractAnalysis(kw));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> aminerFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractAminer(kw));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> journalFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractJournal(kw));
        return map;
    };

    private BiFunction<Map, ConfigReq, Object> literatureFun = (req, configReq) -> {
        Map<String, Object> map = Maps.newHashMap();
        String kw = (String) req.get("content");
        map.put("result", relatedService.extractLiterature(kw));
        return map;
    };


    public BiFunction getFunction(ConfigReq r) {
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
        } else if (denpendency.test(r)) {
            return denpendencyFun;
        }
        return (x, y) -> x;
    }


}
