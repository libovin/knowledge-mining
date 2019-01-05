package com.hiekn.knowledge.mining.service;

import com.alibaba.fastjson.JSON;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatternServiceTest {

    @Autowired
    private PatternService patternService;

    @Test
    public void testGetProp() {
        System.out.println(patternService.getProp());
    }

    @Test
    public void testMatches0() {
        List list = new ArrayList();
        list.add(new TermBean("a", "a"));
        list.add(new TermBean("a", "a"));
        list.add(new TermBean("b", "b"));
        list.add(new TermBean("b", "b"));
        list.add(new TermBean("c", "c"));
        list.add(new TermBean("c", "c"));
        list.add(new TermBean("c", "c"));
        System.out.println(JSON.toJSONString(patternService.matches(list, Pattern.compile("\\d+"))));
    }

    @Test
    public void testMatches1() {
        List list = new ArrayList();
        list.add(new PartOfSpeech("a", "a"));
        list.add(new PartOfSpeech("a", "a"));
        list.add(new PartOfSpeech("b", "b"));
        list.add(new PartOfSpeech("b", "b"));
        list.add(new PartOfSpeech("c", "c"));
        list.add(new PartOfSpeech("c", "c"));
        list.add(new PartOfSpeech("c", "c"));
        System.out.println(JSON.toJSONString(patternService.matches(list, Pattern.compile("\\d+"))));
    }


    @Test
    public void testMatches2() {
        List list = new ArrayList();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        System.out.println(JSON.toJSONString(patternService.matches(list, Pattern.compile("\\d+"))));
    }

    @Test
    public void testMatchesString() {
        System.out.println(JSON.toJSONString(patternService.matches("a11a22b33b44c55c66", Pattern.compile("\\d+"))));
    }

    @Test
    public void testFind() {
        System.out.println(JSON.toJSONString(patternService.find("a11a22b33b44c55c66", Pattern.compile("\\d+"))));
    }
}
