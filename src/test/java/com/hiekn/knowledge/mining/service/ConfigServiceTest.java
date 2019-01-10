package com.hiekn.knowledge.mining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigServiceTest {
    @Autowired
    private ConfigService configService;

    @Test
    public void getPropTest(){
        System.out.println(configService.getProp());
    }
}
