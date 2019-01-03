package com.hiekn.knowledge.mining.service.impl;

import com.alibaba.fastjson.JSON;
import com.hiekn.knowledge.mining.bean.bo.Item;
import com.hiekn.knowledge.mining.bean.bo.PatternFind;
import com.hiekn.knowledge.mining.bean.bo.PatternMatches;
import com.hiekn.knowledge.mining.service.PatternService;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatternServiceImpl implements PatternService {

    @Override
    public Item getProp() {
        return Item.of("匹配模式", "type");
    }

    @Override
    public List<PatternFind> find(String kw, Pattern pattern) {
        Matcher matcher = pattern.matcher(kw);
        List<PatternFind> arrayList = new ArrayList<>();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String word = kw.substring(start, end);
            arrayList.add(new PatternFind(word, start, end));
        }
        return arrayList;
    }

    @Override
    public List<PatternMatches> matches(List list, Pattern pattern) {
        List<PatternMatches> patternList = new ArrayList<>();
        for (Object item : list) {
            if (item instanceof String) {
                String key = (String) item;
                patternList.add(matches(key, pattern));
            } else if (item instanceof PartOfSpeech) {
                PartOfSpeech partOfSpeech = (PartOfSpeech) item;
                patternList.add(matches(partOfSpeech.getDescription(), pattern));
            } else if (item instanceof TermBean) {
                TermBean termBean = (TermBean) item;
                patternList.add(matches(termBean.getTerm(), pattern));
            } else {

            }
        }
        return patternList;
    }

    @Override
    public PatternMatches matches(String string, Pattern pattern) {
        Matcher matcher = pattern.matcher(string);
        return new PatternMatches(string, matcher.matches());
    }


    public static void main(String[] args) {
        PatternServiceImpl service = new PatternServiceImpl();
        List list = new ArrayList();
        list.add(new TermBean("a","a"));
        list.add(new TermBean("a","a"));
        list.add(new TermBean("b","b"));
        list.add(new TermBean("b","b"));
        list.add(new TermBean("c","c"));
        list.add(new TermBean("c","c"));
        list.add(new TermBean("c","c"));
        System.out.println(JSON.toJSONString(service.find("a11a22b33b44c55c66",Pattern.compile("\\d+"))));
    }
}
