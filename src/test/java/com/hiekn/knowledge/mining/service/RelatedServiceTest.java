package com.hiekn.knowledge.mining.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelatedServiceTest {

    @Autowired
    private RelatedService relatedService;

    @Test
    public void testExtractAnalysis(){
        String kw = "";
        System.out.println(relatedService.extractAnalysis(kw));
    }

    @Test
    public void testExtractJournal(){
        String kw = "";
        System.out.println(relatedService.extractJournal(kw));
    }

    @Test
    public void testExtractLiterature(){
        String kw = "";
        System.out.println(relatedService.extractLiterature(kw));
    }

    @Test
    public void testExtractAminer(){
        String kw = "";
        System.out.println(relatedService.extractAminer(kw));
    }
}
