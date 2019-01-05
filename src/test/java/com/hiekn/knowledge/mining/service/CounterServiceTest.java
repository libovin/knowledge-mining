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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    @Test
    public void getProp() {
        System.out.println(counterService.getProp());
    }

    @Test
    public void test0() {
        List list = new ArrayList();
        list.add(new TermBean("a", "a"));
        list.add(new TermBean("a", "a"));
        list.add(new TermBean("b", "b"));
        list.add(new TermBean("b", "b"));
        list.add(new TermBean("c", "c"));
        list.add(new TermBean("c", "c"));
        list.add(new TermBean("c", "c"));
        System.out.println(JSON.toJSONString(counterService.count(list)));
    }

    @Test
    public void test1() {
        List list = new ArrayList();
        list.add(new PartOfSpeech("a", "a"));
        list.add(new PartOfSpeech("a", "a"));
        list.add(new PartOfSpeech("b", "b"));
        list.add(new PartOfSpeech("b", "b"));
        list.add(new PartOfSpeech("c", "c"));
        list.add(new PartOfSpeech("c", "c"));
        list.add(new PartOfSpeech("c", "c"));
        System.out.println(JSON.toJSONString(counterService.count(list)));
    }
    @Test
    public void test2() {
        List list = new ArrayList();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        System.out.println(JSON.toJSONString(counterService.count(list)));
    }
}
