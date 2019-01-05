package com.hiekn.knowledge.mining.service.impl;

import com.google.common.collect.Maps;
import com.hiekn.knowledge.mining.bean.vo.CounterConfig;
import com.hiekn.knowledge.mining.bean.vo.NlpConfig;
import com.hiekn.knowledge.mining.bean.vo.PatternConfig;
import com.hiekn.knowledge.mining.bean.vo.RelatedConfig;
import com.hiekn.knowledge.mining.service.ConfigService;
import com.hiekn.knowledge.mining.service.CounterService;
import com.hiekn.knowledge.mining.service.NlpService;
import com.hiekn.knowledge.mining.service.PatternService;
import com.hiekn.knowledge.mining.service.RelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    public Map handler(Map result, CounterConfig config) {
        Map map = Maps.newHashMap();
        List list = (List) result.get("result");
        map.put("content", result.get("content"));
        map.put("result", counterService.count(list));
        return map;
    }

    @Override
    public Map handler(Map result, NlpConfig config) {
        Map map = Maps.newHashMap();
        String content = (String) result.get("content");
        Object o = null;
        if ("segment".equals(config.getMethod())) {
            if ("crf".equals(config.getAlgorithm())){
                o = nlpService.segment(content);
            }


        } else if ("pos".equals(config.getMethod())) {
            o = nlpService.pos(content);
        } else if ("ner".equals(config.getMethod())) {
            o = nlpService.ner(content);
        } else if ("keyword".equals(config.getMethod())) {
            o = nlpService.extractKeyword(content);
        } else if ("summary".equals(config.getMethod())) {
            o = nlpService.autoSummary(content);
        } else if ("textClassification".equals(config.getMethod())) {
            o = nlpService.textClassification(content);
        }
        map.put("content", result.get("content"));
        map.put("result", o);
        return map;
    }

    @Override
    public Map handler(Map result, PatternConfig config) {
        Map map = Maps.newHashMap();
        Object t = null;
        Object o = null;
        if ("content".equals(config.getSource())) {
            o = result.get("content");
        } else if ("result".equals(config.getSource())) {
            o = result.get("result");
        }
        String p = config.getPattern();
        Pattern pattern = Pattern.compile(p);
        if ("find".equals(config.getMethod())) {
            t = patternService.find((String) o, pattern);
        } else if ("matches".equals(config.getMethod())) {
            if (o instanceof String) {
                t = patternService.matches((String) o, pattern);
            } else if (o instanceof List) {
                t = patternService.matches((List) o, pattern);
            }
        }
        map.put("content", result.get("content"));
        map.put("result", t);
        return map;
    }

    @Override
    public Map handler(Map result, RelatedConfig config) {
        Map t = null;
        String content = (String) result.get("content");
        if ("baidu".equals(config.getMethod())) {
            t = relatedService.extractAnalysis(content);
        } else if ("aminer".equals(config.getMethod())) {
            t = relatedService.extractAminer(content);
        } else if ("journal".equals(config.getMethod())) {
            t = relatedService.extractJournal(content);
        } else if ("literature".equals(config.getMethod())) {
            t = relatedService.extractLiterature(content);
        }
        return t;
    }


    @Override
    public Map getProp() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nlp", nlpService.getProp());
        map.put("counter",counterService.getProp());
        map.put("pattern", patternService.getProp());
        map.put("related", relatedService.getProp());
        return map;
    }
}
