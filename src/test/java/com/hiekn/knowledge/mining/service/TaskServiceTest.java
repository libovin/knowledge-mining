package com.hiekn.knowledge.mining.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void getProp() {
        System.out.println(JSON.toJSONString(taskService.getProp()));
    }

    @Test
    public void preprocessTest() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String fileName = "request.json";
        Enumeration<URL> resources;
        JSONObject jsonObject = new JSONObject();
        try {
            resources = classLoader.getResources(fileName);
        } catch (IOException e) {
            return;
        }
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            try {
                String json = Resources.toString(url, Charsets.UTF_8);
                jsonObject.putAll(JSON.parseObject(json));
            } catch (IOException e) {
            }
        }
        System.out.println(JSON.toJSONString(taskService.preprocess(jsonObject)));
    }
}
