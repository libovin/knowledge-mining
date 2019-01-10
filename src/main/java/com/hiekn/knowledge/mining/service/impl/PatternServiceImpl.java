package com.hiekn.knowledge.mining.service.impl;

import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.service.PatternService;
import com.hiekn.knowledge.mining.util.RuleUtils;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatternServiceImpl implements PatternService {

    @Override
    public Object find(Map req, Map config) {
        List<PatternFind> arrayList = new ArrayList<>();
        String input = (String) config.get("input");
        String patternId = (String) config.get("pattern");
        if ("content".equals(input)) {
            Object o = req.get("content");
            if (o instanceof String) {
                String content = (String) o;
                return find(content, patternId);
            }
        } else if ("result".equals(input)) {
            Object o = req.get("result");
            if (o instanceof String) {
                String result = (String) o;
                return find(result, patternId);
            }
        }
        return arrayList;
    }

    @Override
    public Object matches(Map req, Map config) {

        String input = (String) config.get("input");
        String patternId = (String) config.get("pattern");

        List<PatternMatches> patternList = new ArrayList<>();
        if ("content".equals(input)) {
            Object o = req.get("content");
            if (o instanceof String) {
                String content = (String) o;
                return matches(content, patternId);
            }
        } else if ("result".equals(input)) {
            Object o = req.get("content");
            if (o instanceof List) {
                List result = (List) o;
                for (Object item : result) {
                    if (item instanceof String) {
                        String key = (String) item;
                        patternList.add(matches(key, patternId));
                    } else if (item instanceof PartOfSpeech) {
                        PartOfSpeech partOfSpeech = (PartOfSpeech) item;
                        patternList.add(matches(partOfSpeech.getDescription(), patternId));
                    } else if (item instanceof TermBean) {
                        TermBean termBean = (TermBean) item;
                        patternList.add(matches(termBean.getTerm(), patternId));
                    }
                }
            }
        }
        return patternList;
    }

    private PatternMatches matches(String string, String patternId) {
        Pattern pattern = RuleUtils.instance.getRulePattern(patternId);
        Matcher matcher = pattern.matcher(string);
        return new PatternMatches(string, matcher.matches());
    }

    private List<PatternFind> find(String kw, String patternId) {
        List<PatternFind> arrayList = new ArrayList<>();
        Pattern pattern = RuleUtils.instance.getRulePattern(patternId);
        Matcher matcher = pattern.matcher(kw);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String word = kw.substring(start, end);
            arrayList.add(new PatternFind(word, start, end));
        }
        return arrayList;
    }

}
