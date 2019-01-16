package com.hiekn.knowledge.mining.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hiekn.nlplab.bean.TermBean;
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

}
