package com.hiekn.knowledge.mining.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HandlerTest {

    @Test
    public void test() {
        Predicate<Map<String, ?>> segment = map -> "nlp".equals(map.get("model")) && "segment".equals(map.get("method"));
        Predicate<Map<String, ?>> pos = map -> "nlp".equals(map.get("model")) && "pos".equals(map.get("method"));
        Predicate<Map<String, ?>> ner = map -> "nlp".equals(map.get("model")) && "ner".equals(map.get("method"));
        Predicate<Map<String, ?>> keyword = map -> "nlp".equals(map.get("model")) && "keyword".equals(map.get("method"));
        Predicate<Map<String, ?>> summary = map -> "nlp".equals(map.get("model")) && "summary".equals(map.get("method"));
        Predicate<Map<String, ?>> classifier = map -> "nlp".equals(map.get("model")) && "classifier".equals(map.get("method"));

        Predicate<Map<String, ?>> count = map -> "counter".equals(map.get("model")) && "count".equals(map.get("method"));

        Predicate<Map<String, ?>> find = map -> "pattern".equals(map.get("model")) && "find".equals(map.get("method"));
        Predicate<Map<String, ?>> match = map -> "pattern".equals(map.get("model")) && "match".equals(map.get("method"));

        Predicate<Map<String, ?>> baidu = map -> "related".equals(map.get("model")) && "baidu".equals(map.get("method"));
        Predicate<Map<String, ?>> aminer = map -> "related".equals(map.get("model")) && "aminer".equals(map.get("method"));
        Predicate<Map<String, ?>> journal = map -> "related".equals(map.get("model")) && "journal".equals(map.get("method"));
        Predicate<Map<String, ?>> literature = map -> "related".equals(map.get("model")) && "literature".equals(map.get("method"));

    }
}
