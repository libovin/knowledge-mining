package com.hiekn.knowledge.mining.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hiekn.nlp.bean.PartOfSpeech;
import com.hiekn.nlp.bean.TermBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    private Map config;

    @Before
    public void buildConfig() {
        Map map = Maps.newHashMap();
        map.put("model","counter");
        map.put("method","count");
        map.put("input","result");
        this.config = map;
    }

    @Test
    public void test0() {
        Map map = Maps.newHashMap();
        List list = new ArrayList();
        list.add(new TermBean("a", "a"));
        list.add(new TermBean("a", "a"));
        list.add(new TermBean("b", "b"));
        list.add(new TermBean("b", "b"));
        list.add(new TermBean("c", "c"));
        list.add(new TermBean("c", "c"));
        list.add(new TermBean("c", "c"));
        map.put("result", list);
        System.out.println(JSON.toJSONString(counterService.count(map, config)));
    }

    @Test
    public void test1() {
        Map map = Maps.newHashMap();
        List list = new ArrayList();
        list.add(new PartOfSpeech("a", "a"));
        list.add(new PartOfSpeech("a", "a"));
        list.add(new PartOfSpeech("b", "b"));
        list.add(new PartOfSpeech("b", "b"));
        list.add(new PartOfSpeech("c", "c"));
        list.add(new PartOfSpeech("c", "c"));
        list.add(new PartOfSpeech("c", "c"));
        map.put("result", list);
        System.out.println(JSON.toJSONString(counterService.count(map, config)));
    }

    @Test
    public void test2() {
        Map map = Maps.newHashMap();
        List list = new ArrayList();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        map.put("result", list);
        System.out.println(JSON.toJSONString(counterService.count(map, config)));
    }
}
